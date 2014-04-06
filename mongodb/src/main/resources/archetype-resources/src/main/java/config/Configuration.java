#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.util.Properties;

import org.restexpress.RestExpress;
import ${package}.controller.SampleOidEntityController;
import ${package}.controller.SampleUuidEntityController;
import ${package}.persistence.SampleOidEntityRepository;
import ${package}.persistence.SampleUuidEntityRepository;
import ${package}.service.SampleOidEntityService;
import ${package}.service.SampleUuidEntityService;
import org.restexpress.util.Environment;

import com.strategicgains.repoexpress.mongodb.MongoConfig;
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

	private SampleUuidEntityController sampleUuidController;
	private SampleOidEntityController sampleOidController;

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		this.executorThreadPoolSize = Integer.parseInt(p.getProperty(EXECUTOR_THREAD_POOL_SIZE, DEFAULT_EXECUTOR_THREAD_POOL_SIZE));
		this.metricsSettings = new MetricsConfig(p);
		MongoConfig mongo = new MongoConfig(p);
		initialize(mongo);
	}

	private void initialize(MongoConfig mongo)
	{
        SampleUuidEntityRepository samplesUuidRepository = new SampleUuidEntityRepository(mongo.getClient(), mongo.getDbName());
		SampleUuidEntityService sampleUuidService = new SampleUuidEntityService(samplesUuidRepository);
		sampleUuidController = new SampleUuidEntityController(sampleUuidService);

		SampleOidEntityRepository samplesOidRepository = new SampleOidEntityRepository(mongo.getClient(), mongo.getDbName());
		SampleOidEntityService sampleOidService = new SampleOidEntityService(samplesOidRepository);
		sampleOidController = new SampleOidEntityController(sampleOidService);
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
		return sampleUuidController;
	}

	public SampleOidEntityController getSampleOidEntityController()
	{
		return sampleOidController;
	}
}
