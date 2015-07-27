#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.objectid;

import io.netty.handler.codec.http.HttpMethod;

import java.util.List;

import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.common.query.QueryFilter;
import org.restexpress.common.query.QueryOrder;
import org.restexpress.common.query.QueryRange;
import org.restexpress.query.QueryFilters;
import org.restexpress.query.QueryOrders;
import org.restexpress.query.QueryRanges;
import ${package}.Constants;

import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.builder.DefaultUrlBuilder;
import com.strategicgains.hyperexpress.builder.TokenBinder;
import com.strategicgains.hyperexpress.builder.TokenResolver;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.strategicgains.repoexpress.mongodb.Identifiers;

/**
 * This is the 'controller' layer, where HTTP details are converted to domain concepts and passed to the service layer.
 * Then service layer response information is enhanced with HTTP details, if applicable, for the response.
 * <p/>
 * This controller demonstrates how to process an entity that is identified by a MongoDB ObjectId.
 */
public class SampleOidEntityController
{
	private static final UrlBuilder LOCATION_BUILDER = new DefaultUrlBuilder();
	private SampleOidEntityService service;

	public SampleOidEntityController(SampleOidEntityService sampleService)
	{
		super();
		this.service = sampleService;
	}

	public SampleOidEntity create(Request request, Response response)
	{
		SampleOidEntity entity = request.getBodyAs(SampleOidEntity.class, "Resource details not provided");
		SampleOidEntity saved = service.create(entity);

		// Construct the response for create...
		response.setResponseCreated();

		// Bind the resource with link URL tokens, etc. here...
		TokenResolver resolver = HyperExpress.bind(Constants.Url.SAMPLE_ID, Identifiers.MONGOID.format(saved.getId()));

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_OID_SAMPLE);
		response.addLocationHeader(LOCATION_BUILDER.build(locationPattern, resolver));

		// Return the newly-created resource...
		return saved;
	}

	public SampleOidEntity read(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.SAMPLE_ID, "No resource ID supplied");
		SampleOidEntity entity = service.read(Identifiers.MONGOID.parse(id));

		// enrich the resource with links, etc. here...
		HyperExpress.bind(Constants.Url.SAMPLE_ID, Identifiers.MONGOID.format(entity.getId()));

		return entity;
	}

	public List<SampleOidEntity> readAll(Request request, Response response)
	{
		QueryFilter filter = QueryFilters.parseFrom(request);
		QueryOrder order = QueryOrders.parseFrom(request);
		QueryRange range = QueryRanges.parseFrom(request, 20);
		List<SampleOidEntity> entities = service.readAll(filter, range, order);
		long count = service.count(filter);
		response.setCollectionResponse(range, entities.size(), count);

		// Bind the resources in the collection with link URL tokens, etc. here...
		HyperExpress.tokenBinder(new TokenBinder<SampleOidEntity>()
		{
			@Override
			public void bind(SampleOidEntity entity, TokenResolver resolver)
			{
				resolver.bind(Constants.Url.SAMPLE_ID, Identifiers.MONGOID.format(entity.getId()));
			}
		});

		return entities;
	}

	public void update(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.SAMPLE_ID, "No resource ID supplied");
		SampleOidEntity entity = request.getBodyAs(SampleOidEntity.class, "Resource details not provided");
		entity.setId(Identifiers.MONGOID.parse(id));
		service.update(entity);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.SAMPLE_ID, "No resource ID supplied");
		service.delete(Identifiers.MONGOID.parse(id));
		response.setResponseNoContent();
	}
}
