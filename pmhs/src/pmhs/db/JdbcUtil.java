package pmhs.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	Connection con;

	/*static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 디비 연동
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public static Connection getConnect() {
		Connection con = null;
		try {
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "java");
			// java/java 아이디에 연결
			
			// 트랜잭션 처리
			// 하나의 일처리 단위
			// 비지니스 요청 중 여러개의 데이터 조작 작업을 해야 하는 요청이 있다.
			// 이렇게 여러개의 데이터 조작 작업을 하나의 일처리 단위로 묶어주는 것이 트랜잭션 처리이다.
			// Connection 은 autoCommit 이 true야... 따라서, 기본적으로 트랜잭션이 적용이 안됨.
			
			Context initCtx = new InitialContext();
			// 톰켓 자체의 컨텍스트를 얻어옴(무조건).
			
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// 정의된 Resource에 접근할 수 있는 컨텍스트를 얻어옴(무조건).
			
			DataSource ds = (DataSource)envCtx.lookup("jdbc/board"); // 데이터 소스 얻어옴
			// 커넥션은 데이터소스를 통해서 커넥션풀에서 얻어온다.
			con = ds.getConnection();
			
			con.setAutoCommit(false); // 바꿔줘야 트랜잭션이 걸린다.
			
			System.out.println("Connect Success!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) { // 각각 close하는 메소드 선언
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 트랜잭션 부분
	public static void commit(Connection con) { // commit 메소드 정의
		try {
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 트랜잭션 한 작업을 취소
	public static void rollback(Connection con) { // rollback 메소드 정의
		try {
			con.rollback();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
