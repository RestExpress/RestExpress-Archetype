#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.serialization;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.strategicgains.restexpress.serialization.json.DefaultJsonProcessor;

public class JsonSerializationProcessor
extends DefaultJsonProcessor
{
	@Override
    protected void initializeModule(SimpleModule module)
    {
	    super.initializeModule(module);
	    
	    //TODO: add your own [de]serializers here.
	    //module.addDeserializer(type, deser);
    }
}
