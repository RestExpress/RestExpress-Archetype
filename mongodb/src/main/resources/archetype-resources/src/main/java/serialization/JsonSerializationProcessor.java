#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.serialization;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.gson.GsonBuilder;
import com.strategicgains.restexpress.serialization.json.DefaultJsonProcessor;
import com.strategicgains.restexpress.serialization.json.GsonTimestampSerializer;
import com.strategicgains.util.date.DateAdapterConstants;

public class JsonSerializationProcessor
extends DefaultJsonProcessor
{

	public JsonSerializationProcessor()
    {
	    super(
	    	new GsonBuilder()
			.disableHtmlEscaping()
			.registerTypeAdapter(Date.class, new GsonTimestampSerializer())
			.registerTypeAdapter(ObjectId.class, new GsonObjectIdSerializer())
			.setDateFormat(DateAdapterConstants.TIMESTAMP_OUTPUT_FORMAT)
			.create()
	    );
    }
}
