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
				ArrayList<String> temp = new ArrayList<>();
				boolean checkCat = false,checkAuth = false,checkString = false,checkTitle = false,check = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = true;
						checkString = false;
						checkTitle = false;
					}
					else if(qname.equals("author") && checkCat){
						checkAuth = true;
					}
					else if(qname.equals("title") && checkCat){
						checkTitle = true;
					}
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkCat && checkAuth){
						String auth = new String(chArray,start,length);
						temp.add(auth);
						if(str.equalsIgnoreCase(auth)){
							checkString = true;
						}

					}
					else if(checkTitle){
						if((new String(chArray,start,length)).equals("Home Page")){
							check = true;
						}
					}
				}
				public void endElement(String uri,String localName,String qname)throws SAXException{
					if(qname.equals("author")){
						checkAuth = false;
					}
					else if(qname.equals("www")){
						if(checkString && check){
							author.addAll(temp);
							check = false;
						}
						else if(checkString == false || check == false){
							temp.clear();
							check = false;
						}
						check = false;
						checkCat = false;
					}
				}
			};
			saxTheFile.parse("/home/karan/Desktop/Java/Xml/dblp.xml",defHandler);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public ArrayList<String> getAuth(){
		return author;
	}
}