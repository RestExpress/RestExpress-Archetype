#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.serialization;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.restexpress.serialization.json.JacksonJsonProcessor;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonSerializationProcessor
extends JacksonJsonProcessor
{
	@Override
    protected void initializeModule(SimpleModule module)
    {
	    super.initializeModule(module);
	    // For UUID as entity identifiers...
	    module.addDeserializer(UUID.class, new UuidDeserializer());
	    module.addSerializer(UUID.class, new UuidSerializer());

	    // For MongoDB ObjectId as entity identifiers...
	    module.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
	    module.addSerializer(ObjectId.class, new ObjectIdSerializer());
    }
}
