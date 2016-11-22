import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
public class XmlHandlerTitle{
	private ArrayList<String> author = new ArrayList<>();	
	public void findTitle(String str){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser saxTheFile = fac.newSAXParser();
			DefaultHandler defHandler = new DefaultHandler(){
				String title,pages,url,volume,year,snum;
				int counter=0;
				boolean titleCheck = false,relcheck=false,volCheck = false,yearCheck = false,urlCheck = false,checkAuth = false,check = false,pagesCheck = false,checkforall = false,checkCat = true;
				public void startElement(String uri,String localName,String qname,Attributes att)throws SAXException{
					if(qname.equals("www")){
						checkCat = false;
					}
					else if(qname.equals("author")){
						checkAuth = true;
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

				}
				public void characters(char chArray[],int start,int length)throws SAXException{
					if(checkAuth && checkCat){
						String temp = new String(chArray,start,length); 
						author.add(temp);
					}
					else if(titleCheck && checkCat){
						title = new String(chArray,start,length);
						String tempArray[] = str.split(" ");
						int i= 0;
						for(i=0;i<tempArray.length;i++){
							if(tempArray[i].length() >= 4){
								String titleArray [] = title.split(" ");
								int j;
								for(j=0;j<titleArray.length;j++){
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
				}
				public void endElement(String uri,String localName,String qname)throws SAXException{
					if(qname.equals("title")){
						titleCheck = false;
					}
					else if(qname.equals("url")){
						urlCheck = false;
						if(relcheck){
							snum = Integer.toString(counter);
							counter = 0;
							write(snum,author,title,url,year,pages,volume);
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
					else if(qname.equals("author")){
						checkAuth = false;
					}
				}
			};
			saxTheFile.parse("/home/karan/Desktop/Java/Xml/dblp.xml",defHandler);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void write(String snum,ArrayList<String> author,String title , String url,String year,String pages,String volume){
		try{
			PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "TitleSearch.txt",true) ) );
			write.print(snum +","+author + "," + title + "," + pages + "," + year + ","+ volume+ "," + url + "\n");
			write.flush();
			write.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}