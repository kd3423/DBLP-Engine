import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

public class Search{
	private ArrayList<String> author = new ArrayList<String>();
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<Integer> publication = new ArrayList<Integer>();
	private ArrayList<Integer> year = new ArrayList<Integer>();
	
	
	public Search(String x) throws MalformedURLException, InterruptedException{
		
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		XmlHandlerTitleForAuthor xml_title = new XmlHandlerTitleForAuthor();
		XmlHandlerAuthor xml_author = new XmlHandlerAuthor();
		xml_author.setName(x);
		Thread t = new Thread(xml_author);
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
					// TODO Auto-generated catch block
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
}
