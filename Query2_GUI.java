import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	public void query2_search() throws FileNotFoundException{
		BufferedReader read = new BufferedReader(new FileReader("author.txt"));
		
	}
	
}
