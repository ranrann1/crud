package swing;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutApp extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public GridLayoutApp(String title) {
		super(title);
		
		setLayout(new GridLayout(3,2));
		
		JButton b1 = new JButton("버튼1");
		JButton b2 = new JButton("버튼2");
		JButton b3 = new JButton("버튼3");
		JButton b4 = new JButton("버튼4");
		JButton b5 = new JButton("버튼5");
		JButton b6 = new JButton("버튼6");
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500,100,300,400);
		setVisible(true);

	}
	public static void main(String[] args) {
		new GridLayoutApp("gridlayout");
}
}
