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
	private ArrayList<String> ee = new ArrayList<>();
	private ArrayList<String> pages = new ArrayList<>();
	private ArrayList<Integer> year = new ArrayList<>();
	private ArrayList<String> url = new ArrayList<>();
	private ArrayList<String> volume = new ArrayList<>();
	private ArrayList<String> publication = new ArrayList<>(); 	
	public void findTitle(ArrayList<String> auth){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = factory.new SAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				
				boolean titleCheck = false;
				boolean eeCheck = false;
				boolean pubCheck = false;
				boolean volCheck = false;
				boolean yearCheck = false;
				boolean urlCheck = false;
				boolean checkAuth = false;
				boolean check = false;
				boolean checkforall = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("author")){
						checkAuth = true;
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
							title.add(new String(chArray,start,length)));
						}
						else if(eeCheck){
							ee.add(new String(chArray,start,length)));
						}
						else if(urlCheck){
							url.add(new String(chArray,start,length)));
						}
						else if(volCheck){
							volume.add(new String(chArray,start,length)));
						}
						else if(yearCheck){
							title.add(new String(chArray,start,length)));
						}
						else if(pagesCheck){
							pages.add(new String(chArray,start,length)));
						}
						else if(pubCheck){
							title.add(new String(chArray,start,length)));
						}
					}

				}
				public void endElement(String uri,String localName,String qname,Attributes att){
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
