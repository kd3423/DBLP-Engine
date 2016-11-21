import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

public class Search{
	private ArrayList<String> author = new ArrayList<String>();
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<Integer> publication = new ArrayList<Integer>();
	private ArrayList<Integer> year = new ArrayList<Integer>();
	
	
	public Search(String x) throws InterruptedException, IOException{
		
		XmlHandlerTitleForAuthor xml_title = new XmlHandlerTitleForAuthor();
		author = getAuthor(x);
		xml_title.setAuthor(author);;
		Thread t = new Thread(xml_title);
		JFrame load = new JFrame();
		load.setSize(300, 100);
		load.setVisible(true);
		load.setLocationRelativeTo(null);
		ImageIcon loading = new ImageIcon("load.png");
	    load.add(new JLabel(loading, JLabel.CENTER));
	    SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){
	    	
			@Override
			protected Void doInBackground() throws Exception {
				t.start();
				
				return null;
			}
			protected void done(){
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				load.setVisible(false);
			}
	    	
	    };
	    worker.execute();
	    try{
	    	worker.get();
	    }
	    catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}
	public ArrayList<String> getAuthor(String x) throws IOException{
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
							System.out.println(q);
						}
						flag = 1;
					}
						
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return author;
		
	}
}
