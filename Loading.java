import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Loading{
	JFrame load = new JFrame();
	public Loading(){
		load.setSize(300, 200);
		load.setLocationRelativeTo(null);
		ImageIcon loading = new ImageIcon("ajax-loader.gif");
	    load.add(new JLabel(loading, JLabel.CENTER));
	    load.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    load.setResizable(false);;
	    load.setUndecorated(true);
	}
	public void start(){
		load.setVisible(true);
	}
	public void stop(){
		load.setVisible(false);
	}
	
}
