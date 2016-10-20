import org.xml.sax.Attributes; //org.xml.sax package defines all the interfaces used for the SAX parser
import org.xml.sax.helpers.DefaultHandler; // DefaultHandler class that will handle the SAX events that the parser generates
import org.xml.sax.SAXException;
import java.io.*;
/* 	XMLReader xmlobj = XMLReader.Factory.createXMLReader();
	xmlobj.setContentHandler(new XmlHandler());
	xmlobj.parse("dblp.xml");
*/
public class XmlHandler extends DefaultHandler{
	private String author_title,author,title,pages,year,volume,journal,number,url,ee,author_Name,title_String,pages_String,year_String,volume_String,journal_String,number_String,url_String,ee_String;
	private int strLength,flag = 0;
	public void search(String str){
		author_title = str;
		strLength = author_title.length();
	}
	public void startElement(String uri, String localName, String qname, Attributes attributes ) throws SAXException{
		try{
			if(!author_title.isEmpty()){
				if(qname.equals("author")){
					author = qname;
				}
				else if(qname.equals("title")){
					title = qname;
				}
				else if(qname.equals("pages")){
					pages = qname;
				}
				else if(qname.equals("year")){
					year = qname;
				}
				else if(qname.equals("volume")){
					volume = qname;
				}
				else if(qname.equals("journal")){
					journal = qname;
				}
				else if(qname.equals("number")){
					number = qname;
				}
				else if(qname.equals("url")){
					url = qname;
				}
				else if(qname.equals("ee")){
					ee = qname;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void character(char [] chArray , int start , int length){
			if(flag == 0){
				author_Name = new String (chArray,start,length);
				if(author_Name.equals(author_title)){
					System.out.println("Author : " + author_Name);
					flag = 1;
				}
			}
			else if(flag == 1 && !(title.isEmpty())){
				title_String = new String(chArray,start,length);
				System.out.println("Title : " + title_String);
			}
			else if(flag == 1 && !(pages.isEmpty())){
				pages_String = new String(chArray,start,length);
				System.out.println("Pages :" + pages_String);
			}
			else if(flag == 1 && !(year.isEmpty())){
				year_String = new String(chArray,start,length);
				System.out.println("Year :" + year_String);
			}
			else if(flag == 1 && !(volume.isEmpty())){
				volume_String = new String(chArray,start,length);
				System.out.println("Volume : " + volume_String);
			}
			else if(flag == 1 && !(journal.isEmpty())){
				journal_String = new String(chArray,start,length);
				System.out.println("Journal : " + journal_String);
			}
			else if(flag == 1 && !(number.isEmpty())){
				number_String = new String(chArray,start,length);
				System.out.println("Number :" + number_String);
			}
			else if(flag == 1 && !(url.isEmpty())){
				url_String = new String(chArray,start,length);
				System.out.println("Url : " + url_String);
			}
			else if(flag == 1 && !(ee.isEmpty())){
				ee_String = new String(chArray,start,length);
				System.out.println("ee : " + ee_String);
				flag = 0;
			}

	}
	public void endElement(String uri, String localName, String qname)throws SAXException{
		try{
			if(qname.equals("author")){
					author = new String();
			}
			else if(qname.equals("title")){
				title = new String();
			}
			else if(qname.equals("pages")){
				pages = new String();
			}
			else if(qname.equals("year")){
				year = new String();
			}
			else if(qname.equals("volume")){
				volume = new String();
			}
			else if(qname.equals("journal")){
				journal = new String();
			}
			else if(qname.equals("number")){
				number = new String();
			}
			else if(qname.equals("url")){
				url = new String();
			}
			else if(qname.equals("ee")){
				ee = new String();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getAuthor(){
		return author_Name;
	}
	public String getTitle(){
		return title_String;
	}
	public int getYear(){
		int y = Integer.parseInt(year_String);
		return y;
	}
	public String getPages(){
		return pages_String;
	}
	public String getVolume(){
		return volume_String;
	}
	public String getJournal(){
		return journal_String;
	}
	public String getUrl(){
		return url_String;
	}
	public int getNumber(){
		int x = Integer.parseInt(number_String);
		return x;
	}
}