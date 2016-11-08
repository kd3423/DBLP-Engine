import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import java.io.*;
public class XmlHandlerAuthor{
	private ArrayList<String> author = new ArrayList<>();
	
	public void find(String str){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = factory.new SAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				boolean checkCat = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						// setCategory(qname);
						checkCat = true;
					}
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkCat){
						
					}
					else if(checkString){
						if(str.equals(new String(chArray,start,length))){
							checkVal = true;
						}
					}
				}
				public void endElement(String uri,String localName,String qname,Attributes att){
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setCategory(String cat){
		category = cat;
	}
	public void setEe(String eE){
		ee = eE;
	}
	public void setPages(String pag){
		pages = pag;
	}
	public void setYear(int y){
		year = y;
	}
	public void setUrl(String ur){
		url = ur;
	}
	public void setTitle(String t){
		title = t;
	}
	public void setAuthor(String auth){
		author = auth;
	}
	public void setVolume(String vol){
		volume = vol;
	}
	public void setPub(int x){
		publication = x;
	}