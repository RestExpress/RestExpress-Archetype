#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import com.strategicgains.repoexpress.mongodb.AbstractUuidMongodbEntity;

/**
 * This is a sample entity identified by a UUID (instead of a MongoDB ObjectID).
 * It also contains createdAt and updatedAt properties that are automatically maintained
 * by the persistence layer (SampleUuidEntityRepository).
 */
public class SampleUuidEntity
extends AbstractUuidMongodbEntity
{
	public SampleUuidEntity()
	{
	}
}
