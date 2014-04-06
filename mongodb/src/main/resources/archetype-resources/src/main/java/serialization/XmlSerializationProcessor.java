#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.serialization;

import ${package}.domain.SampleUuidEntity;
import org.restexpress.serialization.xml.XstreamXmlProcessor;

public class XmlSerializationProcessor
extends XstreamXmlProcessor
{
	public XmlSerializationProcessor()
    {
	    super();
	    alias("sample", SampleUuidEntity.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
		registerConverter(new XstreamUuidConverter());
		registerConverter(new XstreamOidConverter());
    }
}
