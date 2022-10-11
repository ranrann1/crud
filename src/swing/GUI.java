package swing;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



import java.awt.Color;
import java.awt.Font;

public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static final int NONE = 0;
	public static final int ADD = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	public static final int UPDATE_CHANGE = 4;
	public static final int SEARCH = 5;

	JTextField pnoTF, nameTF, phoneTF, diseaseTF, birthdayTF;
	JButton addB, deleteB, updateB, searchB, cancelB;
	
	JTable table;
	
	int cmd;
	
	public GUI() throws Exception {
		getContentPane().setBackground(new Color(0, 0, 128));
		setTitle("환자관리 프로그램");
		setSize(800, 400);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);

		JPanel left = new JPanel();
		left.setBounds(610, 30, 157, 280);
		left.setBackground(new Color(0, 0, 128));
		left.setForeground(Color.BLUE);

		JPanel ppno = new JPanel();
		ppno.add(new JLabel("환자번호"));
		ppno.add(pnoTF = new JTextField(10));

		JPanel pname = new JPanel();
		pname.add(new JLabel("이름"));
		pname.add(nameTF = new JTextField(10));
		
		JPanel pphone = new JPanel();
		pphone.add(new JLabel("전화"));
		pphone.add(phoneTF = new JTextField(10));

		JPanel pdisease = new JPanel();
		pdisease.add(new JLabel("진료과"));
		pdisease.add(diseaseTF = new JTextField(10));
		left.setLayout(new GridLayout(0, 1, 0, 0));

		left.add(ppno);
		left.add(pname);
		left.add(pphone);
		left.add(pdisease);
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(0, 0, 128));
		bottom.setBounds(0, 340, 786, 23);
		bottom.setLayout(new GridLayout(1, 5));

		bottom.add(addB = new JButton("추가"));
		addB.setFont(new Font("굴림", Font.BOLD, 14));
		addB.addActionListener(this);

		bottom.add(deleteB = new JButton("삭제"));
		deleteB.setFont(new Font("굴림", Font.BOLD, 14));
		deleteB.addActionListener(this);
		
		bottom.add(updateB = new JButton("변경"));
		updateB.setFont(new Font("굴림", Font.BOLD, 14));
		updateB.addActionListener(this);

		bottom.add(searchB = new JButton("검색"));
		searchB.setFont(new Font("굴림", Font.BOLD, 14));
		searchB.addActionListener(this);
		
		bottom.add(cancelB = new JButton("초기화"));
		cancelB.setFont(new Font("굴림", Font.BOLD, 14));
		cancelB.addActionListener(this);

		Object[] title={"환자번호","이름","전화번호","진료과","생년월일"};
		table=new JTable(new DefaultTableModel(title, 0));
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		getContentPane().setLayout(null);

		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(51, 24, 505, 289);
		
		getContentPane().add(sp);
		getContentPane().add(left);
		
		JPanel pbirthday = new JPanel();
		left.add(pbirthday);
		pbirthday.add(new JLabel("생일"));
		pbirthday.add(birthdayTF = new JTextField(10));
		getContentPane().add(bottom);
		cmd = NONE;
		initialize();
		displayAllPatient();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void initialize() {
		pnoTF.setEditable(false);
		nameTF.setEditable(false);
		phoneTF.setEditable(false);
		diseaseTF.setEditable(false);
		birthdayTF.setEditable(false);
	}

	public void setEditable(int n) {
		switch (n) {
		case ADD:
			pnoTF.setEditable(true);
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			diseaseTF.setEditable(true);
			birthdayTF.setEditable(true);
			break;
		case DELETE:
			pnoTF.setEditable(true);
			break;
		case UPDATE:
			pnoTF.setEditable(true);
			break;
		case UPDATE_CHANGE:
			pnoTF.setEditable(false);
			nameTF.setEditable(true);
			phoneTF.setEditable(true);
			diseaseTF.setEditable(true);
			birthdayTF.setEditable(true);
			break;
		case SEARCH:
			diseaseTF.setEditable(true);
			break;
		case NONE:
			pnoTF.setEditable(false);
			nameTF.setEditable(false);
			phoneTF.setEditable(false);
			diseaseTF.setEditable(false);
			birthdayTF.setEditable(false);
		}
	}

	public void setEnable(int n) {
		addB.setEnabled(false);
		deleteB.setEnabled(false);
		updateB.setEnabled(false);
		searchB.setEnabled(false);

		switch (n) {
		case ADD:
			addB.setEnabled(true);
			setEditable(ADD);
			cmd = ADD;
			break;
		case DELETE:
			deleteB.setEnabled(true);
			setEditable(DELETE);
			cmd = DELETE;
			break;
		case UPDATE:
			updateB.setEnabled(true);
			setEditable(UPDATE);
			cmd = UPDATE;
			break;			
		case UPDATE_CHANGE:
			updateB.setEnabled(true);
			setEditable(UPDATE_CHANGE);
			cmd = UPDATE_CHANGE;
			break;			
		case SEARCH:
			searchB.setEnabled(true);
			setEditable(SEARCH);
			cmd = SEARCH;
			break;
		case NONE:
			addB.setEnabled(true);
			deleteB.setEnabled(true);
			updateB.setEnabled(true);
			searchB.setEnabled(true);
		}
	}

	public void clear() {
		pnoTF.setText("");
		nameTF.setText("");
		phoneTF.setText("");
		diseaseTF.setText("");
		birthdayTF.setText("");
	}

	public void initDisplay() {
		setEnable(NONE);
		clear();
		cmd = NONE;
		initialize();		
	}

	public static void main(String args[]) throws Exception {
		new GUI();
	}
	
	public void actionPerformed(ActionEvent ev) {
		Component c = (Component) ev.getSource();
		try {
			if (c == addB) {
				if (cmd != ADD) {
					setEnable(ADD);					
				} else {
					addPatient();
				}
			} else if (c == deleteB) {
				if (cmd != DELETE) {
					setEnable(DELETE);
				} else {
					removePatient();
				}
			} else if (c == updateB) {
				if (cmd != UPDATE && cmd != UPDATE_CHANGE) {
					setEnable(UPDATE);
				} else if (cmd != UPDATE_CHANGE) {
					searchPnoPatient();
				} else {
					modifyPatient();
				}
			} else if (c == searchB) {
				if (cmd != SEARCH) {
					setEnable(SEARCH);
				} else {
					searchDiseasePatient();
				}
			} else if (c == cancelB) {
				displayAllPatient();
				initDisplay();
				
			}
		} catch (Exception e) {
			System.out.println("예외 발생 : " + e);
		}		
	}
	
	
	
	
	
	//환자 테이블에 저장된 모든 환자검색후 jtable컴퍼넌트에 출력메소드
	public void displayAllPatient() {
		List<PatientDTO> patientList=PatientDAO.getDAO().selectAllPatientList();
		
		if(patientList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "해당하는 환자정보가 없습니다.");
			return;
		}
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		for(int i=model.getRowCount();i>0;i--) {
			
			model.removeRow(0);
		}
		
		for(PatientDTO patient :patientList) {
			
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(patient.getPno());
			rowData.add(patient.getName());
			rowData.add(patient.getPhone());
			rowData.add(patient.getDisease());
			rowData.add(patient.getBirthday());
			
			
			model.addRow(rowData);
		
	}
	}
	
	
	
	
	
	public void addPatient() {
		
		String pnoTemp=pnoTF.getText();
		if(pnoTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "환자번호를 입력해주세요");
			pnoTF.requestFocus();
			return;
		}
		String pnoReg="^[1-9][0-9]{3}$";
		if(!Pattern.matches(pnoReg, pnoTemp)) {
			JOptionPane.showMessageDialog(this, "환자번호를 숫자로만 입력해 주세요.");
			pnoTF.requestFocus();
			return;
		}
		int pno=Integer.parseInt(pnoTemp);
		if(PatientDAO.getDAO().selectPatient(pno)!=null) {
			JOptionPane.showMessageDialog(this, "사용중인 환자번호 입니다.다시 입력해 주세요.");
		pnoTF.requestFocus();
			return;
		}
		
		String name=nameTF.getText();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}
		
		String nameReg="^[가-힣]{2,5}$";
		if(!Pattern.matches(nameReg, name)) {
			JOptionPane.showMessageDialog(this, "이름은 2~5 범위의 한글로 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}
		
		String phone=phoneTF.getText();
		if(phone.equals("")) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}
		
		String phoneReg="(01[016789])-\\d{3,4}-\\d{4}";
		if(!Pattern.matches(phoneReg, phone)) {
			JOptionPane.showMessageDialog(this, "전화번호를 형식에 맞게 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}
String disease=diseaseTF.getText();
		
		if(disease.equals("")) {
			JOptionPane.showMessageDialog(this, "진료과를 입력해 주세요.");
			diseaseTF.requestFocus();
			return;
		}
		
String birthday=birthdayTF.getText();
		
		if(birthday.equals("")) {
			JOptionPane.showMessageDialog(this, "생년월일을 입력해 주세요.");
			birthdayTF.requestFocus();
			return;
		}
		
		String birthdayReg="(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
		if(!Pattern.matches(birthdayReg, birthday)) {
			JOptionPane.showMessageDialog(this, "생년월일을 형식에 맞게 입력해 주세요.");
			birthdayTF.requestFocus();
			return;
		}
		
		PatientDTO patient= new PatientDTO();
		patient.setPno(pno);
		patient.setName(name);
		patient.setPhone(phone);
		patient.setDisease(disease);
		patient.setBirthday(birthday);
		
		int rows=PatientDAO.getDAO().insertPatient(patient);
		JOptionPane.showMessageDialog(this, rows+"명의 환자정보를 삽입 하였습니다.");
		
		displayAllPatient();
		initDisplay();
	}
	
	
	
	
	public void searchPnoPatient() {
		String pnoTemp=pnoTF.getText();
		
		if(pnoTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "환자번호를 입력해주세요");
			pnoTF.requestFocus();
			return;
		}
		String pnoReg="^[1-9][0-9]{3}$";
		if(!Pattern.matches(pnoReg, pnoTemp)) {
			JOptionPane.showMessageDialog(this, "환자번호는 4자리의 숫자로만 입력해 주세요.");
			pnoTF.requestFocus();
			return;
		}
		
		int pno=Integer.parseInt(pnoTemp);
		
		PatientDTO patient=PatientDAO.getDAO().selectPatient(pno);
		if(patient==null) {
			JOptionPane.showMessageDialog(this, "변경하고자 하는 환자정보가 없습니다.");
			pnoTF.requestFocus();
			pnoTF.setText("");
			return;
		}
		pnoTF.setText(patient.getPno()+"");
		nameTF.setText(patient.getName());
		phoneTF.setText(patient.getPhone());
		diseaseTF.setText(patient.getDisease());
		birthdayTF.setText(patient.getBirthday());
		
		setEnable(UPDATE_CHANGE);
		
		
		
	}
	public void modifyPatient() {
		int pno=Integer.parseInt(pnoTF.getText());

		String name=nameTF.getText();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 반드시 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}
		
		String nameReg="^[가-힣]{2,5}$";
		if(!Pattern.matches(nameReg, name)) {
			JOptionPane.showMessageDialog(this, "이름은 2~5 범위의 한글로만 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}

		String phone=phoneTF.getText();
		
		if(phone.equals("")) {
			JOptionPane.showMessageDialog(this, "전화번호를 반드시 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}
		
		String phoneReg="(01[016789])-\\d{3,4}-\\d{4}";
		if(!Pattern.matches(phoneReg, phone)) {
			JOptionPane.showMessageDialog(this, "전화번호를 형식에 맞게 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}
		String disease=diseaseTF.getText();
		
		if(disease.equals("")) {
			JOptionPane.showMessageDialog(this, "진료과를 반드시 입력해 주세요.");
			diseaseTF.requestFocus();
			return;
		}
		String birthday=birthdayTF.getText();
		
		if(birthday.equals("")) {
			JOptionPane.showMessageDialog(this, "생년월일을 반드시 입력해 주세요.");
			birthdayTF.requestFocus();
			return;
		}
		
		String birthdayReg="(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
		if(!Pattern.matches(birthdayReg, birthday)) {
			JOptionPane.showMessageDialog(this, "생년월일을 형식에 맞게 입력해 주세요.");
			birthdayTF.requestFocus();
			return;
		}
		PatientDTO patient=new PatientDTO();
		patient.setPno(pno);
		patient.setName(name);
		patient.setPhone(phone);
		patient.setDisease(disease);
		patient.setBirthday(birthday);
		
		int rows=PatientDAO.getDAO().updatePatient(patient);
		
		JOptionPane.showMessageDialog(this, rows+"명의 환자정보를 변경 하였습니다.");

		displayAllPatient();
		initDisplay();
	}
	
	public void removePatient() {
		String pnoTemp=pnoTF.getText();
		if(pnoTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "환자번호를 반드시 입력해 주세요.");
			pnoTF.requestFocus();
			return;
		}
		
		String noReg="^[1-9][0-9]{3}$";
		if(!Pattern.matches(noReg, pnoTemp)) {
			JOptionPane.showMessageDialog(this, "환자번호는 4자리의 숫자로만 입력해 주세요.");
			pnoTF.requestFocus();
			return;
		}
		
		int pno=Integer.parseInt(pnoTemp);
		
		int rows=PatientDAO.getDAO().deletePatient(pno);
		
		if(rows>0) {
			JOptionPane.showMessageDialog(this, rows+"명의 환자정보를 삭제 하였습니다.");
			displayAllPatient();
		} else {
			JOptionPane.showMessageDialog(this, "삭제하고자 하는 환자정보가 없습니다.");
		}
		
		initDisplay();
	}
	
	
	
	
	
	public void searchDiseasePatient() {
		String disease=diseaseTF.getText();
		if(disease.equals("")) {
			JOptionPane.showMessageDialog(this, "진료과를 반드시 입력해 주세요.");
			diseaseTF.requestFocus();
			return;
		}
		
		String diseaseReg="^[가-힣]{2,8}$";
		if(!Pattern.matches(diseaseReg, disease)) {
			JOptionPane.showMessageDialog(this, "진료과는 2~8 범위의 한글로만 입력해 주세요.");
			diseaseTF.requestFocus();
			return;
		}
		
		
		List<PatientDTO> patientList=PatientDAO.getDAO().selectDiseasePatientList(disease);

		if(patientList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "검색된 진료과가 없습니다.");
			initDisplay();
			return;
		}
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		
		for(int i=model.getRowCount();i>0;i--) {
			model.removeRow(0);
		}
		
		for(PatientDTO patient:patientList) {
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(patient.getPno());
			rowData.add(patient.getName());
			rowData.add(patient.getPhone());
			rowData.add(patient.getDisease());
			rowData.add(patient.getBirthday());
			model.addRow(rowData);
		}
		
		initDisplay();
		
	}		
}
	
	
	
	

	




	
	
	







