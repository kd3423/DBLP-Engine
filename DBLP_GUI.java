import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DBLP_GUI extends JFrame{
	private JPanel panel = new JPanel(null);
	private JLabel label = new JLabel("DBLP Query Engine");
	private JComboBox<String> dropdown = new JComboBox<String>();
	Query1_GUI query1;
	Query2_GUI query2;
	
	

	public DBLP_GUI(){
		super("DBLP");
		//Label
		label.setBounds(100,10,400,45);
		label.setFont(new Font("Courier New",Font.PLAIN,40));
		
		//DropDown
		Dropdown();
		
		//Panel
		panel.setBounds(getBounds());
		query1 = new Query1_GUI(panel);
		query2 = new Query2_GUI(panel);
		panel.add(label);
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
