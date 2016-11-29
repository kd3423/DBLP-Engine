import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class DBLP_GUI extends JFrame{
	private int count=0;
	private JPanel panel = new JPanel(null);
	private JLabel label = new JLabel("DBLP Query Engine");
	private JLabel no = new JLabel("No. of results:"+count);
	private JComboBox<String> dropdown = new JComboBox<String>();
	private Query1_GUI query1;
	private Query2_GUI query2;
	private Query3_GUI query3;
	private JPanel panel2 = new JPanel(null);
	private JButton next = new JButton("Next->>");
	private JButton prev = new JButton("<<-Prev");
	private int k =0 ;
	private Object[][] data = new Object[20][8];
	private Object[] columnNames = {"Sno","Author","Title","Pages","Year","Volume","Journal/Booktitle","URL"};
	private JTable table;
	private JScrollPane pane;
	private int flag = 0;
	private Loading load = new Loading();
	private JButton reset  = new JButton(new AbstractAction("Reset"){
		public void actionPerformed(ActionEvent e) {
			query1.query1_reset();query2.query2_reset();query3.query3_reset();
		}
	});
	private JButton search  = new JButton(new AbstractAction("Search"){
		public void actionPerformed(ActionEvent arg0) {
			Timer timer = new Timer();
				if(dropdown.getSelectedItem().equals("Query 1")){
					try {
						if(flag == 0){
							flag = 1;k = 0;load.start();query1.query1_search();
							
							timer.schedule(new TimerTask(){
								public void run() {
									if(query1.cache == 1){
										counter();update();load.stop();flag = 0;timer.cancel();
									}
								}
							}, 1000,1000);
						}
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
				else if(dropdown.getSelectedItem().equals("Query 3")){
					try {
						if(flag == 0){
							flag = 1;k = 0;load.start();query3.query3_search();
							
							timer.schedule(new TimerTask(){
								public void run() {
									if(query3.cache == 1){
										counter();update();load.stop();flag = 0;timer.cancel();
									}
								}
							}, 1000,1000);
						}
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
		}});
	public DBLP_GUI() throws IOException{
		super("DBLP");
		File file = new File("Ref.txt");
		file.delete();
		file = new File("sort.txt");
		file.delete();
		label.setBounds(400,10,400,45);label.setFont(new Font("Courier New",Font.PLAIN,40));
		Dropdown();
		table = new JTable(data, columnNames);pane = new JScrollPane(table);pane.setBounds(0,0,925,343);
		panel2.add(pane);panel2.setBounds(260,80,925,360);
		prev.setBounds(300,450,100, 30);prev.setBackground(Color.white);
		no.setFont(new Font("Courier New",Font.PLAIN,15));no.setBounds(450,450,200,30);
		next.setBounds(650,450,100, 30);next.setBackground(Color.white);
		panel.setBounds(getBounds());
		query1 = new Query1_GUI(panel);query1.query1_add();
		query2 = new Query2_GUI(panel);
		query3 = new Query3_GUI(panel);
		panel.add(panel2);panel.add(label);	panel.add(prev);panel.add(next);panel.add(dropdown);panel.add(search);panel.add(reset);	panel.add(no);
		reset.setBounds(150,400,100,30);reset.setBackground(Color.white);
		search.setBounds(20,400,100,30);search.setBackground(Color.white);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				k=k+20;
				if(k<=count){
					update();
				}
				else if(k > count){
					k=k-20;
				}
			}});
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(k != 0){
					k=k-20;update();
				}
			}});
		setSize(1200, 500);setLocationRelativeTo(null);setResizable(false);setVisible(true);add(panel);setDefaultCloseOperation(EXIT_ON_CLOSE);
		File f1 = new File("author.txt");
		if(!(f1.exists())){
			XmlHandlerAuthor xml_author = new XmlHandlerAuthor();
			Thread x = new Thread(xml_author);
			x.start();
			load.start();
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run() {
					if(xml_author.working == 1){
						load.stop();timer.cancel();
					}}}, 1000,1000);}
	}
	void Dropdown(){		
		dropdown.addItem("Query 1");dropdown.addItem("Query 2");dropdown.addItem("Query 3");dropdown.setBounds(45,100,100,25);dropdown.setBackground(Color.WHITE);dropdown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 1")){
					query1();
				}
				else if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 2")){
					query2();
				}
				else if(((JComboBox<String>) e.getSource()).getSelectedItem().equals("Query 3")){
					query3();
				}}});
	}
	void query1(){
		query1.query1_add();query2.query2_remove();query3.query3_remove();
	}
	void query2(){
		query2.query2_add();query1.query1_remove();query3.query3_remove();
	}
	void query3(){
		query1.query1_remove();query2.query2_remove();query3.query3_add();
	}
	public static void main(String[] args) throws IOException{
		new DBLP_GUI();
	}
	public void counter(){
		int i = 0;
		try {
			BufferedReader read = new BufferedReader(new FileReader("sort.txt"));
			while((read.readLine())!= null){
				i++;
			}
			count = i;
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object[][] reader(){
		Object[][] data = new Object[20][8];
		try{
			BufferedReader read = new BufferedReader(new FileReader("sort.txt"));
			for(int i = 0;i<k;i++){
				read.readLine().split("~");
			}
			for(int i = 0;i<20;i++){
				String call;
				if((call = read.readLine())!= null){
					data[i] = call.split("~");
				}
			}
			read.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public void update(){
		no.setText("No. of results:"+count);
		panel2.remove(pane);
		data = reader();
		table = new JTable(data, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);table.getColumnModel().getColumn(4).setPreferredWidth(10);table.getColumnModel().getColumn(3).setPreferredWidth(10);table.getColumnModel().getColumn(5).setPreferredWidth(10);
		pane = new JScrollPane(table);pane.setBounds(0,0,925,343);panel2.add(pane);
		panel2.repaint();
	}
}
