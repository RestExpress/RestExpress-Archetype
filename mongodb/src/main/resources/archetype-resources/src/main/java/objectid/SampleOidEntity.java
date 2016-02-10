#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.objectid;

import org.restexpress.plugin.hyperexpress.Linkable;
import ${package}.Constants;

import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.hyperexpress.annotation.TokenBindings;
import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;

/**
 * This is a sample entity identified by a MongoDB ObjectID (instead of a UUID).
 * It also contains createdAt and updatedAt properties that are automatically maintained
 * by the persistence layer (SampleOidEntityRepository).
 */
@TokenBindings({
	@BindToken(value=Constants.Url.SAMPLE_ID, field="id")
})
public class SampleOidEntity
extends AbstractMongodbEntity
implements Linkable
{
	public SampleOidEntity()
	{
	}
}
