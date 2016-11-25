import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
public class Search{
	private ArrayList<String> author = new ArrayList<String>();
	public Search(String x) throws InterruptedException, IOException{
		XmlHandlerTitleForAuthor xml_title = new XmlHandlerTitleForAuthor();
		author = getAuthor(x);
		xml_title.setAuthor(author);;
		Thread t = new Thread(xml_title);
		t.start();
		Loading load = new Loading(20);
		load.run();
	}
	private ArrayList<String> getAuthor(String x) throws IOException{
		ArrayList<String> author =new ArrayList<String>();
		int flag = 0;
		try {
			BufferedReader read = new BufferedReader(new FileReader("author.txt"));
			String call;
			while((call = read.readLine())!= null && flag == 0){
				String[] z = call.split("#");
				for(String e: z){
					if(e.equals(x)){
						for(String q: z){
							author.add(q);
						}
						flag = 1;
					}
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return author;
	}
}
