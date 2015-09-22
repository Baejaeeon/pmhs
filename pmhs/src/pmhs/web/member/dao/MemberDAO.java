package pmhs.web.member.dao;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import pmhs.web.member.vo.Member;

public class MemberDAO {
	private static MemberDAO instance;
	private Connection con;

	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO member(m_id, m_name, m_studentNum, m_passwd, m_zipcode1, m_zipcode2,"
				+ " m_address1, m_address2, m_birthDay, m_email, m_phone, m_gender)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null; // statement 선언
		int insertCount = 0;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, member.getM_name());
			pstmt.setInt(3, member.getM_studentNum());
			pstmt.setString(4, member.getM_passwd());
			pstmt.setInt(5, member.getM_zipcode1());
			pstmt.setInt(6, member.getM_zipcode2());
			pstmt.setString(7, member.getM_address1());
			pstmt.setString(8, member.getM_address2());
			pstmt.setString(9, member.getM_birthDay());
			pstmt.setString(10, member.getM_email());
			pstmt.setString(11, member.getM_phone());
			pstmt.setString(12, member.getM_gender());
			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt); // close 메소드 사용
		}
		return insertCount;
	}
}
