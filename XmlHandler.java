import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
public class XmlHandler extends DefaultHandler{
	public void startElement(String uri, String localName, String qname, Attributes attributes ) throws SAXException{
		try{
			System.out.println("start of : " + qname);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void character(char [] chArray , int start , int length){
		System.out.println(new String(chArray,start,length));
	}
	public void endElement(String uri, String localName, String qname)throws SAXException{
		try{
			System.out.println("end of : " + qname);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}