#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.strategicgains.hyperexpress.domain.Link;
import com.strategicgains.hyperexpress.domain.Linkable;
import com.strategicgains.repoexpress.mongodb.AbstractUuidMongodbEntity;

/**
 * Base MongoDB entity, identified by a UUID and can contain a list of Link elements.
 */
public abstract class AbstractLinkableEntity
extends AbstractUuidMongodbEntity
implements Linkable
{
	private List<Link> links;

	@Override
	public List<Link> getLinks()
	{
		return Collections.unmodifiableList(links);
	}

	@Override
	public void setLinks(List<Link> links)
	{
		this.links = new ArrayList<Link>(links);
	}
	
	@Override
	public void addLink(Link link)
	{
		if (links == null)
		{
			links = new ArrayList<Link>();
		}

		links.add(new Link(link));
	}
	
	@Override
	public void addAllLinks(Collection<Link> links)
	{
		for (Link link : links)
		{
			addLink(link);
		}
	}
}
