import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class Query1_GUI{
	private JPanel query1_panel = new JPanel(null);
	private JComboBox<String> drop = new JComboBox<String>();
	private JLabel name = new JLabel("Name/Title tags");
	private JLabel year = new JLabel("Since Year");
	private JLabel range = new JLabel("Custom Range            -");
	private JTextField text_name = new JTextField();
	private JTextField text_year = new JTextField();
	private JTextField text_range1 = new JTextField();
	private JTextField text_range2 = new JTextField();
	private JRadioButton sort_year= new JRadioButton("Sort by Year");
	private JRadioButton sort_relevence = new JRadioButton("Sort by Relevence");
	private ButtonGroup buttons = new ButtonGroup();
	public int cache;
	public Query1_GUI(JPanel panel){
		query1_panel.setVisible(false);
		query1_panel.setBounds(0,125,250,270);
		drop.setBounds(25,20,200,30);
		drop.setBackground(Color.white);
		name.setBounds(30,65,175,30);
		name.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_name.setBounds(150,65,100,30);
		year.setBounds(30,100,150,30);
		year.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_year.setBounds(150,100,100,30);
		range.setBounds(30,135,175,30);
		range.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_range1.setBounds(150,135,45,30);
		text_range2.setBounds(205,135,45,30);
		sort_year.setBounds(20,180,150,20);
		sort_relevence.setBounds(20,220,200,20);
		drop.addItem("Search by Author Name");
		drop.addItem("Search by Title");
		buttons.add(sort_year);
		sort_year.setSelected(true);
		buttons.add(sort_relevence);
		query1_panel.add(drop);
		query1_panel.add(name);
		query1_panel.add(year);
		query1_panel.add(range);
		query1_panel.add(text_name);
		query1_panel.add(text_year);
		query1_panel.add(text_range1);
		query1_panel.add(text_range2);
		query1_panel.add(sort_year);
		query1_panel.add(sort_relevence);
		panel.add(query1_panel);
		panel.repaint();
	}
	public void query1_remove(){
		query1_panel.setVisible(false);
	}
	public void query1_add(){
		query1_panel.setVisible(true);
	}
	public void query1_search() throws InterruptedException, IOException{
		int x;
		cache = 0;
		if(drop.getSelectedItem().equals("Search by Author Name")){
			if(!(text_name.getText().equals(""))){
				if(!(text_year.getText().equals(""))) x = 2;
				else if(!(text_range1.getText().equals("")) && !(text_range2.getText().equals("")))x = 4;
				else if(sort_year.isSelected()) x = 1;
				else{
					x = 0;
				}
				Search temp = new Search(text_name.getText());
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					@Override
					public void run() {
						try {
							if(temp.working == 1){
								SortSelect(x);
								cache = 1;
								timer.cancel();
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
				}},1000,1000);
			}
			else{
				JFrame error = new JFrame("Error Searching");
				error.setSize(300, 100);
				error.setVisible(true);
				error.setLocationRelativeTo(null);
			    error.add(new JLabel(new ImageIcon("error.png"), JLabel.CENTER));
			}
		}
		else if(drop.getSelectedItem().equals("Search by Title")){	
			if(!(text_name.getText().equals(""))){
				int temp = 4;
				if(!(text_year.getText().equals(""))) x = 2;
				else if(!(text_range1.getText().equals("")) && !(text_range2.getText().equals("")))x = 4;
				else if(sort_year.isSelected()) x = 1;
				else if(sort_relevence.isSelected()){temp = 0;x=1;}
				else{
					x = 0;
				}
				XmlHandlerTitle title = new XmlHandlerTitle(text_name.getText(),temp);
				Thread t = new Thread(title);
				t.start();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					@Override
					public void run() {
						try {
							if(title.working == 1){
								SortSelect(x);
								cache = 1;
								timer.cancel();
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
				}},1000,1000);
			}
		}
	}
	private void SortSelect(int x) throws IOException{
		BufferedReader read = new BufferedReader(new FileReader("Ref.txt"));
		BufferedWriter write = new BufferedWriter(new FileWriter("sort.txt"));
		String call;
		int i = 1;
		while((call = read.readLine())!= null){
			String[] z = call.split("~");
			if(x == 1){
				write.write(i +"~"+ z[1] + "~" + z[2] + "~" + z[3] + "~" + z[4] + "~"+ z[5] + "~" + z[6] + "\n");
				write.flush();
				i++;
			}
			else if(x == 2){
				int year = Integer.parseInt(text_year.getText());
				if(year <= Integer.parseInt(z[4])){
					write.write(i +"~"+ z[1] + "~" + z[2] + "~" + z[3] + "~" + z[4] + "~"+ z[5] + "~" + z[6] + "\n");
					write.flush();
					i++;
				}
			}
			else if(x == 3){
				
			}
			else if(x == 4){
				int year1 = Integer.parseInt(text_range1.getText()),year2 = Integer.parseInt(text_range2.getText());
				if(year1 <= Integer.parseInt(z[4]) && Integer.parseInt(z[4]) <= year2){
					write.write(i +"~"+ z[1] + "~" + z[2] + "~" + z[3] + "~" + z[4] + "~"+ z[5] + "~" + z[6] + "\n");
					write.flush();
					i++;
				}
			}
		}
		read.close();
		write.close();
	}
	public void query1_reset(){
		text_name.setText("");
		text_range1.setText("");
		text_range2.setText("");
		text_year.setText("");
		sort_year.setSelected(true);
	}
}