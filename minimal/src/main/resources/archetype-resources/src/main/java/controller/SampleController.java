#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import org.restexpress.Request;
import org.restexpress.Response;

public class SampleController
{
	public SampleController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'POST' logic here...
		return null;
	}

	public Object read(Request request, Response response)
	{
		//TODO: Your 'GET' logic here...
		return null;
	}

	public void update(Request request, Response response)
	{
		//TODO: Your 'PUT' logic here...
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		//TODO: Your 'DELETE' logic here...
		response.setResponseNoContent();
	}
}
