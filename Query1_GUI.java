import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
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
	private JRadioButton sort_date= new JRadioButton("Sort by Date");
	private JRadioButton sort_year = new JRadioButton("Sort by Year");
	private JRadioButton sort_relevence = new JRadioButton("Sort by Relevence");
	private JRadioButton sort_bt_year = new JRadioButton("Sort in between two years");
	private ButtonGroup buttons = new ButtonGroup();
	
	
	public Query1_GUI(JPanel panel){
		
		query1_panel.setVisible(false);
		query1_panel.setBounds(0,125,250,270);
		drop.setBounds(25,20,175,30);
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
		sort_date.setBounds(20,180,150,20);
		sort_year.setBounds(20,200,150,20);
		sort_relevence.setBounds(20,220,200,20);
		sort_bt_year.setBounds(20,240,220,20);	
		
		drop.addItem("Search by Author Name");
		drop.addItem("Search by Title");
		
		buttons.add(sort_bt_year);
		buttons.add(sort_date);
		sort_date.setSelected(true);
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
	public void query1_search() throws InterruptedException, IOException{
		
		if(!(text_name.getText().equals(""))){
			new Search(text_name.getText());
		}
//		if(sort_date.isSelected()){
//			sort();
//		}
//		
	}
	public void sort(){
	try{
		List<ArrayList<String>> csvLines = new ArrayList<ArrayList<String>>();

		Comparator<ArrayList<String>> comp = new Comparator<ArrayList<String>>() {
		    public int compare(ArrayList<String> csvLine1, ArrayList<String> csvLine2) {
		    	int x = Integer.valueOf(csvLine1.get(4)).compareTo(Integer.valueOf(csvLine2.get(4)));
		    	
		        return -x;
		    }
		};
		
		BufferedReader read = new BufferedReader(new FileReader("Ref.txt"));
    	String call;
		while((call = read.readLine())!= null){
		ArrayList<String> temp = new ArrayList<String>();
		String[] x = call.split("#");
		for(int i = 0;i<x.length;i++){
			temp.add(x[i]);
		}
		csvLines.add(temp);
		}
    	read.close();
    	Collections.sort(csvLines,comp);
    	for(ArrayList<String> e : csvLines){
    		System.out.println(e);
    	}
    	PrintWriter write = new PrintWriter( new BufferedWriter( new FileWriter ( "Ref.txt") ) );
    	for(int i = 0;i<csvLines.size();i++){
    		write.print(csvLines.get(i).get(0)+"#"+csvLines.get(i).get(1)+ "#" +csvLines.get(i).get(2)+ "#" +csvLines.get(i).get(3)+ "#" + csvLines.get(i).get(4) + "#"+ csvLines.get(i).get(5)+ "#" + csvLines.get(i).get(6)+ "\n");
    		write.flush();
    	}
		write.close();
	}	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
}