import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandler implements Runnable{
	String join;
	private ArrayList<String> author = new ArrayList<>();	
	public volatile int working;
	public void findAuth(){
		try{
			working = 0;
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				ArrayList<String> temp = new ArrayList<>();
				int counter = 0;
				String snum;
				boolean checkCat = false,checkAuth = false,checkString = true,checkTitle = false,check = false;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("author")){
						checkAuth = true;
						join="";
					}
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkAuth){
						String auth = new String(chArray,start,length);
						join = join + auth;
					}
				}
				public void endElement(String uri,String localName,String qname)throws SAXException{
					if(qname.equals("author")){
						checkAuth = false;
						writer(join);
					}
				}
			};
			saxTheFile.parse("dblp.xml",defHandler);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			working = 1;
		}
	}
	private void writer(String author){
		try{
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "author1.txt",true) ) );
			write.println(author);
			write.flush();
			write.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		findAuth();		
	}
}