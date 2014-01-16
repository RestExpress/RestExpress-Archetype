#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;

import ${package}.domain.SampleCompoundIdentifierEntity;
import ${package}.persistence.SampleCompoundIdentifierEntityRepository;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.ValidationEngine;

public class SampleCompoundIdentifierEntityService
{
	private SampleCompoundIdentifierEntityRepository samples;
	
	public SampleCompoundIdentifierEntityService(SampleCompoundIdentifierEntityRepository samplesRepository)
	{
		super();
		this.samples = samplesRepository;
	}

	public SampleCompoundIdentifierEntity create(SampleCompoundIdentifierEntity definition)
	{
		ValidationEngine.validateAndThrow(definition);
		return samples.create(definition);
	}

	public SampleCompoundIdentifierEntity read(String context, String nodeType, String relType)
    {
		return samples.read(new Identifier(context, nodeType, relType));
    }

	public void update(SampleCompoundIdentifierEntity definition)
    {
		ValidationEngine.validateAndThrow(definition);
		samples.update(definition);
    }

	public void delete(String context, String nodeType, String relType)
    {
		samples.delete(new Identifier(context, nodeType, relType));
    }

	public List<SampleCompoundIdentifierEntity> readAll(String context, String nodeType)
    {
	    return samples.readAll(context, nodeType);
    }

	public long count(String context, String nodeType)
    {
	    return samples.count(context, nodeType);
    }
}
