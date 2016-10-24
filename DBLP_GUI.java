import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class DBLP_GUI extends JFrame{
	private JPanel panel = new JPanel(null);
	private JLabel label = new JLabel("DBLP Query Engine");
	private JComboBox<String> dropdown = new JComboBox<String>();
	private Query1_GUI query1;
	private Query2_GUI query2;
	private JPanel panel2 = new JPanel(null);
	private JButton next = new JButton("Next");
	private JButton prev = new JButton("Prev");

	public DBLP_GUI(){
		super("DBLP");
		//Label
		label.setBounds(100,10,400,45);
		label.setFont(new Font("Courier New",Font.PLAIN,40));
		
		//DropDown
		Dropdown();
		
		//Panel
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		JTable table = new JTable(data, columnNames);
		panel2.add(table);
		prev.setBounds(300,450,100, 30);
		prev.setBackground(Color.white);
		next.setBounds(450,450,100, 30);
		next.setBackground(Color.white);
		panel.setBounds(getBounds());
		query1 = new Query1_GUI(panel);
		query2 = new Query2_GUI(panel);
		panel2.setBounds(260,80,325,350);
		panel2.setBackground(Color.white);
		panel.add(panel2);
		panel.add(label);
		panel.add(prev);
		panel.add(next);
		panel.add(dropdown);
		
		
		//Frame
		setSize(600, 500);
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
