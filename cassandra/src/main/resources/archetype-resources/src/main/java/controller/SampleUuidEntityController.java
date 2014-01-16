#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.exception.BadRequestException;
import ${package}.Constants;
import ${package}.domain.SampleUuidEntity;
import ${package}.service.SampleUuidEntityService;

import com.strategicgains.hyperexpress.UrlBuilder;
import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.repoexpress.util.UuidConverter;
import com.strategicgains.syntaxe.ValidationEngine;

public class SampleUuidEntityController
{
	private SampleUuidEntityService service;
	
	public SampleUuidEntityController(SampleUuidEntityService sampleService)
	{
		super();
		this.service = sampleService;
	}

	public SampleUuidEntity create(Request request, Response response)
	{
		SampleUuidEntity sample = request.getBodyAs(SampleUuidEntity.class, "Resource details not provided");
		ValidationEngine.validateAndThrow(sample);
		SampleUuidEntity saved = service.create(sample);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_UUID_SAMPLE);
		response.addLocationHeader(new UrlBuilder(locationPattern)
			.param(Constants.Url.UUID, saved.getUuid().toString())
			.build());

		// Return the newly-created resource...
		return saved;
	}

	public SampleUuidEntity read(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		SampleUuidEntity sample = service.read(new Identifier(UuidConverter.parse(id)));
		addSelfLink(request, sample);
		return sample;
	}

	public void update(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		SampleUuidEntity sample = request.getBodyAs(SampleUuidEntity.class, "Resource details not provided");
		
		if (!UuidConverter.parse(id).equals(sample.getUuid()))
		{
			throw new BadRequestException("ID in URL and ID in resource body must match");
		}

		service.update(sample);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		service.delete(new Identifier(UuidConverter.parse(id)));
		response.setResponseNoContent();
	}

	private void addSelfLink(Request request, SampleUuidEntity sample)
    {
//	    String selfPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_SAMPLE);
//		LinkDefinition selfLink = new HalLinkBuilder(selfPattern)
//			.urlParam(Constants.Url.UUID, sample.getUuid().toString())
//			.build();
//		sample.linkTo(RelTypes.SELF, selfLink);
    }
}
