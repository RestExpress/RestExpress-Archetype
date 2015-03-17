#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.compoundid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.strategicgains.repoexpress.cassandra.AbstractCassandraRepository;
import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.repoexpress.event.DefaultTimestampedIdentifiableRepositoryObserver;

public class SampleCompoundIdentifierEntityRepository
extends AbstractCassandraRepository<SampleCompoundIdentifierEntity>
{
	private static final String IDENTITY_CQL = " where key1 = ? and key2 = ? and key3 = ?";
	private static final String EXISTENCE_CQL = "select count(*) from %s" + IDENTITY_CQL;
	private static final String READ_CQL = "select * from %s" + IDENTITY_CQL;
	private static final String DELETE_CQL = "delete from %s" + IDENTITY_CQL;
	private static final String UPDATE_CQL = "update %s set updatedat = ?" + IDENTITY_CQL;
	private static final String CREATE_CQL = "insert into %s (key1, key2, key3, createdat, updatedat) values (?, ?, ?, ?, ?)";
	private static final String READ_ALL_CQL = "select * from %s where key1 = ? and key2 = ?";
	private static final String READ_ALL_COUNT_CQL = "select count(*) from %s where key1 = ? and key2 = ?";

	private PreparedStatement existStmt;
	private PreparedStatement readStmt;
	private PreparedStatement createStmt;
	private PreparedStatement deleteStmt;
	private PreparedStatement updateStmt;
	private PreparedStatement readAllStmt;
	private PreparedStatement readAllCountStmt;

	public SampleCompoundIdentifierEntityRepository(Session session)
	{
		super(session, "samplecompoundidentities");
		addObserver(new DefaultTimestampedIdentifiableRepositoryObserver<SampleCompoundIdentifierEntity>());
		initialize();
	}

	protected void initialize()
	{
		existStmt = getSession().prepare(String.format(EXISTENCE_CQL, getTable()));
		readStmt = getSession().prepare(String.format(READ_CQL, getTable()));
		createStmt = getSession().prepare(String.format(CREATE_CQL, getTable()));
		deleteStmt = getSession().prepare(String.format(DELETE_CQL, getTable()));
		updateStmt = getSession().prepare(String.format(UPDATE_CQL, getTable()));
		readAllStmt = getSession().prepare(String.format(READ_ALL_CQL, getTable()));
		readAllCountStmt = getSession().prepare(String.format(READ_ALL_COUNT_CQL, getTable()));
	}

	@Override
	public boolean exists(Identifier identifier)
	{
		if (identifier == null || identifier.isEmpty()) return false;

		BoundStatement bs = new BoundStatement(existStmt);
		bindIdentifier(bs, identifier);
		return (getSession().execute(bs).one().getLong(0) > 0);
	}

	protected SampleCompoundIdentifierEntity readEntityById(Identifier identifier)
	{
		if (identifier == null || identifier.isEmpty()) return null;

		BoundStatement bs = new BoundStatement(readStmt);
		bindIdentifier(bs, identifier);
		return marshalRow(getSession().execute(bs).one());
	}

	@Override
	protected SampleCompoundIdentifierEntity createEntity(SampleCompoundIdentifierEntity entity)
	{
		BoundStatement bs = new BoundStatement(createStmt);
		bindCreate(bs, entity);
		getSession().execute(bs);
		return entity;
	}

	@Override
	protected SampleCompoundIdentifierEntity updateEntity(SampleCompoundIdentifierEntity entity)
	{
		BoundStatement bs = new BoundStatement(updateStmt);
		bindUpdate(bs, entity);
		getSession().execute(bs);
		return entity;
	}

	@Override
	protected void deleteEntity(SampleCompoundIdentifierEntity entity)
	{
		BoundStatement bs = new BoundStatement(deleteStmt);
		bindIdentifier(bs, entity.getId());
		getSession().execute(bs);
	}

	public List<SampleCompoundIdentifierEntity> readAll(String context, String nodeType)
	{
		BoundStatement bs = new BoundStatement(readAllStmt);
		bs.bind(context, nodeType);
		return (marshalAll(getSession().execute(bs)));
	}

	public long count(String context, String nodeType)
	{
		BoundStatement bs = new BoundStatement(readAllCountStmt);
		bs.bind(context, nodeType);
		return (getSession().execute(bs).one().getLong(0));
	}

	protected List<SampleCompoundIdentifierEntity> marshalAll(ResultSet rs)
	{
		List<SampleCompoundIdentifierEntity> results = new ArrayList<SampleCompoundIdentifierEntity>();
		Iterator<Row> i = rs.iterator();

		while (i.hasNext())
		{
			results.add(marshalRow(i.next()));
		}

		return results;
	}

	protected SampleCompoundIdentifierEntity marshalRow(Row row)
	{
		if (row == null) return null;

		SampleCompoundIdentifierEntity s = new SampleCompoundIdentifierEntity();
		s.setKey1(row.getString("key1"));
		s.setKey2(row.getString("key2"));
		s.setKey3(row.getString("key3"));
		s.setCreatedAt(row.getDate("createdat"));
		s.setUpdatedAt(row.getDate("updatedat"));
		return s;
	}

	private void bindCreate(BoundStatement bs, SampleCompoundIdentifierEntity entity)
	{
		bs.bind(entity.getKey1(),
			entity.getKey2(),
		    entity.getKey3(),
		    entity.getCreatedAt(),
		    entity.getUpdatedAt());
	}

	private void bindUpdate(BoundStatement bs, SampleCompoundIdentifierEntity entity)
	{
		bs.bind(entity.getUpdatedAt(),
		    entity.getKey1(),
		    entity.getKey2(),
		    entity.getKey3());
	}
}
