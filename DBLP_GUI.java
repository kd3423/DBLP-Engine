import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DBLP_GUI extends JFrame{
	private JPanel panel = new JPanel(null);
	private JLabel label = new JLabel("DBLP Query Engine");
	private JComboBox<String> dropdown = new JComboBox<String>();
	private Query1_GUI query1;
	private Query2_GUI query2;
	private JPanel panel2 = new JPanel(null);
	private JButton next = new JButton("Next->>");
	private JButton prev = new JButton("<<-Prev");
	String[] test1;
	String[][] test2;
	
	private ArrayList<String> author;
	private ArrayList<String> title;
	private ArrayList<String> genre;
	private ArrayList<String> price;

	public DBLP_GUI(){
		super("DBLP");
		//Label
		label.setBounds(200,10,400,45);
		label.setFont(new Font("Courier New",Font.PLAIN,40));
		
		//DropDown
		Dropdown();
		
		//Panel
		final Object[][] data = {
			    {"Mary", "Campione", "Snowboarding", "5"},
			    {"Alison", "Huml", "Rowing", "3"},
			    {"Kathy", "Walrath", "Chasing toddlers", "2"},
			    {"Mark", "Andrews", "Speed reading", "20"},
			    {"Angela", "Lih", "Teaching high school", "4"}
			    };
			    final Object[] columnNames = {"Author", 
			                              "title",
			                              "genre",
			                              "price"};
		JTable table = new JTable(data, columnNames);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0,0,525,350);
		panel2.add(pane);
		prev.setBounds(300,450,100, 30);
		prev.setBackground(Color.white);
		next.setBounds(650,450,100, 30);
		next.setBackground(Color.white);
		panel.setBounds(getBounds());
		query1 = new Query1_GUI(panel);
		query2 = new Query2_GUI(panel);
		panel2.setBounds(260,80,525,350);
		panel.add(panel2);
		panel.add(label);
		panel.add(prev);
		panel.add(next);
		panel.add(dropdown);
		
		
		//Frame
		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	void Dropdown(){
		
		dropdown.addItem("Queries");
		dropdown.addItem("Query 1");
		dropdown.addItem("Query 2");
		dropdown.addItem("Query 3");
		dropdown.setBounds(45,100,100,25);
		dropdown.setBackground(Color.WHITE);
		dropdown.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 1"))
				{
					query1();
				}
				else if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 2"))
				{
					query2();
				}
				else if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 3"))
				{
					query3();
				}
				
			}
			
		});
		
	}
	
	void query1(){
		query1.query1_add();
		query2.query2_remove();
		
	}
	void query2(){
		query2.query2_add();
		query1.query1_remove();
	}
	void query3(){
		query1.query1_remove();
		
	}
	public static void main(String[] args){
		new DBLP_GUI();
	}
}
