import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandler implements Runnable{
	private ArrayList<String> auth;
	private ArrayList<String> author = new ArrayList<>();	
	public volatile int working;
	private String join,join1;
	private HashMap<String,Integer> hash = new HashMap<String,Integer>();
	private HashMap<String[],Integer> hash2 = new HashMap<String[],Integer>();
	int k;
	public void fillFile(){
		try{
			working = 0;
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				int counter = 0;
				String title,pages,url,volume,year,snum,journal;
				boolean titleCheck = false,volCheck = false,yearCheck = false,urlCheck = false,checkAuth = false,pagesCheck = false,checkforall = false,journalCheck = false,checkCat = true;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = false;
					}
					else if(qname.equals("author")){
						checkAuth = true;
						join = "";
					}
					else if(qname.equals("title")){
						titleCheck = true;
						join1 = "";
					}
					else if(qname.equals("year")){
						yearCheck = true;
					}
					else if(qname.equals("url")){
						urlCheck = true;
					}
					else if(qname.equals("pages")){
						pagesCheck = true;
					}
					else if(qname.equals("volume")){
						volCheck = true;
					}
					else if(qname.equals("journal") || qname.equals("booktitle")){
						journalCheck = true;
					}
				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkAuth && checkCat){
						String temp = new String(chArray,start,length); 
						join = join + temp;
						checkforall = true;
					}
					else if(titleCheck && checkCat && checkforall){
						title = new String(chArray,start,length);
						join1 = join1 + title;
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
					else if(journalCheck && checkCat && checkforall){
						journal = new String(chArray,start,length);
					}
				}
				public void endElement(String uri,String localName,String qname)throws SAXException{
					if(qname.equals("title")){
						titleCheck = false;
					}
					else if(qname.equals("url")){
						urlCheck = false;
						if(!checkforall){
							author.clear();
						}
						else{
							counter = counter + 1;
							snum = Integer.toString(counter);
							checkforall = false;				
						}
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
					else if(qname.equals("journal") || qname.equals("booktitle")){
						journalCheck = false;
					}
					else if(qname.equals("author")){
						checkAuth = false;
						writer(join);
					}
				}
			};
			saxTheFile.parse("dblp.xml",defHandler);
			fin();
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "author1.txt") ) );
			for(String[] e : hash2.keySet()){
				String k="";
				for(String ee : e){
					if(ee != ""){
						k = ee;
					}
				}
				write.println(hash2.get(e) + "#" + k);
				write.flush();
			}
			write.close();
			working = 1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void writer(String x){

		if(hash.containsKey(x)){
				k = hash.get(x);
				k++;
				hash.put(join, k);
		}
		else{
				k = 1;
				hash.put(join, k);
		}
	}
public void fin()throws IOException{
	hash.remove("");
	BufferedReader read = new BufferedReader(new FileReader("author.txt"));
	String call;
	while((call = read.readLine())!= null){
		String[] z = call.split("#");
		hash2.put(z,0);
	}
	for(String[] e : hash2.keySet()){
		int k = 0;
		for(String ee : e){
			if(hash.containsKey(ee)){
				k = k + hash.get(ee);
			}
		}
		hash2.put(e,k);
	}


}
@Override
public void run() {
	fillFile();
}
}