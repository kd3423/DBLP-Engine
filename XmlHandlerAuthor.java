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
				boolean checkTitle = false;
				int counter =0,xx=0;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = true;
					}
					else if(qname.equals("author") && checkCat){
						checkAuth = true;
					}
					else if(qname.equals("title") && checkCat){
						checkTitle = true;
						// checkAuth = false;
					}
					// else{
					// 	checkCat = false;
					// 	checkAuth = false;
					// 	// checkString = false;
					// 	checkTitle = false;
					// }
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkCat && checkAuth){
						author.add(new String(chArray,start,length));
						counter++;
						// System.out.println("add");
						// if(str.equals(new String(chArray,start,length))){
						// 	checkString = true;
						// 	// System.out.println("Found!!");
						// 	author.add(new String(chArray,start,length));
						// }
						// else if(checkString){
						// 	author.add(new String(chArray,start,length));
						// }
						if(str.equals(new String(chArray,start,length))){
							checkString = true;
							System.out.println("same");
						}

					}
					else if(checkTitle == true){
						if(!(new String(chArray,start,length)).equals("Home Page")){
							checkTitle = false;
						}
					}
				}
				public void endElement(String uri,String localName,String qname){
					if(qname.equals("author")){
						checkAuth = false;
					}
					else if(qname.equals("www")){
						if(checkString == false || checkTitle == false){
							// System.out.println(author);
							if(xx < author.size()){
								author.subList(author.size()-(counter-1),author.size()).clear();
								xx = author.size();
							}
							// System.out.println("gfmdl");
							// System.out.println(author);
							// System.out.println("Clear ArrayList due to string");
						}
						counter = 0;
						checkCat = false;
						checkString = false;
						checkTitle = false;
						// System.out.println("dfsnk");
					}
					// System.out.println("Bye");
					// else if(qname.equals("title")){
					// 	if(checkTitle == false){
					// 		author.clear();
					// 	}
					// }
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