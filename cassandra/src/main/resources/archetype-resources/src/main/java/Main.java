#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.restexpress.util.Environment;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Configuration config = Environment.load(args, Configuration.class);
		Server server = new Server(config);
		server.start().awaitShutdown();
	}
}
