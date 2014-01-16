#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

public class Constants
{
	/**
	 * These define the URL parmaeters used in the route definition strings (e.g. '{userId}').
	 */
	public class Url
	{
		//TODO: Your URL parameter names here...
		public static final String UUID = "uuid";
		public static final String KEY1 = "key1";
		public static final String KEY2 = "key2";
		public static final String KEY3 = "key3";
	}

	/**
	 * These define the route names used in naming each route definitions.  These names are used
	 * to retrieve URL patterns within the controllers by name to create links in responses.
	 */
	public class Routes
	{
		//TODO: Your Route names here...
		public static final String SINGLE_UUID_SAMPLE = "sample.uuid.single.route";
		public static final String SINGLE_COMPOUND_SAMPLE = "sample.compound.single.route";
		public static final String SAMPLE_UUID_COLLECTION = "sample.uuid.collection.route";
		public static final String SAMPLE_COMPOUND_COLLECTION = "sample.compound.collection.route";
	}
}
