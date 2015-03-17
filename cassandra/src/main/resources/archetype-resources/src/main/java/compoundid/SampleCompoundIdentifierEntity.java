#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.compoundid;

import org.restexpress.plugin.hyperexpress.Linkable;

import com.strategicgains.repoexpress.domain.AbstractTimestampedIdentifiable;
import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.annotation.Required;

public class SampleCompoundIdentifierEntity
extends AbstractTimestampedIdentifiable
implements Linkable
{
	@Required
	private String key1;

	@Required
	private String key2;

	@Required
	private String key3;

	public SampleCompoundIdentifierEntity()
	{
	}

	@Override
	public Identifier getId()
	{
		return new Identifier(key1, key2, key3);
	}

	@Override
	public void setId(Identifier id)
	{
		// right now, we're ignoring this as the keys are set via their own setters.
		// or do you want this to call the individual setters?
	}

	public String getKey1()
	{
		return key1;
	}

	public void setKey1(String key1)
	{
		this.key1 = key1;
	}

	public String getKey2()
	{
		return key2;
	}

	public void setKey2(String key2)
	{
		this.key2 = key2;
	}

	public String getKey3()
	{
		return key3;
	}

	public void setKey3(String key3)
	{
		this.key3 = key3;
	}
}
