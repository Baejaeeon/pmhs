package pmhs.web.memberAdmin.dao;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pmhs.web.memberAdmin.vo.Member;

public class MemberAdminDAO {
	private static MemberAdminDAO instance;
	private Connection con;

	public MemberAdminDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberAdminDAO getInstance() {
		if (instance == null) {
			instance = new MemberAdminDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<Member> selectMemberList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member";
		PreparedStatement pstmt = null; // statement 선언
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memberList = null;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			rs = pstmt.executeQuery(); // resultSet 생성 후 select 구문 실행
			if (rs.next()) {
				memberList = new ArrayList<Member>();
				do {
					member = new Member(rs.getString("m_id"), rs.getString("m_name"),
							rs.getInt("m_studentNum"), 
							rs.getString("m_passwd"), rs.getInt("m_zipcode1"),
							rs.getInt("m_zipcode2"), rs.getString("m_address1"),
							rs.getString("m_address2"), rs.getString("m_birthDay"), 
							rs.getString("m_email"), rs.getString("m_phone"), 
							rs.getString("m_gender"));
					memberList.add(member); // 리스트에 멤버 추가
				} while (rs.next());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}

	public int deleteMember(String[] removeIdArray) {
		// TODO Auto-generated method stub
		StringBuffer sqlStr = new StringBuffer("DELETE member ");
		for(int i = 0; i < removeIdArray.length; i++){
			if(i == 0 && i == removeIdArray.length-1) {
	            sqlStr.append("WHERE m_id IN ( '" + removeIdArray[i] + "')"); // 첫번째 인덱스 일 경우..
	         }
	         if(i == 0 && i != removeIdArray.length-1) { // 마지막 인덱스 일 경우..
	            sqlStr.append("WHERE m_id IN ( '" + removeIdArray[i] +"'");
	         }
	         if(i != 0) {
	        	 sqlStr.append(",'" + removeIdArray[i] +"'");
	         }
	         if(i != 0 && i == removeIdArray.length-1) {
	            sqlStr.append(")");
	         }
		}
		
		System.out.println(sqlStr);
		
		PreparedStatement pstmt = null; // statement 선언
		int deleteCount = 0;
		try {
			// DB연결
			pstmt = con.prepareStatement(sqlStr.toString()); // statement를 만들어준다.
            
			
			deleteCount = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt); // close 메소드 사용
		}
		return deleteCount;
	}

	public ArrayList<Member> selectSearchId(String search) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE m_id = ?";
		PreparedStatement pstmt = null; // statement 선언
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memberList = null;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			pstmt.setString(1, search);// resultSet 생성 후 select 구문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberList = new ArrayList<Member>();
				do {
					member = new Member(rs.getString("m_id"), rs.getString("m_name"),
							rs.getInt("m_studentNum"), 
							rs.getString("m_passwd"), rs.getInt("m_zipcode1"),
							rs.getInt("m_zipcode2"), rs.getString("m_address1"),
							rs.getString("m_address2"), rs.getString("m_birthDay"), 
							rs.getString("m_email"), rs.getString("m_phone"), 
							rs.getString("m_gender"));
					memberList.add(member); // 리스트에 멤버 추가
				} while (rs.next());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}

	public ArrayList<Member> selectSearchStudentNum(String search) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE m_studentNum = ?";
		PreparedStatement pstmt = null; // statement 선언
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memberList = null;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			pstmt.setString(1, search);// resultSet 생성 후 select 구문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberList = new ArrayList<Member>();
				do {
					member = new Member(rs.getString("m_id"), rs.getString("m_name"),
							rs.getInt("m_studentNum"), 
							rs.getString("m_passwd"), rs.getInt("m_zipcode1"),
							rs.getInt("m_zipcode2"), rs.getString("m_address1"),
							rs.getString("m_address2"), rs.getString("m_birthDay"), 
							rs.getString("m_email"), rs.getString("m_phone"), 
							rs.getString("m_gender"));
					memberList.add(member); // 리스트에 멤버 추가
				} while (rs.next());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	
	}

	public ArrayList<Member> selectSearchName(String search) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE m_name = ?";
		PreparedStatement pstmt = null; // statement 선언
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memberList = null;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			pstmt.setString(1, search);// resultSet 생성 후 select 구문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberList = new ArrayList<Member>();
				do {
					member = new Member(rs.getString("m_id"), rs.getString("m_name"),
							rs.getInt("m_studentNum"), 
							rs.getString("m_passwd"), rs.getInt("m_zipcode1"),
							rs.getInt("m_zipcode2"), rs.getString("m_address1"),
							rs.getString("m_address2"), rs.getString("m_birthDay"), 
							rs.getString("m_email"), rs.getString("m_phone"), 
							rs.getString("m_gender"));
					memberList.add(member); // 리스트에 멤버 추가
				} while (rs.next());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	
	}
	}
	


