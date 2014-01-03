#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.util.List;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.common.query.QueryFilter;
import org.restexpress.common.query.QueryOrder;
import org.restexpress.common.query.QueryRange;
import org.restexpress.exception.BadRequestException;
import org.restexpress.query.QueryFilters;
import org.restexpress.query.QueryOrders;
import org.restexpress.query.QueryRanges;

import com.strategicgains.hyperexpress.RelTypes;
import com.strategicgains.hyperexpress.domain.Link;
import com.strategicgains.hyperexpress.domain.LinkableCollection;
import com.strategicgains.hyperexpress.util.LinkUtils;
import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.repoexpress.mongodb.MongodbUuidEntityRepository;
import ${package}.Constants;
import ${package}.domain.Sample;
import com.strategicgains.syntaxe.ValidationEngine;

public class SampleController
{
	private MongodbUuidEntityRepository<Sample> samples;
	
	public SampleController(MongodbUuidEntityRepository<Sample> orderRepository)
	{
		super();
		this.samples = orderRepository;
	}

	public Sample create(Request request, Response response)
	{
		Sample order = request.getBodyAs(Sample.class, "Sample details not provided");
		ValidationEngine.validateAndThrow(order);
		Sample saved = samples.create(order);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_SAMPLE);
		response.addLocationHeader(LinkUtils.formatUrl(locationPattern, Constants.Url.SAMPLE_ID, saved.getId().primaryKey().toString()));

		// Return the newly-created ID...
		return saved;
	}

	public Sample read(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.SAMPLE_ID, "No Sample ID supplied");
		Sample sample = samples.read(new Identifier(id));
		
		// Add 'self' link
		String selfPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_SAMPLE);
		String selfUrl = LinkUtils.formatUrl(selfPattern, Constants.Url.SAMPLE_ID, sample.getId().primaryKey().toString());
		sample.addLink(new Link(RelTypes.SELF, selfUrl));

		return sample;
	}

	public LinkableCollection<Sample> readAll(Request request, Response response)
	{
		QueryFilter filter = QueryFilters.parseFrom(request);
		QueryOrder order = QueryOrders.parseFrom(request);
		QueryRange range = QueryRanges.parseFrom(request, 20);
		List<Sample> results = samples.readAll(filter, range, order);
		long count = samples.count(filter);
		response.setCollectionResponse(range, results.size(), count);
		
		// Add 'self' links
		String sampleSelfPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_SAMPLE);

		for (Sample result : results)
		{
			String selfUrl = LinkUtils.formatUrl(sampleSelfPattern, Constants.Url.SAMPLE_ID, result.getId().primaryKey().toString());
			result.addLink(new Link(RelTypes.SELF, selfUrl));
		}

		String selfUrl = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SAMPLE_COLLECTION);
		LinkableCollection<Sample> wrapper = new LinkableCollection<Sample>(results);
		wrapper.addLink(new Link(RelTypes.SELF, selfUrl));
		return wrapper;
	}

	public void update(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.SAMPLE_ID);
		Sample sample = request.getBodyAs(Sample.class, "Sample details not provided");
		
		if (!id.equals(sample.getId()))
		{
			throw new BadRequestException("ID in URL and ID in Sample must match");
		}

		ValidationEngine.validateAndThrow(sample);
		samples.update(sample);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.SAMPLE_ID, "No Sample ID supplied");
		samples.delete(new Identifier(id));
		response.setResponseNoContent();
	}
}
