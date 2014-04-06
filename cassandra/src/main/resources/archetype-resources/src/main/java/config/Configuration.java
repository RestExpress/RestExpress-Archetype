#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.util.Properties;

import org.restexpress.RestExpress;
import ${package}.controller.SampleCompoundIdentifierEntityController;
import ${package}.controller.SampleUuidEntityController;
import ${package}.persistence.SampleCompoundIdentifierEntityRepository;
import ${package}.persistence.SampleUuidEntityRepository;
import ${package}.service.SampleCompoundIdentifierEntityService;
import ${package}.service.SampleUuidEntityService;
import org.restexpress.util.Environment;

import com.strategicgains.repoexpress.cassandra.CassandraConfig;
import com.strategicgains.restexpress.plugin.metrics.MetricsConfig;

public class Configuration
extends Environment
{
	private static final String DEFAULT_EXECUTOR_THREAD_POOL_SIZE = "20";

	private static final String PORT_PROPERTY = "port";
	private static final String BASE_URL_PROPERTY = "base.url";
	private static final String EXECUTOR_THREAD_POOL_SIZE = "executor.threadPool.size";

	private int port;
	private String baseUrl;
	private int executorThreadPoolSize;
	private MetricsConfig metricsSettings;

	private SampleUuidEntityController sampleUuidEntityController;
	private SampleCompoundIdentifierEntityController sampleCompoundIdentifierEntityController;

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		this.executorThreadPoolSize = Integer.parseInt(p.getProperty(EXECUTOR_THREAD_POOL_SIZE, DEFAULT_EXECUTOR_THREAD_POOL_SIZE));
		this.metricsSettings = new MetricsConfig(p);
		CassandraConfig dbConfig = new CassandraConfig(p);
		initialize(dbConfig);
	}

	private void initialize(CassandraConfig dbConfig)
	{
		SampleUuidEntityRepository sampleUuidEntityRepository = new SampleUuidEntityRepository(dbConfig.getSession());
		SampleUuidEntityService SampleUuidEntityService = new SampleUuidEntityService(sampleUuidEntityRepository);
		sampleUuidEntityController = new SampleUuidEntityController(SampleUuidEntityService);
		
		SampleCompoundIdentifierEntityRepository sampleCompoundIdentifierEntityRepository = new SampleCompoundIdentifierEntityRepository(dbConfig.getSession());
		SampleCompoundIdentifierEntityService sampleCompoundIdentifierEntityService = new SampleCompoundIdentifierEntityService(sampleCompoundIdentifierEntityRepository);
		sampleCompoundIdentifierEntityController = new SampleCompoundIdentifierEntityController(sampleCompoundIdentifierEntityService);
	}

	public int getPort()
	{
		return port;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
	
	public int getExecutorThreadPoolSize()
	{
		return executorThreadPoolSize;
	}

	public MetricsConfig getMetricsConfig()
    {
	    return metricsSettings;
    }

	public SampleUuidEntityController getSampleUuidEntityController()
	{
		return sampleUuidEntityController;
	}

	public SampleCompoundIdentifierEntityController getSampleCompoundIdentifierEntityController()
	{
		return sampleCompoundIdentifierEntityController;
	}
}
