import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Query3_GUI {
	private JPanel query3_panel = new JPanel(null);
	
	private JLabel name = new JLabel("Author Name");
	private JLabel year = new JLabel("Year of Prediction");
	private JTextField text_name = new JTextField();
	private JTextField text_year = new JTextField();
	private JFrame error = new JFrame("Error Searching");
	public int cache;
	public Query3_GUI(JPanel panel){
		
		error.setSize(300, 100);
		error.setVisible(false);
		error.setLocationRelativeTo(null);
	    error.add(new JLabel(new ImageIcon("error.png"), JLabel.CENTER));
		query3_panel.setVisible(false);
		query3_panel.setBounds(0,125,250,270);
		name.setBounds(30,65,175,30);
		year.setBounds(30,100,175,30);
		name.setFont(new Font("Courier New", Font.PLAIN, 15));
		year.setFont(new Font("Courier New", Font.PLAIN, 15));
		text_name.setBounds(140,65,100,30);
		text_year.setBounds(170,100,70,30);
		query3_panel.add(name);
		query3_panel.add(year);
		query3_panel.add(text_name);
		query3_panel.add(text_year);
		panel.add(query3_panel);
	}
	public void query3_remove(){
		query3_panel.setVisible(false);
	}
	public void query3_add(){
		query3_panel.setVisible(true);
	}
	public void query3_reset(){
		text_name.setText("");
		text_year.setText("");
	}
	public void query3_search() throws InterruptedException, IOException{
		cache = 0;
		if(!(text_name.getText().equals("")) && !(text_year.getText().equals(""))){
			Search temp = new Search(text_name.getText());
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					if(temp.working == 1){
						try {
							prediction();
						} catch (IOException e) {
							e.printStackTrace();
						}
						cache = 1;
						timer.cancel();
					}
			}},1000,1000);
		}
		else{
			error.setVisible(true);
			cache = 1;
		}
	}
	private void prediction() throws IOException{
		BufferedReader read = new BufferedReader(new FileReader("Ref.txt"));
		BufferedWriter write = new BufferedWriter(new FileWriter("sort.txt"));
		String call;
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		int year = Integer.parseInt(text_year.getText());
		int i = 0;
		int k = 0;
		while((call = read.readLine())!= null){
			String [] z = call.split("~");
			
			if(year > Integer.parseInt(z[4])){
				write.write(i+1 +"~"+ z[1] + "~" + z[2] + "~" + z[3] + "~" + z[4] + "~"+ z[5] + "~" + z[6] + "~" + z[7]+"\n");
				if(map.containsKey(Integer.parseInt(z[4]))){
					k++;
					map.put(Integer.parseInt(z[4]), k);
				}
				else{
					k = 1;
					map.put((Integer.parseInt(z[4])), k);
				}
				i++;
			}
		}
		write.close();
		read.close();
		int sum_year = 0;
		int mean_year = 0;
		int sum_count = 0;
		int mean_count = 0;
		double deno=0,numo=0;
		for(Integer e : map.keySet()){
			sum_year = sum_year + e;
		}
		mean_year = sum_year/map.keySet().size();
		for(Integer e : map.values()){
			sum_count = sum_count + e;
		}
		mean_count = sum_count/map.keySet().size();
		for(Integer e: map.keySet()){
			numo = numo + ((e-mean_year)*(map.get(e)-mean_count));
		}
		for(Integer e : map.keySet()){
			deno = deno + ((e-mean_year)*(e-mean_year));
		}
		double b = numo/deno;
		double a = mean_count - b*mean_year;
		JLabel popl = new JLabel("Result for the "+year+" is: "+(a+b*year), JLabel.CENTER);
		popl.setBackground(Color.white);
		JFrame pop = new JFrame("Predicted Result");
		popl.setFont(new Font("Courier New",Font.BOLD,15));
		pop.setSize(400, 100);pop.setVisible(false);pop.setLocationRelativeTo(null);
	    pop.add(popl);
	    pop.setVisible(true);
	    pop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
