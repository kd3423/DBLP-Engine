import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Query3_GUI {
	private JPanel query3_panel = new JPanel(null);
	private JLabel name = new JLabel("Author Name");
	private JLabel year = new JLabel("Year of Prediction");
	private JTextField text_name = new JTextField();
	private JTextField text_year = new JTextField();
	public Query3_GUI(JPanel panel){
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
	public double[] holt_alg(double h,double y_last,double y_pred,double T_pred,double alpha,double beta){
		double pred_y_new = alpha * y_last + (1-alpha) * (y_pred + T_pred * h);
		double pred_t_new = beta * (pred_y_new - y_pred)/h + (1-beta)*T_pred;
		double [] temp = new double[2];
		temp[0] = pred_y_new;
		temp[1] = pred_t_new;
		return temp;
	}
	public ArrayList<Double> smoothing(double[] t,double[] y,double alpha,double beta){
		double pred_y = y[1];
		double pred_t = (y[1] - y[0])/(t[1] - t[0]);
		ArrayList<Double> y_hat = new ArrayList<Double>();
		y_hat.add(y[0]);
		y_hat.add(y[1]);
		return y_hat;
		
	}
}