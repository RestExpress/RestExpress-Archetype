#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strategicgains.restexpress.Format;
import com.strategicgains.restexpress.Parameters;
import com.strategicgains.restexpress.RestExpress;
import com.strategicgains.restexpress.exception.BadRequestException;
import com.strategicgains.restexpress.pipeline.SimpleConsoleLogMessageObserver;
import com.strategicgains.restexpress.plugin.cache.CacheControlPlugin;
import com.strategicgains.restexpress.plugin.metrics.MetricsPlugin;
import com.strategicgains.restexpress.plugin.route.RoutesMetadataPlugin;
import ${package}.config.Configuration;
import ${package}.config.MetricsConfig;
import ${package}.serialization.ResponseProcessors;
import com.strategicgains.restexpress.util.Environment;
import com.strategicgains.syntaxe.ValidationException;
import com.yammer.metrics.reporting.GraphiteReporter;

public class Main
{
	private static final String SERVICE_NAME = "TODO: Enter Service Name";
	private static final Logger LOG = LoggerFactory.getLogger(SERVICE_NAME);

	public static void main(String[] args) throws Exception
	{
		Configuration config = loadEnvironment(args);
		RestExpress server = new RestExpress()
		    .setName(SERVICE_NAME)
		    .setBaseUrl(config.getBaseUrl())
		    .setDefaultFormat(config.getDefaultFormat())
		    .setExecutorThreadCount(config.getExecutorThreadPoolSize())
		    .putResponseProcessor(Format.JSON, ResponseProcessors.json())
		    .putResponseProcessor(Format.XML, ResponseProcessors.xml())
		    .putResponseProcessor(Format.WRAPPED_JSON, ResponseProcessors.wrappedJson())
		    .putResponseProcessor(Format.WRAPPED_XML, ResponseProcessors.wrappedXml())
		    .addMessageObserver(new SimpleConsoleLogMessageObserver());

		Routes.define(config, server);
		configureMetrics(config, server);

		new RoutesMetadataPlugin()							// Support basic discoverability.
			.register(server)
			.parameter(Parameters.Cache.MAX_AGE, 86400);	// Cache for 1 day (24 hours).

		new CacheControlPlugin()							// Support caching headers.
			.register(server);

		mapExceptions(server);
		server.bind(config.getPort());
		server.awaitShutdown();
	}

	private static void configureMetrics(Configuration config, RestExpress server)
    {
		MetricsConfig mc = config.getMetricsConfig();

	    if (mc.isEnabled())
		{
			new MetricsPlugin()									// Instrument all routes.
				.register(server);

			if (mc.isGraphiteEnabled())
			{
				GraphiteReporter.enable(mc.getPublishSeconds(), TimeUnit.SECONDS,
					mc.getGraphiteHost(), mc.getGraphitePort());
			}
			else
			{
				LOG.warn("*** Graphite Metrics Publishing is Disabled ***");
			}
		}
		else
		{
			LOG.warn("*** Metrics Generation is Disabled ***");
		}
    }

    private static void mapExceptions(RestExpress server)
    {
    	server
	    	.mapException(ValidationException.class, BadRequestException.class);
    }

	private static Configuration loadEnvironment(String[] args)
    throws FileNotFoundException, IOException
    {
	    if (args.length > 0)
		{
			return Environment.from(args[0], Configuration.class);
		}

	    return Environment.fromDefault(Configuration.class);
    }
}
