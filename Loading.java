import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Loading{
	private int x;
	public Loading(int x){
		this.x = x*1000;
	}
	public void run(){
		JFrame load = new JFrame("Loading");
		load.setSize(300, 100);
		load.setVisible(true);
		load.setLocationRelativeTo(null);
		ImageIcon loading = new ImageIcon("ajax-loader.gif");
	    load.add(new JLabel(loading, JLabel.CENTER));
	    Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				load.setVisible(false);
			}
		}, x);
	}
}
