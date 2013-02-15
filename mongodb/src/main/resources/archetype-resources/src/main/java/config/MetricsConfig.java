#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strategicgains.restexpress.exception.ConfigurationException;

public class MetricsConfig
{
	private static final Logger LOG = LoggerFactory.getLogger(MetricsConfig.class);
	private static final String METRICS_IS_ENABLED_PROPERTY = "metrics.isEnabled";
	private static final String METRICS_MACHINE_NAME_PROPERTY = "metrics.machineName";
	private static final String GRAPHITE_IS_ENABLED_PROPERTY = "metrics.graphite.isEnabled";
	private static final String GRAPHITE_HOST_PROPERTY = "metrics.graphite.host";
	private static final String GRAPHITE_PORT_PROPERTY = "metrics.graphite.port";
	private static final String GRAPHITE_PUBLISHING_SECONDS_PROPERTY = "metrics.graphite.publishSeconds";

	private boolean isEnabled;
	private String machineName;
	private boolean isGraphiteEnabled;
	private String graphiteHost;
	private Integer graphitePort;
	private Integer publishSeconds;

	public MetricsConfig(Properties p)
	{
		isEnabled = Boolean.parseBoolean(p.getProperty(METRICS_IS_ENABLED_PROPERTY, "true"));
		if (!isEnabled) return;

		isGraphiteEnabled = Boolean.parseBoolean(p.getProperty(GRAPHITE_IS_ENABLED_PROPERTY, "true"));
		if (!isGraphiteEnabled) return;

		machineName = p.getProperty(METRICS_MACHINE_NAME_PROPERTY);
		
		if (machineName == null)
		{
			LOG.warn("*** Machine Name (" + METRICS_MACHINE_NAME_PROPERTY + ") is not set. ***");
		}

		graphiteHost = p.getProperty(GRAPHITE_HOST_PROPERTY);

		if (graphiteHost == null)
		{
			throw new ConfigurationException("Please define a graphite host for property: " + GRAPHITE_HOST_PROPERTY);
		}

		try
		{
			graphitePort = Integer.parseInt(p.getProperty(GRAPHITE_PORT_PROPERTY));
		}
		catch (NumberFormatException e)
		{
			throw new ConfigurationException("Please define a graphite port for property: " + GRAPHITE_PORT_PROPERTY, e);
		}

		try
		{
			publishSeconds = Integer.parseInt(p.getProperty(GRAPHITE_PUBLISHING_SECONDS_PROPERTY));
		}
		catch (NumberFormatException e)
		{
			throw new ConfigurationException("Please define how frequently (in seconds) to publish to graphite in property: "
			        + GRAPHITE_PUBLISHING_SECONDS_PROPERTY, e);
		}
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}
	
	public String getMachineName()
	{
		return machineName;
	}

	public boolean isGraphiteEnabled()
	{
		return isGraphiteEnabled;
	}

	public String getGraphiteHost()
	{
		return graphiteHost;
	}

	public Integer getGraphitePort()
	{
		return graphitePort;
	}

	public Integer getPublishSeconds()
	{
		return publishSeconds;
	}
}
