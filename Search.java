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
		t.start();
		JFrame load = new JFrame("Loading");
		load.setSize(300, 100);
		load.setVisible(true);
		load.setDefaultCloseOperation(2);
		load.setLocationRelativeTo(null);
		ImageIcon loading = new ImageIcon("ajax-loader.gif");
	    load.add(new JLabel(loading, JLabel.CENTER));
		
		//load.setVisible(false);
		
	}
}
