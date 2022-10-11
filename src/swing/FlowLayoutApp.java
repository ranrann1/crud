package swing;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutApp extends JFrame{

	private static final long serialVersionUID = 1L;
	public FlowLayoutApp(String title) {
		super(title);
		setLayout(new FlowLayout());
		
		JButton b1 = new JButton("버튼1");
		JButton b2 = new JButton("버튼2");
		JButton b3 = new JButton("버튼3");
		JButton b4 = new JButton("버튼4");
		JButton b5 = new JButton("버튼5");

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500,100,400,400);
		setVisible(true);

		
	}
	public static void main(String[] args) {
		new FlowLayoutApp("flowlayout");
	}
	
}
