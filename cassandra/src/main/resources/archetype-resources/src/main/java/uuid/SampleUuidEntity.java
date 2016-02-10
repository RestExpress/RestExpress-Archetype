#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.uuid;

import org.restexpress.plugin.hyperexpress.Linkable;
import ${package}.Constants;
import ${package}.serialization.UuidFormatter;

import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.hyperexpress.annotation.TokenBindings;
import com.strategicgains.repoexpress.domain.AbstractUuidEntity;

@TokenBindings({
	@BindToken(value=Constants.Url.UUID, field="id", formatter=UuidFormatter.class)
})
public class SampleUuidEntity
extends AbstractUuidEntity
implements Linkable
{
	public SampleUuidEntity()
	{
	}
}
