package swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventHandleApp extends JFrame {

	private static final long serialVersionUID = 1L;

	
	
	
	
	public EventHandleApp(String title) {
		super(title);
		
		setLayout(new FlowLayout());
		
		JButton exit = new JButton("프로그램 종료");
		add(exit);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500,100,400,400);
		setVisible(true);
		
		exit.addActionListener(new ButtonEventHandle());//객체 추가후 이벤트처리
		}
	
	
	
	
	public static void main(String[] args) {
		new EventHandleApp("이벤트핸들러");
	}
	
	
	
	public class ButtonEventHandle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	
	
}
