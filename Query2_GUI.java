import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Query2_GUI {
	private JPanel query2_panel = new JPanel(null);
	private JLabel publication = new JLabel("No of Publications");
	private JTextField text_publication = new JTextField();
	public int cache;
	public Query2_GUI(JPanel panel){
		query2_panel.setVisible(false);
		query2_panel.setBounds(0,125,250,270);
		publication.setBounds(30,65,175,30);
		publication.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_publication.setBounds(170,65,70,30);
		query2_panel.add(publication);
		query2_panel.add(text_publication);
		panel.add(query2_panel);
	}
	public void query2_remove(){
		query2_panel.setVisible(false);
	}
	public void query2_add(){
		query2_panel.setVisible(true);
	}
	public void query2_reset(){
		text_publication.setText("");
	}
	public void query2_search() throws IOException{
		File f2 = new File("author1.txt");
		if(!(f2.exists())){
			XmlHandler xml = new XmlHandler();
			Thread x = new Thread(xml);
			x.start();
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run() {
					if(xml.working == 1){
						try {
							start();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						timer.cancel();
					}}}, 1000,1000);}
		else{
			start();
		}
	}
	public void start() throws IOException{
		cache = 0;
		BufferedReader read = new BufferedReader(new FileReader("author1.txt"));
		BufferedWriter write = new BufferedWriter(new FileWriter("sort.txt"));
		String call;
		while((call = read.readLine())!= null){
			String z[] = call.split("#");
//			ArrayList<String> temp = new ArrayList<String>();
			if(Integer.parseInt(text_publication.getText()) <= Integer.parseInt(z[0])){
//				for(String e : z){
//					if(e !="" ){
//						temp.add(e);
//					}
//				}
				write.write(z[0] + "!!" + z[1]+"\n");
				write.flush();
			}
		}
		write.close();
		cache = 1;
	}
	
}
