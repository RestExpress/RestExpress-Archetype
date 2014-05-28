#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.FileNotFoundException;
import java.io.IOException;

import org.restexpress.RestExpress;
import org.restexpress.pipeline.SimpleConsoleLogMessageObserver;
import ${package}.config.Configuration;
import ${package}.serialization.SerializationProvider;
import org.restexpress.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{
	private static final String SERVICE_NAME = "TODO: Enter Service Name";
	private static final Logger LOG = LoggerFactory.getLogger(SERVICE_NAME);

	public static void main(String[] args) throws Exception
	{
		RestExpress server = initializeServer(args);
		server.awaitShutdown();
	}

	public static RestExpress initializeServer(String[] args) throws IOException
	{
		RestExpress.setSerializationProvider(new SerializationProvider());

		Configuration config = loadEnvironment(args);
		RestExpress server = new RestExpress()
				.setName(SERVICE_NAME)
				.setBaseUrl(config.getBaseUrl())
				.setExecutorThreadCount(config.getExecutorThreadPoolSize())
				.addMessageObserver(new SimpleConsoleLogMessageObserver());

		Routes.define(config, server);
		server.bind(config.getPort());
		return server;
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
