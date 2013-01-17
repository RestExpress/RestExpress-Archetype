#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.serialization;

import com.strategicgains.hyperexpress.domain.Link;
import com.strategicgains.hyperexpress.domain.LinkableCollection;
import com.strategicgains.restexpress.serialization.xml.DefaultXmlProcessor;

/**
 * Specifies the XML-element-name-to-object type mapping.  The element name is what exists in the XML
 * input/output, mapping that to a domain object or DTO within the project.  This facilitates the
 * XML to domain and domain to XML serialization.  Otherwise, the XML serializer will use the full
 * object name (e.g. java.util.ArrayList).
 */
public class XmlSerializationProcessor
extends DefaultXmlProcessor
{
	public XmlSerializationProcessor()
    {
	    super();
		alias("link", Link.class);
		alias("collection", LinkableCollection.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
    }
}
