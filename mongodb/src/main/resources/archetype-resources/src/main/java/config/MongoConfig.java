#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.strategicgains.restexpress.exception.ConfigurationException;

public class MongoConfig
{
	private static final String BOOTSTRAPS_PROPERTY = "mongodb.bootstraps";
	private static final String DATABASE_PROPERTY = "mongodb.database";
	private static final String USERNAME_PROPERTY = "mongodb.user";
	private static final String PASSWORD_PROPERTY = "mongodb.password";
	private static final String CONNECTIONS_PER_HOST_PROPERTY = "mongodb.connectionsPerHost";
	private static final String USE_KEEPALIVE_PROPERTY = "mongodb.useKeepAlive";
	private static final String CONNECT_TIMEOUT_PROPERTY = "mongodb.connectTimeoutMS";
	private static final String AUTO_CONNECT_RETRY_PROPERTY = "mongodb.autoConnectRetry";
	private static final String AUTO_CONNECT_RETRY_TIME_PROPERTY = "mongodb.maxAutoConnectRetryTimeMS";
	private static final String MAX_WAIT_TIME_PROPERTY = "mongodb.maxWaitTimeMS";
	private static final String SOCKET_TIMEOUT_PROPERTY = "mongodb.socketTimeoutMS";

	private String dbName;
	private MongoClient client;

    public MongoConfig(Properties p)
    {
		dbName = p.getProperty(DATABASE_PROPERTY);

		if (dbName == null)
		{
			throw new ConfigurationException("Please define a database name for property: " + DATABASE_PROPERTY);
		}

		String dbUser = p.getProperty(USERNAME_PROPERTY);
		String dbPassword = p.getProperty(PASSWORD_PROPERTY);
		List<ServerAddress> bootstraps = null;

		try
		{
			String bootstrapString = p.getProperty(BOOTSTRAPS_PROPERTY);
			bootstraps = parseBootstrapString(bootstrapString);
		}
		catch (Exception e)
		{
			throw new ConfigurationException(e);
		}
		
		MongoClientOptions defaults = new MongoClientOptions.Builder().build();
		int connectionsPerHost = Integer.parseInt(p.getProperty(CONNECTIONS_PER_HOST_PROPERTY, String.valueOf(defaults.getConnectionsPerHost())));
		int connectTimeout = Integer.parseInt(p.getProperty(CONNECT_TIMEOUT_PROPERTY, String.valueOf(defaults.getConnectTimeout())));
		boolean useKeepAlive = Boolean.parseBoolean(p.getProperty(USE_KEEPALIVE_PROPERTY, String.valueOf(defaults.isSocketKeepAlive())));
		boolean autoConnectRetry = Boolean.parseBoolean(p.getProperty(AUTO_CONNECT_RETRY_PROPERTY, String.valueOf(defaults.isAutoConnectRetry())));
		long autoConnectRetryTime = Long.parseLong(p.getProperty(AUTO_CONNECT_RETRY_TIME_PROPERTY, String.valueOf(defaults.getMaxAutoConnectRetryTime())));
		int maxWaitTime = Integer.parseInt(p.getProperty(MAX_WAIT_TIME_PROPERTY, String.valueOf(defaults.getMaxWaitTime())));
		int socketTimeout = Integer.parseInt(p.getProperty(SOCKET_TIMEOUT_PROPERTY, String.valueOf(defaults.getSocketTimeout())));

		MongoClientOptions options = new MongoClientOptions.Builder()
			.connectionsPerHost(connectionsPerHost)
			.socketKeepAlive(useKeepAlive)
			.socketTimeout(socketTimeout)
			.connectTimeout(connectTimeout)
			.maxWaitTime(maxWaitTime)
			.autoConnectRetry(autoConnectRetry)
			.maxAutoConnectRetryTime(autoConnectRetryTime)
			.build();

		client = new MongoClient(bootstraps, options);

		if (dbUser != null && dbPassword != null && dbPassword.length() > 0)
		{
			if (!client.getDB(dbName).authenticate(dbUser, dbPassword.toCharArray()))
			{
				throw new ConfigurationException("User not authenticated against database: " + dbName);
			}
		}
    }

	public String getDbName()
	{
		return dbName;
	}

	public MongoClient getClient()
	{
		return client;
	}

	private List<ServerAddress> parseBootstrapString(String bootstrapString)
	throws NumberFormatException,
	    UnknownHostException
	{
		if (bootstrapString == null || bootstrapString.trim().isEmpty())
		{
			throw new ConfigurationException("Please set MongoDB bootstrap servers in property: " + BOOTSTRAPS_PROPERTY);
		}

		String[] serverAndPorts = bootstrapString.split(",");
		List<ServerAddress> results = new ArrayList<ServerAddress>(serverAndPorts.length);

		for (String sp : serverAndPorts)
		{
			String[] server = sp.split(":");

			if (server.length == 2)
			{
				results.add(new ServerAddress(server[0], Integer.parseInt(server[1])));
			}
			else if (server.length == 1)
			{
				results.add(new ServerAddress(server[0]));
			}
		}

		return results;
	}
}
