package swing;
//데이터를 전달하기 위한 객체
//dao클래스의 메소드에서, 행정보를 매개변수로전달or반환 위해 작성한 클래스
//테이블의 컬럼과1:1매칭-> 게터&세터 사용함(필드에 직접적인 접근제한)
//값전달용

/*
이름       널?       유형            
-------- -------- ------------- 
PNO      NOT NULL NUMBER(4)     
NAME              VARCHAR2(50)  
PHONE             VARCHAR2(20)  
DISEASE           VARCHAR2(100) 
BIRTHDAY          DATE  
*/

//patient 테이블의 환자정보를 표현하여 전달하기위한 클래스
public class PatientDTO {
	private int pno;
	private String name;
	private String phone;
	private String disease;
	private String birthday;

	public PatientDTO() {
		
	}

	public PatientDTO(int pno, String name, String phone, String disease, String birthday) {
		super();
		this.pno = pno;
		this.name = name;
		this.phone = phone;
		this.disease = disease;
		this.birthday = birthday;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
