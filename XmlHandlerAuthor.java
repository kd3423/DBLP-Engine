import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerAuthor{
	private ArrayList<String> author = new ArrayList<>();	
	public void findAuth(String str){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				boolean checkCat = false;
				boolean checkAuth = false;
				boolean checkString = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						// setCategory(qname);
						checkCat = true;
					}
					else if(qname.equals("author")){
						checkAuth = true;
					}
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkCat){
						if(checkAuth){
							if(str.equals(new String(chArray,start,length))){
								checkString = true;
							}
							else if(checkString){
								author.add(new String(chArray,start,length));
							}
						}
					}
					
				}
				public void endElement(String uri,String localName,String qname,Attributes att){
					if(qname.equals("www")){
						checkCat = false;
						checkString = false;
					}
					else if(qname.equals("author")){
						checkAuth = false;
					}
				}
			};
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public ArrayList<String> getAuth(){
		return author;
	}
}	