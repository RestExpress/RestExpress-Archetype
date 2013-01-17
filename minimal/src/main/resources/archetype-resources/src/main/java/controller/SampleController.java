#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;

public class SampleController
{
	public SampleController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'create' logic here...
		return null;
	}

	public Object read(Request request, Response response)
	{
		//TODO: Your 'read' logic here...
		return null;
	}

//	public LinkableCollection<Object> readAll(Request request, Response response)
//	{
//		//TODO: Your 'readAll' logic here...
//		return null;
//	}

	public void update(Request request, Response response)
	{
		//TODO: Your 'update' logic here...
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		//TODO: Your 'delete' logic here...
		response.setResponseNoContent();
	}
}
