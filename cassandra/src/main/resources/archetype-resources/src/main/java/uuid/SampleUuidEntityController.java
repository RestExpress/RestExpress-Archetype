#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.uuid;

import org.restexpress.Request;
import org.restexpress.Response;
import ${package}.Constants;

import com.strategicgains.hyperexpress.builder.DefaultTokenResolver;
import com.strategicgains.hyperexpress.builder.DefaultUrlBuilder;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.strategicgains.repoexpress.adapter.Identifiers;
import com.strategicgains.repoexpress.util.UuidConverter;

import io.netty.handler.codec.http.HttpMethod;

/**
 * This is the 'controller' layer, where HTTP details are converted to domain concepts and passed to the service layer.
 * Then service layer response information is enhanced with HTTP details, if applicable, for the response.
 * <p/>
 * This controller demonstrates how to process a Cassandra entity that is identified by a single, primary row key such
 * as a UUID.
 */
public class SampleUuidEntityController
{
	private static final UrlBuilder LOCATION_BUILDER = new DefaultUrlBuilder();
	private SampleUuidEntityService service;
	
	public SampleUuidEntityController(SampleUuidEntityService sampleService)
	{
		super();
		this.service = sampleService;
	}

	public SampleUuidEntity create(Request request, Response response)
	{
		SampleUuidEntity entity = request.getBodyAs(SampleUuidEntity.class, "Resource details not provided");
		SampleUuidEntity saved = service.create(entity);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_UUID_SAMPLE);
		response.addLocationHeader(LOCATION_BUILDER.build(locationPattern, new DefaultTokenResolver()));

		// Return the newly-created resource...
		return saved;
	}

	public SampleUuidEntity read(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		return service.read(Identifiers.UUID.parse(id));
	}

	public void update(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		SampleUuidEntity entity = request.getBodyAs(SampleUuidEntity.class, "Resource details not provided");
		entity.setUuid(UuidConverter.parse(id));
		service.update(entity);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		service.delete(Identifiers.UUID.parse(id));
		response.setResponseNoContent();
	}
}
