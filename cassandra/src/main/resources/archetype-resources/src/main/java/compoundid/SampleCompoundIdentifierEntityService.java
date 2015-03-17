#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.compoundid;

import java.util.List;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.ValidationEngine;

/**
 * This is the 'service' or 'business logic' layer, where business logic, syntactic and semantic
 * domain validation occurs, along with calls to the persistence layer.
 */
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

	public SampleCompoundIdentifierEntity read(Identifier id)
    {
		return samples.read(id);
    }

	public void update(SampleCompoundIdentifierEntity definition)
    {
		ValidationEngine.validateAndThrow(definition);
		samples.update(definition);
    }

	public void delete(Identifier identifier)
    {
		samples.delete(identifier);
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
