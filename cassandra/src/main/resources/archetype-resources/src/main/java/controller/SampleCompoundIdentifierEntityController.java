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

		// As an alternative to the above guard clause, you could just force the IDs for some use cases...
//		toCreate.setKey1(key1);
//		toCreate.setKey2(key2);
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

		// Return the newly-created resource...
		return saved;
	}

	public SampleCompoundIdentifierEntity read(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		String key3 = request.getHeader(Constants.Url.KEY3, "Key3 not provided");
		SampleCompoundIdentifierEntity sample = service.read(key1, key2, key3);

		addSelfLink(request, sample);

		// Add 'up' link
//		String upPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SAMPLE_COLLECTION);
//		LinkDefinition upLink = new HalLinkBuilder(upPattern)
//			.urlParam(Constants.Url.KEY1, key1)
//			.urlParam(Constants.Url.KEY2, key2)
//			.title("This resource's containing collection")
//			.build();
//		sample.linkTo(RelTypes.UP, upLink);

		return sample;
	}

	public List<SampleCompoundIdentifierEntity> readAll(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		QueryRange range = QueryRanges.parseFrom(request);
		List<SampleCompoundIdentifierEntity> samples = service.readAll(key1, key2);
		long count = service.count(key1, key2);
		response.setCollectionResponse(range, samples.size(), count);

		// Add 'self' link
//		String selfPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SAMPLE_COLLECTION);
//		LinkDefinition selfLink = new HalLinkBuilder(selfPattern)
//			.urlParam(Constants.Url.KEY1, key1)
//			.urlParam(Constants.Url.KEY2, key2)
//			.build();
//		HalResource resource = new HalResource();
//		resource.linkTo(RelTypes.SELF, selfLink);
//		String eachSelfPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_SAMPLE);
//
//		for (SampleCompoundIdentifierEntity result : samples)
//		{
//			LinkDefinition eachSelfLink = new HalLinkBuilder(eachSelfPattern)
//				.urlParam(Constants.Url.KEY1, result.getKey1())
//				.urlParam(Constants.Url.KEY2, result.getKey2())
//				.urlParam(Constants.Url.KEY3, result.getKey3())
//				.build();
//			result.linkTo(RelTypes.SELF, eachSelfLink);
//
//			resource.embed("samples", result);
//		}
//
//		return resource;
		return samples;
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

		// As an alternative to the above guard clause, you could just force the IDs for some use cases...
//		toUpdate.setKey1(key1);
//		toUpdate.setKey2(key2);
//		toUpdate.setKey3(key3);
		service.update(toUpdate);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String key1 = request.getHeader(Constants.Url.KEY1, "Key1 not provided");
		String key2 = request.getHeader(Constants.Url.KEY2, "Key2 not provided");
		String key3 = request.getHeader(Constants.Url.KEY3, "Key3 not provided");
		service.delete(key1, key2, key3);
		response.setResponseNoContent();
	}

	private void addSelfLink(Request request, SampleCompoundIdentifierEntity sample)
    {
//	    String selfPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_SAMPLE);
//		LinkDefinition selfLink = new HalLinkBuilder(selfPattern)
//			.urlParam(Constants.Url.KEY1, sample.getKey1())
//			.urlParam(Constants.Url.KEY2, sample.getKey2())
//			.urlParam(Constants.Url.KEY3, sample.getKey3())
//			.build();
//		sample.linkTo(RelTypes.SELF, selfLink);
    }
}
