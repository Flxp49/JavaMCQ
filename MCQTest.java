import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MCQTest {
	public static void main(String[] args) throws IOException {
		MCQ m = new MCQ();
		m.setSize(600,400);
		m.setTitle("Quiz");
		m.setVisible(true);
		m.addWindowListener
		(new WindowAdapter()
		{  
			public void windowClosing(WindowEvent e)
			{  System.exit(0);; }
		});
		
	}

}
