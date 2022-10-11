package swing;



import java.sql.Connection;
import java.sql.SQLException;

//DBCP(DataBase Connection Pool) : 다수의 Connection 객체를 미리 생성하여 저장하고 Connection 
//객체를 반환하는 기능을 제공하는 클래스
// => Connection 객체를 미리 만들어 사용하므로 JDBC 프로그램의 실행 속도 증가
// => Connection 객체를 생성하기 위한 정보 변경이 용이 - 유지보수의 효율성 증가
// => Connection 객체의 갯수 제한 가능
public class ConnectionPoolApp { 
	public static void main(String[] args) throws SQLException {
		//ConnectionPool 객체를 생성하여 저장 - 에러 발생
		// => 생성자가 은닉화 선언되어 있어 생성자를 이용한 객체 생성 불가능
		//ConnectionPool cp=new ConnectionPool();
		
		//ConnectionPool 객체를 반환받아 저장 
		// => ConnectionPool 객체에는 Connection 객체를 미리 생성하여 저장
		//ConnectionPool 클래스는 싱글톤 클래스(Singleton Class)로 선언
		//싱글톤 클래스 : 싱글톤 디자인 패턴을 사용하여 선언된 클래스
		// => 프로그램에 객체를 하나만 제공하기 위한 목적으로 작성된 클래스
		ConnectionPool cp=ConnectionPool.getInstance();//ConnectionPool은 하나임
	
	
		//ConnectionPool 객체로부터 Connection 객체를 반환받아 저장
		Connection con1=cp.getConnection();//커넥션풀에서 미리만든 커넥션을 빌림
		System.out.println("con1 = "+con1);
		//사용한 Connection 객체를 ConnectionPool 객체에게 전달->다른사람이 다시 쓸수있도록
		cp.freeConnection(con1);
		
		Connection con2=cp.getConnection();
		System.out.println("con2 = "+con2);
		cp.freeConnection(con2);
		
		Connection con3=cp.getConnection();
		System.out.println("con3 = "+con3);
		cp.freeConnection(con3);
		
		Connection con4=cp.getConnection();
		System.out.println("con4 = "+con4);
		cp.freeConnection(con4);
	}
}