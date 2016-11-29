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
	private HashMap<String,Integer> hash = new HashMap<String,Integer>();
	int k;
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
					if(qname.equals("www")){
						checkCat = false;
					}
					else if(qname.equals("article")){
						checkCat = true;
					}
					else if(qname.equals("author") && checkCat){
						checkAuth = true;
						join="";
					}
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkAuth && checkCat){
						String auth = new String(chArray,start,length);
						join = join + auth;
					}
				}
				public void endElement(String uri,String localName,String qname)throws SAXException{
					if(qname.equals("author") && checkCat){
						checkAuth = false;
						writer(join);
					}
					else if(qname.equals("article")){
						checkCat = false;
					}
				}
			};
			saxTheFile.parse("dblp.xml",defHandler);

			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ("author1.txt") ) );
			for(String e : hash.keySet()){
			write.println(hash.get(e) + "#" + e);
			write.flush();
			}
			
			write.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			working = 1;

		}
	}
	private void writer(String author){
		// try{
			//PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "author1.txt",true) ) );
			if(hash.containsKey(author)){
				k++;
				hash.put(author, k);
			}
			else{
				k = 1;
				hash.put(author, k);
			}
			
			//write.println(author);
			//write.flush();
			//write.close();
		// }
		// catch(IOException e)
		// {
		// 	e.printStackTrace();
		// }
	}
	@Override
	public void run() {
		findAuth();		
	}
}