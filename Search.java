import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Search{
	private ArrayList<String> author = new ArrayList<String>();
	public volatile int working;
	public Search(String x) throws InterruptedException, IOException{
		working  = 0;
		XmlHandlerTitleForAuthor xml_title = new XmlHandlerTitleForAuthor();
		author = getAuthor(x);
		xml_title.setAuthor(author);;
		Thread t = new Thread(xml_title);
		t.start();
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {	
				if(xml_title.working == 1){
					working = xml_title.working;
					timer.cancel();
				}
			}
		},1000,1000);
	}
	private ArrayList<String> getAuthor(String x) throws IOException{
		ArrayList<String> author =new ArrayList<String>();
		int flag = 0;
		try {
			BufferedReader read = new BufferedReader(new FileReader("author.txt"));
			String call;
			while((call = read.readLine())!= null && flag == 0){
				String[] z = call.split("#");
				for(String e: z){
					String[] k = e.split(" ");
					for(String m:k){
						if(m.equalsIgnoreCase(x)){
							for(String q: z){
								author.add(q);
							}
						}
					}
					if(e.equalsIgnoreCase(x)){
						for(String q: z){
							author.add(q);
						}
					}
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return author;
	}
}

