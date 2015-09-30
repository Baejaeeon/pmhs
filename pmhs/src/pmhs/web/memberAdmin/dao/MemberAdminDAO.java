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
		PreparedStatement pstmt = null; // statement ����
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memberList = null;
		try {
			// DB����
			pstmt = con.prepareStatement(sql); // statement�� ������ش�.
			rs = pstmt.executeQuery(); // resultSet ���� �� select ���� ����
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
					memberList.add(member); // ����Ʈ�� ��� �߰�
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
	            sqlStr.append("WHERE m_id IN ( '" + removeIdArray[i] + "')"); // ù��° �ε��� �� ���..
	         }
	         if(i == 0 && i != removeIdArray.length-1) { // ������ �ε��� �� ���..
	            sqlStr.append("WHERE m_id IN ( '" + removeIdArray[i] +"'");
	         }
	         if(i != 0) {
	        	 sqlStr.append(",'" + removeIdArray[i] +"'");
	         }
	         if(i != 0 && i == removeIdArray.length-1) {
	            sqlStr.append(")");
	         }
		}
		
		PreparedStatement pstmt = null; // statement ����
		int deleteCount = 0;
		try {
			// DB����
			pstmt = con.prepareStatement(sqlStr.toString()); // statement�� ������ش�.
            
			
			deleteCount = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt); // close �޼ҵ� ���
		}
		return deleteCount;
	}
	}


