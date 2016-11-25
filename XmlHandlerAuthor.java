import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerAuthor implements Runnable{
	private ArrayList<String> author = new ArrayList<>();	
	public void findAuth(){
		try{
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				ArrayList<String> temp = new ArrayList<>();
				int counter = 0;
				String snum;
				boolean checkCat = false,checkAuth = false,checkString = true,checkTitle = false,check = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = true;
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
							counter = counter + 1;
							snum = Integer.toString(counter);
							writer(snum,author);
							check = false;
							author.clear();
							temp.clear();
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
			saxTheFile.parse("dblp.xml",defHandler);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void writer(String snum,ArrayList<String> author){
		try{
			String x="";
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "author.txt",true) ) );
			for(String e: author){
				x = x + "#"+e;
			}
			write.println(x);
			write.flush();
			write.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<String> getAuth(){
		return author;
	}
	@Override
	public void run() {
	findAuth();		
	}
}