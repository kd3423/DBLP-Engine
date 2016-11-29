import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerTitle implements Runnable{
	private ArrayList<String> author = new ArrayList<>();	
	private String str;
	private int type;
	private String join1,join2;
	public volatile int working;
	public XmlHandlerTitle(String x,int y){
		this.str = x;
		this.type = y;
	}
	public void findTitle(){
		try{
			working = 0;
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "Ref.txt") ) );
			write.close();
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				String title,pages,url,volume,year,snum,journal;
				int counter=0;
				boolean titleCheck = false,relcheck=false,volCheck = false,yearCheck = false,urlCheck = false,checkAuth = false,pagesCheck = false, journalCheck = false,checkCat = true;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = false;
					}
					else if(qname.equals("author") || qname.equals("editor")){
						checkAuth = true;
						join1 = "";
					}
					else if(qname.equals("title")){
						titleCheck = true;
						join2 = "";
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
						join1 = join1+temp;
					}
					else if(titleCheck && checkCat){
						title = new String(chArray,start,length);
						join2 = join2 + title;
					}
					else if(volCheck && checkCat){
						volume = new String(chArray,start,length);
					}
					else if(pagesCheck && checkCat){
						pages = new String(chArray,start,length);
					}
					else if(urlCheck && checkCat){
						url = new String(chArray,start,length);
					}
					else if(yearCheck && checkCat){
						year = new String(chArray,start,length);
					}
					else if(journalCheck && checkCat){
						journal = new String(chArray,start,length);
					}
				}
				public void endElement(String uri,String localName,String qname)throws SAXException{
					if(qname.equals("title")){
						titleCheck = false;
						String tempArray[] = str.split(" ");
						for(int i=0;i<tempArray.length;i++){
							if(tempArray[i].length() >= 4){
								String titleArray [] = join2.split(" ");
								for(int j=0;j<titleArray.length;j++){
									titleArray[j] = titleArray[j].replace(".",""); 
									if(titleArray[j].equalsIgnoreCase(tempArray[i])){
										counter++;
									}
								}
							}
							if(counter > 0){
								relcheck = true;
							}
						}
					}
					else if(qname.equals("url")){
						urlCheck = false;
						if(relcheck){
							snum = Integer.toString(counter);
							counter = 0;
							writer(snum,author,join2,url,year,pages,volume,journal);
							relcheck = false;
						}
						author.clear();
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
					else if(qname.equals("author") || qname.equals("editor")){
						checkAuth = false;
						author.add(join1);
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
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ("Ref.txt",true) ) );
			String z = "";
			for(String e : author){
				z = z + e;
				z = z + " | ";
			}
			write.print( snum +"~"+ z + "~" + title + "~" + pages + "~" + year + "~"+ volume+ "~"+journal+"~"+ url + "\n");
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
			    	int x = Integer.valueOf(csvLine1.get(type)).compareTo(Integer.valueOf(csvLine2.get(type)));
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
	    		write.print(csvLines.get(i).get(0)+"~"+csvLines.get(i).get(1)+ "~" +csvLines.get(i).get(2)+ "~" +csvLines.get(i).get(3)+ "~" + csvLines.get(i).get(4) + "~"+ csvLines.get(i).get(5)+ "~" + csvLines.get(i).get(6)+"~"+csvLines.get(i).get(7)+ "\n");
	    		write.flush();
	    	}
			write.close();
			}	
			catch(Exception e){
			e.printStackTrace();
			}
		}
	public void run() {
		findTitle();
		sort();
		working = 1;
	}
}