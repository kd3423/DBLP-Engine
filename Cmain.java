import java.io.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
/* 	XMLReader xmlobj = XMLReader.Factory.createXMLReader();
	xmlobj.setContentHandler(new XmlHandler());
	xmlobj.parse("dblp.xml");
*/
class Cmain{
	public static void main(String [] args){
		try{
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			XMLReader xmlobj = XMLReaderFactory.createXMLReader();
			xmlobj.setContentHandler(new XmlHandler());
			// xmlobj.search("Sanjeev Saxena");
			xmlobj.parse("dblp.xml");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}