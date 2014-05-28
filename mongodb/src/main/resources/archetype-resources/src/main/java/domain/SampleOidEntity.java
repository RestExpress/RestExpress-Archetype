#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import org.restexpress.plugin.hyperexpress.Linkable;

import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;

/**
 * This is a sample entity identified by a MongoDB ObjectID (instead of a UUID).
 * It also contains createdAt and updatedAt properties that are automatically maintained
 * by the persistence layer (SampleOidEntityRepository).
 */
public class SampleOidEntity
extends AbstractMongodbEntity
implements Linkable
{
	public SampleOidEntity()
	{
	}
}
