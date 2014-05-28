#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Map;

import org.restexpress.RestExpress;
import ${package}.domain.SampleOidEntity;
import ${package}.domain.SampleUuidEntity;

import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.RelTypes;

public abstract class Relationships
{
	public static void define(RestExpress server)
	{
		Map<String, String> routes = server.getRouteUrlsByName();

		HyperExpress.relationships()
		.forCollectionOf(SampleUuidEntity.class)
			.rel(RelTypes.SELF, routes.get(Constants.Routes.SAMPLE_UUID_COLLECTION))
				.withQuery("limit={limit}")
				.withQuery("offset={offset}")
			.rel(RelTypes.NEXT, routes.get(Constants.Routes.SAMPLE_UUID_COLLECTION) + "?offset={nextOffset}")
				.withQuery("limit={limit}")
				.optional()
			.rel(RelTypes.PREV, routes.get(Constants.Routes.SAMPLE_UUID_COLLECTION) + "?offset={prevOffset}")
				.withQuery("limit={limit}")
				.optional()

		.forClass(SampleUuidEntity.class)
			.rel(RelTypes.SELF, routes.get(Constants.Routes.SINGLE_UUID_SAMPLE))
			.rel(RelTypes.UP, routes.get(Constants.Routes.SAMPLE_UUID_COLLECTION))

		.forCollectionOf(SampleOidEntity.class)
			.rel(RelTypes.SELF, routes.get(Constants.Routes.SAMPLE_OID_COLLECTION))
				.withQuery("limit={limit}")
				.withQuery("offset={offset}")
			.rel(RelTypes.NEXT, routes.get(Constants.Routes.SAMPLE_OID_COLLECTION) + "?offset={nextOffset}")
				.withQuery("limit={limit}")
				.optional()
			.rel(RelTypes.PREV, routes.get(Constants.Routes.SAMPLE_OID_COLLECTION) + "?offset={prevOffset}")
				.withQuery("limit={limit}")
				.optional()

		.forClass(SampleOidEntity.class)
			.rel(RelTypes.SELF, routes.get(Constants.Routes.SINGLE_OID_SAMPLE))
			.rel(RelTypes.UP, routes.get(Constants.Routes.SAMPLE_OID_COLLECTION));
	}
}
