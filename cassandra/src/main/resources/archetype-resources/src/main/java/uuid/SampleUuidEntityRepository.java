#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.uuid;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.strategicgains.repoexpress.cassandra.CassandraUuidTimestampedEntityRepository;

public class SampleUuidEntityRepository
extends CassandraUuidTimestampedEntityRepository<SampleUuidEntity>
{
	private static final String UPDATE_CQL = "update %s set updatedat = ? where %s = ?";
	private static final String CREATE_CQL = "insert into %s (%s, createdat, updatedat) values (?, ?, ?)";

	private PreparedStatement createStmt;
	private PreparedStatement updateStmt;

	public SampleUuidEntityRepository(Session session)
    {
	    super(session, "sampleuuidentities", "id");
	    initializeStatements();
    }

	protected void initializeStatements()
	{
		createStmt = getSession().prepare(String.format(CREATE_CQL, getTable(), getIdentifierColumn()));
		updateStmt = getSession().prepare(String.format(UPDATE_CQL, getTable(), getIdentifierColumn()));
	}

	@Override
    protected SampleUuidEntity createEntity(SampleUuidEntity entity)
    {
		BoundStatement bs = new BoundStatement(createStmt);
		bindCreate(bs, entity);
		getSession().execute(bs);
		return entity;
    }

	@Override
    protected SampleUuidEntity updateEntity(SampleUuidEntity entity)
    {
		BoundStatement bs = new BoundStatement(updateStmt);
		bindUpdate(bs, entity);
		getSession().execute(bs);
		return entity;
    }

	private void bindCreate(BoundStatement bs, SampleUuidEntity entity)
	{
		bs.bind(entity.getUuid(),
		    entity.getCreatedAt(),
		    entity.getUpdatedAt());
	}

	private void bindUpdate(BoundStatement bs, SampleUuidEntity entity)
	{
		bs.bind(entity.getUpdatedAt(),
		    entity.getUuid());
	}

	@Override
    protected SampleUuidEntity marshalRow(Row row)
    {
		if (row == null) return null;

		SampleUuidEntity s = new SampleUuidEntity();
		s.setUuid(row.getUUID(getIdentifierColumn()));
		s.setCreatedAt(row.getTimestamp("createdat"));
		s.setUpdatedAt(row.getTimestamp("updatedat"));
		return s;
    }
}
