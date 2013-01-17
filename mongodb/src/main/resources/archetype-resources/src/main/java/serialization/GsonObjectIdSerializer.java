#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.serialization;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.strategicgains.restexpress.serialization.json.GsonSerializer;

/**
 * @author toddf
 * @since Feb 16, 2011
 */
public class GsonObjectIdSerializer
implements GsonSerializer<ObjectId>
{
    @Override
    public ObjectId deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context)
    throws JsonParseException
    {
	    return new ObjectId(json.getAsJsonPrimitive().getAsString());
    }

    @Override
    public JsonElement serialize(ObjectId id, Type typeOf, JsonSerializationContext context)
    {
    	return new JsonPrimitive(id.toString());
    }

    @Override
    public ObjectId createInstance(Type arg0)
    {
	    return new ObjectId();
    }
}
