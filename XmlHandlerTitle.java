import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerTitle{
	// private ArrayList<String> title = new ArrayList<>();
	private String ee;
	private String pages;
	private Integer year;
	private String url;
	private String volume;
	private Integer publication;
	public void findTitle(String title){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("author")){
						checkAuth = true;
					}
					else if(qname.equals("title")){
						titleCheck = true;
					}
					else if(qname.equals("ee")){
						eeCheck = true;
					}
					else if(qname.equals("url")){
						urlCheck = true;
					}
					else if(qname.equals("publication")){
						pubCheck = true;
					}
					else if(qname.equals("year")){
						yearCheck = true;
					}
					else if(qname.equals("pages")){
						pagesCheck = true;
					}
					else if(qname.equals("volume")){
						volCheck = true;
					}

				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkAuth){
						for(String x : auth){
							if(x.equals(new String(chArray,start,length))){
								checkforall = true;
							}
						}
					}
					if(checkforall){
						if(titleCheck){
							title.add(new String(chArray,start,length));
						}
						else if(eeCheck){
							ee.add(new String(chArray,start,length));
						}
						else if(urlCheck){
							url.add(new String(chArray,start,length));
						}
						else if(volCheck){
							volume.add(new String(chArray,start,length));
						}
						else if(yearCheck){
							year.add(Integer.parseInt(new String(chArray,start,length)));
						}
						else if(pagesCheck){
							pages.add(new String(chArray,start,length));
						}
						else if(pubCheck){
							publication.add(new String(chArray,start,length));
						}
					}

				}
				public void endElement(String uri,String localName,String qname){
					if(qname.equals("title")){
						titleCheck = false;
					}
					else if(qname.equals("ee")){
						eeCheck = false;
					}
					else if(qname.equals("url")){
						urlCheck = false;
					}
					else if(qname.equals("publication")){
						pubCheck = false;
					}
					else if(qname.equals("year")){
						yearCheck = false;
					}
					else if(qname.equals("pages")){
						pagesCheck = false;
					}
					else if(qname.equals("volume")){
						volCheck = false;
					}
				}
			};
			saxTheFile.parse("/home/karan/Desktop/Java/Xml/dblp.xml",defHandler);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getEe(){
		return ee;
	}
	public String getPages(){
		return pages;
	}
	public Integer getYear(){
		return year;
	}
	public String getUrl(){
		return url;
	}
	public String getTitle(){
		return title;
	}
	public String getVolume(){
		return volume;
	}
	public Integer getPub(){
		return publication;
	}
}