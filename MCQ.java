import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

class question{
	private String qn, c1, c2, c3, c4, an;
	
	public question(String a, String b, String c, String d, String e, String f)
	{
		qn = a;
		c1 = b;
		c2 = c;
		c3 = d;
		c4 = e;
		an = f;
	}
	
	public String getq()
	{
		return qn;
	}
	public String getc1()
	{
		return c1;
	}
	public String getc2()
	{
		return c2;
	}
	public String getc3()
	{
		return c3;
	}
	public String getc4()
	{
		return c4;
	}
	public String geta()
	{
		return an;
	}
	
}
public class MCQ extends JFrame implements ActionListener {
	private static final int MAXQNS = 100;
	private int n;
	private int c;
	private int score;
	private question[] qns;
	private int ans;
	
	
	private JLabel sc;
	private JLabel q;
	private JButton[] ch;
	private JButton next;
	
	
	
	
	public MCQ() throws IOException {
		qns = new question[MAXQNS];
		n=0;
		c=0;
		score = 0;
		ans=0;
		
		
		setLayout(new GridLayout(7,1));
		sc = new JLabel("", JLabel.CENTER);
		sc.setFont(new Font("Serif", Font.BOLD, 24));
		q = new JLabel("", JLabel.CENTER);
		q.setFont(new Font("Serif", Font.PLAIN, 24));
		
		add(sc);
		add(q);
		
		ch = new JButton[4];
		Font f = new Font("Serif", Font.PLAIN, 24);
		for (int i=0; i<4; i++)
		{
			ch[i] = new JButton();
			ch[i].setOpaque(true);
			ch[i].setFont(f);
			ch[i].setBackground(Color.white);
			ch[i].addActionListener(this);
			add(ch[i]);
		}
		
		next = new JButton("Next");
		next.setFont(f);
		next.addActionListener(this);
		next.setEnabled(false);
		add(next);
		
		read();
		display();
	}
	public void read() throws IOException
	{
		String p, q, r ,s, t, u;
		BufferedReader file = new BufferedReader(new FileReader("Questions.txt"));
		p = file.readLine();
		while(p != null) {
			q = file.readLine();
			r = file.readLine();
			s = file.readLine();
			t = file.readLine();
			u = file.readLine();
			qns[n] = new question(p,q,r,s,t,u);
			p = file.readLine();
			n++;
		}
		file.close();
	}
	public void display()
	
	{
		if(c>n)
		{
			System.exit(0);
		}
		
		if(c==n)
		{
			for (int i=0; i<4; i++)
			{
				remove(ch[i]);
			}
			q.setText("");
			sc.setText("Final Score: " + score + "/" + n);
			next.setText("Finish");
			c++;
		}
		else
		{
			q.setText(qns[c].getq());
			ch[0].setText(qns[c].getc1());
			ch[1].setText(qns[c].getc2());
			ch[2].setText(qns[c].getc3());
			ch[3].setText(qns[c].getc4());
			ans = (Integer.parseInt(qns[c].geta()))-1;
			if (c!=0)
			{
				next.setEnabled(false);
			}
			c++;
			
		}
	}
	public void update()
	{
		
		for (int i=0; i<4; i++)
		{
			ch[i].setEnabled(false);
			if (i == ans)
			{
				ch[i].setBackground(Color.green);
			}
			else
			{
				ch[i].setBackground(Color.red);
			}
		}
		next.setEnabled(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ch[ans])
		{
			score ++;
			sc.setText("Correct Answer! " + score + "/" + n);
			update();
		}
		else
		{
			sc.setText("Incorrect Answer! " + score + "/" + n);
			update();
		}
		
		if(e.getSource()==next)
		{
			for (int i=0; i<4; i++)
			{
				ch[i].setBackground(Color.white);
				ch[i].setEnabled(true);
			}
			sc.setText(score + "/" + n);
			display();
		}
		
	}
}