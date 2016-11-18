import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerTitleForAuthor{
	private ArrayList<String> author = new ArrayList<>();
	// private ArrayList<String> title = new ArrayList<>();
	// private ArrayList<String> ee = new ArrayList<>();
	// private ArrayList<String> pages = new ArrayList<>();
	// private ArrayList<Integer> year = new ArrayList<>();
	// private ArrayList<String> url = new ArrayList<>();
	// private ArrayList<String> volume = new ArrayList<>();
	// private ArrayList<String> publication = new ArrayList<>(); 	
	public void findTitle(ArrayList<String> auth){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				String title,pages,ee,url,volume,year,publication;
				boolean titleCheck = false,eeCheck = false;
				boolean pubCheck = false;
				boolean volCheck = false;
				boolean yearCheck = false;
				boolean urlCheck = false;
				boolean checkAuth = false;
				boolean check = false;
				boolean pagesCheck = false;
				boolean checkforall = false;
				boolean checkCat = true;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = false;
					}
					else if(qname.equals("author")){
						checkAuth = true;
					}

				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkAuth && checkCat){
						String temp = new String(chArray,start,length); 
						author.add(temp);
						for(String x : auth){
							if(x.equals(temp)){
								checkforall = true;
							}
						}
					}
					else if(titleCheck && checkCat && checkforall){
						title = new String(chArray,start,length);
					}
					else if(eeCheck && checkCat && checkforall){
						ee = new String(chArray,start,length);
					}
					else if(volCheck && checkCat && checkforall){
						volume = new String(chArray,start,length);
					}
					else if(pagesCheck && checkCat && checkforall){
						pages = new String(chArray,start,length);
					}
					else if(urlCheck && checkCat && checkforall){
						url = new String(chArray,start,length);
					}
					else if(yearCheck && checkCat && checkforall){
						year = new String(chArray,start,length);
					}
					else if(pubCheck && checkCat && checkforall){
						publication = new String(chArray,start,length);
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
	private void write(ArrayList<String> author,String title , String url,String ee,String year, String publication,String pages,String volume){
		try{
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "Ref.txt",true) ) );
			write.print(author + "," + title + "," + publication + "," + year + "," + pages + "," + ee + "," + url);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}