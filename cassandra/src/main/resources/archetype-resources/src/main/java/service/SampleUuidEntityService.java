#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.domain.SampleUuidEntity;
import ${package}.persistence.SampleUuidEntityRepository;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.ValidationEngine;

public class SampleUuidEntityService
{
	private SampleUuidEntityRepository samples;
	
	public SampleUuidEntityService(SampleUuidEntityRepository samplesRepository)
	{
		super();
		this.samples = samplesRepository;
	}

	public SampleUuidEntity create(SampleUuidEntity entity)
	{
		ValidationEngine.validateAndThrow(entity);
		return samples.create(entity);
	}

	public SampleUuidEntity read(Identifier id)
    {
		return samples.read(id);
    }

	public void update(SampleUuidEntity entity)
    {
		ValidationEngine.validateAndThrow(entity);
		samples.update(entity);
    }

	public void delete(Identifier id)
    {
		samples.delete(id);
    }
}
