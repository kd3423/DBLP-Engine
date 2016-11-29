import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerTitleForAuthor implements Runnable{
	private ArrayList<String> auth;
	private ArrayList<String> author = new ArrayList<>();	
	public volatile int working;
	private String join;
	public void fillFile(){
		try{
			working = 0;
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "Ref.txt") ) );
			write.close();
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
						for(String x : auth){
							if(x.equalsIgnoreCase(join)){
								checkforall = true;
							}
						}
					}
					else if(titleCheck && checkCat && checkforall){
						title = new String(chArray,start,length);
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
							writer(snum,author,title,url,year,pages,volume,journal);
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
						author.add(join);
					}
				}
			};
			saxTheFile.parse("dblp.xml",defHandler);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void writer(String snum, ArrayList<String> author,String title , String url,String year,String pages,String volume,String journal){
		try{
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "Ref.txt",true) ) );
			String z = "";
			for(String e : author){
				z = z + e;
				z = z + " | ";
			}
			write.print( snum +"~"+ z + "~" + title + "~" + pages + "~" + year + "~"+ volume+ "~"+journal+"~" + url + "\n");
			write.flush();
			write.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void sort(){
		try{
			List<ArrayList<String>> csvLines = new ArrayList<ArrayList<String>>();
			Comparator<ArrayList<String>> comp = new Comparator<ArrayList<String>>() {
			    public int compare(ArrayList<String> csvLine1, ArrayList<String> csvLine2) {
			    	int x = Integer.valueOf(csvLine1.get(4)).compareTo(Integer.valueOf(csvLine2.get(4)));
			        return -x;
			    }
			};
			BufferedReader read = new BufferedReader(new FileReader("Ref.txt"));
	    	String call;
			while((call = read.readLine())!= null){
			ArrayList<String> temp = new ArrayList<String>();
			String[] x = call.split("~");
			for(int i = 0;i<x.length;i++){
				temp.add(x[i]);
			}
			csvLines.add(temp);
			}
	    	read.close();
	    	Collections.sort(csvLines,comp);
	    	PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "Ref.txt") ) );
	    	for(int i = 0;i<csvLines.size();i++){
	    		write.print((i+1)+"~"+csvLines.get(i).get(1)+ "~" +csvLines.get(i).get(2)+ "~" +csvLines.get(i).get(3)+ "~" + csvLines.get(i).get(4) + "~"+ csvLines.get(i).get(5)+ "~" + csvLines.get(i).get(6)+ "~" + csvLines.get(i).get(7) +"\n");
	    		write.flush();
	    	}
			write.close();
		}	catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			working = 1;
		}
		}
public void setAuthor(ArrayList<String> z){
	auth = z;
}
@Override
public void run() {
	fillFile();
	sort();
}
}