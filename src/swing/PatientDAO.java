package swing;
//DAO에서 데이터를 처리 , 프로그램에서 DAO호출 

import java.sql.*;
import java.util.ArrayList;
import java.util.List;





public class PatientDAO extends JdbcDAO {
  private static PatientDAO _dao;
  
  private PatientDAO() {
	  //생성자 은닉화
  }
  
  static {//정적영역에서 객체생성
	  _dao=new PatientDAO();
  }
  
  public static PatientDAO getDAO() {
		return _dao;
	}
  
  
  
	//1.삽입:환자정보를 전달받아 patient 테이블에 삽입하여 저장하고 삽입행의 갯수를 반환하는 메소드
  public int insertPatient(PatientDTO patient) {
	  //dto 객체는 안에 환자정보 필드 5개를 가짐
	  Connection con= null;
	  PreparedStatement pstmt=null;
	  int rows=0;//결과 저장 변수 ->메소드 반환값
	  try {
		  con=getConnection();//JdbcDAO가 커넥션을 제공
		  
		  String sql="insert into patient values(?,?,?,?,?)";
		  pstmt=con.prepareStatement(sql);
		  pstmt.setInt(1,  patient.getPno());
		  pstmt.setString(2,  patient.getName());
		  pstmt.setString(3,  patient.getPhone());
		  pstmt.setString(4,  patient.getDisease());
		  pstmt.setString(5,  patient.getBirthday());
		  
		  rows=pstmt.executeUpdate();

}catch (SQLException e) {
	System.out.println("[ERROR] insertPatient() 메소드의 SQL오류 = "+e.getMessage());
}finally {
	close(con,pstmt);
}
	  return rows;
  }
  
  
  
  
  //2.변경:환자정보를 전달받아 patient 테이블에 저장된 환자정보를 변경하고, 변경행의 갯수반환
  public int updatePatient(PatientDTO patient) {
	  Connection con= null;
	  PreparedStatement pstmt=null;
	  int rows=0;
	  try {
		  con=getConnection();
		  
		  String sql="update patient set name=?, phone=?, disease=?, birthday=? where pno=?";
		  pstmt=con.prepareStatement(sql);
		  pstmt.setString(1, patient.getName());
		  pstmt.setString(2, patient.getPhone());
		  pstmt.setString(3, patient.getDisease());
		  pstmt.setString(4, patient.getBirthday());
		  pstmt.setInt(5, patient.getPno());
		  
		  rows=pstmt.executeUpdate();

	  }catch(SQLException e) {
		  System.out.println("[ERROR] updatePatient() 메소드의 SQL오류 = "+e.getMessage());
	  }finally {
		close(con,pstmt);
	}
	  return rows;
  }
  
  
  
  
  //3.삭제:환자정보를 전달받아 patient 테이블에 저장된 환자정보를 삭제하고, 삭제행 갯수 반환
  public int deletePatient(int pno) {
	  Connection con =null;
	  PreparedStatement pstmt = null;
	  int rows=0;
	  try {
		  con = getConnection();
		  
		  String sql="delete from patient where pno=?";
		  pstmt=con.prepareStatement(sql);
		  pstmt.setInt(1, pno);
		  
		  rows=pstmt.executeUpdate();
	  }catch (SQLException e) {
		System.out.println("[ERROR] deletePatient() 메소드의 SQL오류 = "+e.getMessage());
	}finally {
		close(con, pstmt);
	}
	  return rows;
  }
  
  
  
  
  //4.번호로환자검색:환자번호를 전달받아서, patient 테이블에 저장된 해당번호의 환자정보를 검색하고 반환하는 메소드
  //단일행 검색 DTO 객체반환
  public PatientDTO selectPatient(int pno) {
	  Connection con = null;
	  PreparedStatement pstmt=null;
	  ResultSet rs= null;
	  PatientDTO patient=null;
	  try {
		  con=getConnection();
		  
		  String sql="select*from patient where pno=?";
		  pstmt=con.prepareStatement(sql);
		  pstmt.setInt(1, pno);
		  
		  rs=pstmt.executeQuery();
		  
		  if(rs.next()){
			  patient=new PatientDTO();
			  patient.setPno(rs.getInt("pno"));
			  patient.setName(rs.getString("name"));
			  patient.setPhone(rs.getString("phone"));
			  patient.setDisease(rs.getString("disease"));
			  patient.setBirthday(rs.getString("birthday").substring(0, 10));

		  }
	} catch (SQLException e) {
		System.out.println("[ERROR] selectPatient() 메소드의 SQL오류 = "+e.getMessage());
	}finally {
		close(con, pstmt, rs);
	}
	  return patient;
  }
  

  
  
  
  //5.진료과로환자검색: 진료과를전달받아서, patient테이블에 저장된 해당이름의 환자정보를 검색하여 반환하는 메서드
  public List<PatientDTO> selectDiseasePatientList(String disease){
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  List<PatientDTO> patientList = new ArrayList<PatientDTO>();
	  try {
		con=getConnection();
		
		String sql="select*from patient where disease like '%'||?||'%' order by pno";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, disease);
		
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			PatientDTO patient = new PatientDTO();
			 
			  patient.setPno(rs.getInt("pno"));
			  patient.setName(rs.getString("name"));
			  patient.setPhone(rs.getString("phone"));
			  patient.setDisease(rs.getString("disease"));
			  patient.setBirthday(rs.getString("birthday").substring(0, 10));
			  patientList.add(patient);
		}
	} catch (SQLException e) {
		System.out.println("[ERROR] selectPatient() 메소드의 SQL오류 = "+e.getMessage());
	}finally {
		close(con, pstmt, rs);
	}
	  return patientList;
  }

  
  
  
  //6.모든환자검색:patient테이블에 저장된 모든 환자정보를 검색하여 반환하는 메소드 
 
  public List<PatientDTO> selectAllPatientList(){
	  Connection con = null;
	  PreparedStatement pstmt=null;
	  ResultSet rs = null;
	  List<PatientDTO> patientList = new ArrayList<PatientDTO>();
	  try {
		con = getConnection();
		
		String sql="select*from patient order by pno";
		pstmt=con.prepareStatement(sql);
		
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			PatientDTO patient = new PatientDTO();
			patient.setPno(rs.getInt("pno"));
			patient.setName(rs.getString("name"));
			patient.setPhone(rs.getString("phone"));
			patient.setDisease(rs.getString("disease"));
			patient.setBirthday(rs.getString("birthday").substring(0, 10));

			patientList.add(patient);
		}
	} catch (SQLException e) {
		System.out.println("[ERROR] selectPatient() 메소드의 SQL오류 = "+e.getMessage());

	}finally {
		close(con, pstmt, rs);
	}
	  return patientList;
  }
}


