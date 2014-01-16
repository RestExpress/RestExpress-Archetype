#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;
import ${package}.config.Configuration;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
	{
		// TODO: Your routes here...
		server.uri("/samples/uuid/{uuid}.{format}", config.getSampleUuidEntityController())
		    .method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
		    .name(Constants.Routes.SINGLE_UUID_SAMPLE);

		server.uri("/samples/uuid.{format}", config.getSampleUuidEntityController())
		    .method(HttpMethod.POST)
		    .name(Constants.Routes.SAMPLE_UUID_COLLECTION);

		server.uri("/samples/compound/{key1}/{key2}/{key3}.{format}", config.getSampleCompoundIdentifierEntityController())
		    .method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
		    .name(Constants.Routes.SINGLE_COMPOUND_SAMPLE);
	
		server.uri("/samples/compound/{key1}/{key2}.{format}", config.getSampleCompoundIdentifierEntityController())
		    .action("readAll", HttpMethod.GET)
		    .method(HttpMethod.POST)
		    .name(Constants.Routes.SAMPLE_COMPOUND_COLLECTION);

		// or REGEX matching routes...
		// server.regex("/some.regex", config.getRouteController());
	}
}
