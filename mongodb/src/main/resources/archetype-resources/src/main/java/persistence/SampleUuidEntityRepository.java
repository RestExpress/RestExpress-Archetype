#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence;

import ${package}.domain.SampleUuidEntity;

import com.mongodb.MongoClient;
import com.strategicgains.repoexpress.mongodb.MongodbUuidEntityRepository;

public class SampleUuidEntityRepository
extends MongodbUuidEntityRepository<SampleUuidEntity>
{
	@SuppressWarnings("unchecked")
    public SampleUuidEntityRepository(MongoClient mongo, String dbName)
	{
		super(mongo, dbName, SampleUuidEntity.class);
	}
}
