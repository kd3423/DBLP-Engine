import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.AbstractAction;
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
	private int k =0 ;
	private Object[][] data;
	private Object[] columnNames = {"Sno","Author","Title","Pages","Year","Volume","URL"};
	private JTable table;
	private JScrollPane pane;
	private int count=0;
	private ArrayList<String> author;
	private ArrayList<String> title;
	private ArrayList<String> genre;
	private ArrayList<String> price;
	private JButton reset  = new JButton("Reset");
	private JButton search  = new JButton(new AbstractAction("Search"){

		@Override
		public void actionPerformed(ActionEvent arg0) {
				if(dropdown.getSelectedItem().equals("Query 1")){
					try {
						query1.query1_search();
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				update();
			}
		}
		
	});

	public DBLP_GUI() throws IOException{
		super("DBLP");
		File f1 = new File("author.txt");
		if(!(f1.exists())){
			XmlHandlerAuthor xml_author = new XmlHandlerAuthor();
			xml_author.findAuth();
		}
		
		label.setBounds(200,10,400,45);
		label.setFont(new Font("Courier New",Font.PLAIN,40));
		Dropdown();
		
		//Panel
		File file = new File("Reg.txt");
		file.delete();
		data =  reader();
		table = new JTable(data, columnNames);
		pane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane.setBounds(0,0,525,360);
		panel2.add(pane);
		
		prev.setBounds(300,450,100, 30);
		prev.setBackground(Color.white);
		next.setBounds(650,450,100, 30);
		next.setBackground(Color.white);
		panel.setBounds(getBounds());
		query1 = new Query1_GUI(panel);
		query1.query1_add();
		query2 = new Query2_GUI(panel);
		panel2.setBounds(260,80,525,360);
		panel.add(panel2);
		panel.add(label);
		panel.add(prev);
		panel.add(next);
		panel.add(dropdown);
		panel.add(search);
		panel.add(reset);
		reset.setBounds(150,400,100,30);
		reset.setBackground(Color.white);
		search.setBounds(20,400,100,30); 
		search.setBackground(Color.white);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				k=k+20;
				if(k<=count){
					
					update();
				}
				else if(k > count){
					k=k-20;
				}
				
			}
		});
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(k != 0) k=k-20;
				update();
			}
		});
		
		
		//Frame
		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	void Dropdown(){		
		dropdown.addItem("Query 1");
		dropdown.addItem("Query 2");
		dropdown.addItem("Query 3");
		dropdown.setBounds(45,100,100,25);
		dropdown.setBackground(Color.WHITE);
		dropdown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 1")){
					query1();
				}
				else if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 2")){
					query2();
				}
				else if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 3")){
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
	public static void main(String[] args) throws IOException{
		new DBLP_GUI();
	}
	public Object[][] reader(){
		Object[][] data = new Object[20][7];
	try{
		BufferedReader read = new BufferedReader(new FileReader("Ref.txt"));
    	for(int i = 0;i<k;i++){
    		String x[] = read.readLine().split("#");
    	}
    	for(int i = 0;i<20;i++){
    		String call;
			if((call = read.readLine())!= null){
    			String x[] = call.split("#");
    			if(count<Integer.parseInt(x[0]))count = Integer.parseInt(x[0]);
    			
    			data[i] = x;
    		}
    	}
    	read.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return data;
	}
	public void update(){
		panel2.remove(pane);
		data = reader();
		
		table = new JTable(data, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane = new JScrollPane(table);
		pane.setBounds(0,0,525,360);
		panel2.add(pane);
		panel2.repaint();
	}
}
