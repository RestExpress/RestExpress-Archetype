#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.util.List;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.common.query.QueryRange;
import org.restexpress.exception.BadRequestException;
import org.restexpress.query.QueryRanges;
import ${package}.Constants;
import ${package}.domain.SampleCompoundIdentifierEntity;
import ${package}.service.SampleCompoundIdentifierEntityService;

import com.strategicgains.hyperexpress.UrlBuilder;
import com.strategicgains.repoexpress.domain.Identifier;

/**
 * This is the 'controller' layer, where HTTP details are converted to domain concepts and passed to the service layer.
 * Then service layer response information is enhanced with HTTP details, if applicable, for the response.
 * <p/>
 * This controller demonstrates how to process a Cassandra entity that has a compound row key, instead of one that has
 * a single, primary row key such as a UUID.
 */
public class SampleCompoundIdentifierEntityController
{
	private SampleCompoundIdentifierEntityService service;
	
	public SampleCompoundIdentifierEntityController(SampleCompoundIdentifierEntityService service)
	{
		super();
		this.service = service;
	}

	public SampleCompoundIdentifierEntity create(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		SampleCompoundIdentifierEntity toCreate = request.getBodyAs(SampleCompoundIdentifierEntity.class, "Resource details not provided");

		if (!key1.equals(toCreate.getKey1()))
		{
			throw new BadRequestException("Key1 in URL and Key1 in resource body must match");
		}

		if (!key2.equals(toCreate.getKey2()))
		{
			throw new BadRequestException("Key2 in URL and Key2 in resource body must match");
		}

		SampleCompoundIdentifierEntity saved = service.create(toCreate);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_COMPOUND_SAMPLE);
		response.addLocationHeader(new UrlBuilder(locationPattern)
			.param(Constants.Url.KEY1, saved.getKey1())
			.param(Constants.Url.KEY2, saved.getKey2())
			.param(Constants.Url.KEY3, saved.getKey3())
			.build());

		// enrich the resource with links, etc. here...

		// Return the newly-created resource...
		return saved;
	}

	public SampleCompoundIdentifierEntity read(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		String key3 = request.getHeader(Constants.Url.KEY3, "Key3 not provided");
		SampleCompoundIdentifierEntity entity = service.read(new Identifier(key1, key2, key3));

		// enrich the resource with links, etc. here...

		return entity;
	}

	public List<SampleCompoundIdentifierEntity> readAll(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		QueryRange range = QueryRanges.parseFrom(request);
		List<SampleCompoundIdentifierEntity> entities = service.readAll(key1, key2);
		long count = service.count(key1, key2);
		response.setCollectionResponse(range, entities.size(), count);

		// enrich the results collection with links, etc. here...

		return entities;
	}

	public void update(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		String key3 = request.getHeader(Constants.Url.KEY3, "Key3 not provided");
		SampleCompoundIdentifierEntity toUpdate = request.getBodyAs(SampleCompoundIdentifierEntity.class, "Resource details not provided");

		if (!toUpdate.getId().equals(new Identifier(key1, key2, key3)))
		{
			throw new BadRequestException("ID in URL and ID in resource body must match");
		}

		service.update(toUpdate);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		String key3 = request.getHeader(Constants.Url.KEY3, "Key3 not provided");
		service.delete(new Identifier(key1, key2, key3));
		response.setResponseNoContent();
	}
}
