import java.io.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.util.*;
/* 	XMLReader xmlobj = XMLReader.Factory.createXMLReader();
	xmlobj.setContentHandler(new XmlHandler());
	xmlobj.parse("dblp.xml");
*/
class Cmain{
	public static void main(String [] args){
		try{
			boolean empt = false;
			System.setProperty("jdk.xml.entityExpansionLimit", "0");
			XmlHandlerAuthor obj = new XmlHandlerAuthor();
			XmlHandlerTitle obj1 = new XmlHandlerTitle();
			obj.findAuth("Roy T. Fielding");
			ArrayList<String> auth = new ArrayList<>();
			auth = obj.getAuth();
			if(auth.isEmpty()){
				empt = true;
			}
			else{
				for(String x : auth){
					System.out.println(x);
				}	
			}
			if(empt){
				System.out.println("No such author found");
			}
			
			// obj1.findTitle(auth);
			// ArrayList<String> title = new ArrayList<>();
			// ArrayList<String> publication = new ArrayList<>();
			// ArrayList<String> ee = new ArrayList<>();
			// ArrayList<String> pages = new ArrayList<>();
			// ArrayList<String> url = new ArrayList<>();
			// ArrayList<String> volume = new ArrayList<>();
			// ArrayList<Integer> year = new ArrayList<>();
			// title = obj1.getTitle();
			// ee = obj1.getEe();
			// publication = obj1.getPub();
			// url = obj1.getUrl();
			// pages = obj1.getPages();
			// year = obj1.getYear();
			// int i =0;
			// for(i = 0 ; i< title.size();i++){
			// 	System.out.print(title.get(i) +" "+ pages.get(i) +" "+ ee.get(i) +" "+ url.get(i) +" "+ publication.get(i) + " ");
			// 	System.out.println(year.get(i));
			// 	System.out.println();
			// }
			System.out.println("YOYO");

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}