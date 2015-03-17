#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.uuid;

import org.restexpress.plugin.hyperexpress.Linkable;

import com.strategicgains.repoexpress.domain.AbstractUuidEntity;

public class SampleUuidEntity
extends AbstractUuidEntity
implements Linkable
{
	public SampleUuidEntity()
	{
	}
}
