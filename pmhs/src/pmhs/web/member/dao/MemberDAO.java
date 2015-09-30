package pmhs.web.member.dao;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pmhs.web.member.vo.Member;
import pmhs.web.member.vo.Zipcode;

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

	public int selectIdCount(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM member WHERE m_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int idCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 로그인 성공
				idCount = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return idCount;
	}

	public ArrayList<Zipcode> selectZipcodeList(String dong) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM zipcode WHERE dong LIKE ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ArrayList<Zipcode> zipSearchList = null;
	    Zipcode zipcode = null;
	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + dong + "%");
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	           zipSearchList = new ArrayList<Zipcode>();
	           do {
	              zipcode = new Zipcode();
	              zipcode.setSido(rs.getString("sido"));
	              zipcode.setDong(rs.getString("dong"));
	              zipcode.setGugun(rs.getString("gugun"));
	              zipcode.setBungi(rs.getString("bungi"));
	              zipcode.setSeq(rs.getInt("seq"));
	              zipcode.setZipcode(rs.getString("zipcode"));
	              zipSearchList.add(zipcode);
	            } while (rs.next());
	         }
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      return zipSearchList;
	   }

	public Member selectLoginMember(String id, String passwd) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE m_id = ? AND m_passwd = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member loginMember = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 로그인 성공	
				loginMember = new Member();
				loginMember.setM_id(rs.getString("m_id"));
				loginMember.setM_name(rs.getString("m_name"));
				loginMember.setM_studentNum(rs.getInt("m_studentNum"));
				loginMember.setM_passwd(rs.getString("m_passwd"));
				loginMember.setM_zipcode1(rs.getInt("m_zipcode1"));
				loginMember.setM_zipcode2(rs.getInt("m_zipcode2"));
				loginMember.setM_address1(rs.getString("m_address1"));
				loginMember.setM_address2(rs.getString("m_address2"));
				loginMember.setM_birthDay(rs.getString("m_birthDay"));
				loginMember.setM_email(rs.getString("m_email"));
				loginMember.setM_phone(rs.getString("m_phone"));
				loginMember.setM_gender(rs.getString("m_gender"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return loginMember;
	}

	public Member selectMember(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE m_id = ?";
		PreparedStatement pstmt = null; // statement 선언
		ResultSet rs = null;
		Member member = null;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // resultSet 생성 후 select 구문 실행
			if (rs.next()) {
					member = new Member(rs.getString("m_id"), rs.getString("m_name"),
							rs.getInt("m_studentNum"),rs.getString("m_passwd"), 
							rs.getInt("m_zipcode1"), rs.getInt("m_zipcode2"),
							rs.getString("m_address1"), rs.getString("m_address2"),
							 rs.getString("m_birthDay"), rs.getString("m_email"),
							rs.getString("m_phone"), rs.getString("m_gender"));
					
			}
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	
	}

	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET m_name = ?, m_studentNum = ?, m_passwd = ?, m_zipcode1 = ?, m_zipcode2 = ?, m_address1 = ?, m_address2 = ?, m_birthDay = ?, m_email = ?, m_phone = ?, m_gender = ? WHERE m_id = ?";
		PreparedStatement pstmt = null; // statement 선언
		int updateCount = 0;
		try {
			// DB연결
			pstmt = con.prepareStatement(sql); // statement를 만들어준다.
			pstmt.setString(1, member.getM_name());
			pstmt.setInt(2, member.getM_studentNum());
			pstmt.setString(3, member.getM_passwd());
			pstmt.setInt(4, member.getM_zipcode1());
			pstmt.setInt(5, member.getM_zipcode2());
			pstmt.setString(6, member.getM_address1());
			pstmt.setString(7, member.getM_address2());
			pstmt.setString(8, member.getM_birthDay());
			pstmt.setString(9, member.getM_email());
			pstmt.setString(10, member.getM_phone());
			pstmt.setString(11, member.getM_gender());
			pstmt.setString(12, member.getM_id());

			updateCount = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt); // close 메소드 사용
		}
		return updateCount;
	
	
	}
	}

