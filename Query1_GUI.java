import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Query1_GUI {
	JPanel query1_panel = new JPanel(null);
	JComboBox<String> drop = new JComboBox<String>();
	JLabel name = new JLabel("Name/Title tags");
	JLabel year = new JLabel("Since Year");
	JLabel range = new JLabel("Custon Range");
	JTextField text_name = new JTextField();
	JTextField text_year = new JTextField();
	JTextField text_range1 = new JTextField();
	JTextField text_range2 = new JTextField();
	JRadioButton sort_date= new JRadioButton("Sort by Date");
	JRadioButton sort_year = new JRadioButton("Sort by Year");
	JRadioButton sort_relevence = new JRadioButton("Sort by Relevence");
	JRadioButton sort_bt_year = new JRadioButton("Sort in between two years");
	ButtonGroup buttons = new ButtonGroup();
	JButton search  = new JButton("Search");
	public Query1_GUI(JPanel panel){
		query1_panel.setVisible(false);
		query1_panel.setBounds(0,125,300,500);
		drop.setBounds(45,30,100,30);
		name.setBounds(30,65,175,30);
		name.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_name.setBounds(150,65,100,30);
		year.setBounds(30,100,150,30);
		year.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_year.setBounds(150,100,100,30);
		range.setBounds(30,135,150,30);
		range.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_range1.setBounds(150,135,45,30);
		text_range2.setBounds(205,135,45,30);
		sort_date.setBounds(20,180,150,20);
		sort_year.setBounds(20,200,150,20);
		sort_relevence.setBounds(20,220,200,20);
		sort_bt_year.setBounds(20,240,220,20);
		
		buttons.add(sort_bt_year);
		buttons.add(sort_date);
		buttons.add(sort_relevence);
		buttons.add(sort_year);
		
		query1_panel.add(drop);
		query1_panel.add(name);
		query1_panel.add(year);
		query1_panel.add(range);
		query1_panel.add(text_name);
		query1_panel.add(text_year);
		query1_panel.add(text_range1);
		query1_panel.add(text_range2);
		query1_panel.add(sort_date);
		query1_panel.add(sort_year);
		query1_panel.add(sort_relevence);
		query1_panel.add(sort_bt_year);
		panel.add(query1_panel);
		panel.repaint();
	}
	public void query1_remove(){
		query1_panel.setVisible(false);
		
	}
	public void query1_add(){
		query1_panel.setVisible(true);
	}
	
}