import java.io.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.util.*;
/* 	XMLReader xmlobj = XMLReader.Factory.createXMLReader();
	xmlobj.setContentHandler(new XmlHandler());
	xmlobj.parse("dblp.xml");
*/
class Cmain{
	public static void main(String [] args){
		try{
			boolean empt = false;
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			XmlHandlerAuthor obj = new XmlHandlerAuthor();
			obj.findAuth("Nathan Goodman");
			ArrayList<String> auth = new ArrayList<>();
			auth = obj.getAuth();
			if(auth.isEmpty()){
				empt = true;
			}
			else{
				for(String x: auth){
					System.out.println(x);
				}	
			}
			if(empt){
				System.out.println("No such author found");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}