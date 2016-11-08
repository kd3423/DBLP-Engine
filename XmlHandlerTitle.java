import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import java.io.*;
/* 	XMLReader xmlobj = XMLReader.Factory.createXMLReader();
	xmlobj.setContentHandler(new XmlHandler());
	xmlobj.parse("dblp.xml");
*/
public class XmlHandlerTitle{
	private ArrayList<String> title = new ArrayList<>();
	
	public void findTitle(ArrayList<String> auth){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = factory.new SAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				// boolean checkAuth = false;
				// boolean checkVal = false;
				// boolean checkCat = false;
				// boolean checkEe = false;
				// boolean checkUrl = false;
				// boolean checkPages = false;
				// boolean checkVolume = false;
				// boolean checkNumber = false;
				// boolean checkYear = false;
				// boolean checkTitle = false;
				boolean checkAuth = false;
				boolean check = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("author")){
						// setCategory(qname);
						check = true;
					}

				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(check){
						for(String x : auth){
							if(x.equals(new String(chArray,start,length))){
								//do things
							}
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
