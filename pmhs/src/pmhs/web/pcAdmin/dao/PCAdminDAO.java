package pmhs.web.pcAdmin.dao;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pmhs.web.pcAdmin.vo.ErrorPCInfo;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class PCAdminDAO {
	private static PCAdminDAO instance;
	private Connection con;

	public PCAdminDAO() {
		// TODO Auto-generated constructor stub
	}

	public static PCAdminDAO getInstance() {
		if (instance == null) {
			instance = new PCAdminDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectReservationCount() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int reservationCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM reservationInfo"); // 총 레코드의 개수를 실행해 가져온다.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservationCount = rs.getInt(1); // 총 예약의 개수를 reservationCount변수에 저장
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reservationCount;
	}

	// 해당 페이지의 예약 목록을 가져와서 출력하는 메소드
	public List<ReservationInfo> selectReservationList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationInfo> reservationList = null;
		ReservationInfo reservation = null;
		
		try {
			// 중요한 SQL 구문
			// 조건에 맞는 해당 페이지에 출력될 레코드 10개 조회하는 SQL구문(lis2, list1 별칭 사용)
			// subQuery 이용
			// 서브 쿼리 중 FROM 절 뒤에 제공되는 서브쿼리를 "인라인뷰"라고 한다. = 인라인 뷰는 별칭이 필요하다.
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM reservationInfo ORDER BY e_time DESC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			// 레코드 번호로 조건을 주기 위해 rownum(레코드번호)을 사용한다.
			// ()안의 DESC된 값들을 가져와서 출력하면서 rownum을 붙여준다.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); // 1페이지 일 경우 1번 부터 10번 글 레코드를 가져온다.
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 글이 하나라도 있으면...
				reservationList = new ArrayList<ReservationInfo>(); // 글 목록을 저장할 컬렉션을 만듬.
				do {
					reservation = new ReservationInfo();
					reservation.setReservationNum(rs.getInt("e_reservationnum"));
					reservation.setSubject(rs.getString("e_subject"));
					reservation.setDeclareDate(rs.getTimestamp("e_declaredate"));
					reservation.setErrorSymptom(rs.getString("e_errorsymptom"));
					reservation.setUnit(rs.getString("p_unit"));
					reservation.setDepartment(rs.getString("p_department"));
					reservation.setLectureRoom(rs.getInt("p_lectureroom"));
					reservation.setPcNum(rs.getInt("p_num"));
					reservation.setName(rs.getString("e_name"));
					reservation.setPhone(rs.getString("e_phone"));
					reservation.setTime(rs.getString("e_time"));
					reservationList.add(reservation); // 해당 페이지에 출력될 글 정보 만들어서 articleList에 저장
				} while (rs.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reservationList;
	}

	public int selectErrorPCCount() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int errorPCCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM errorPCInfo"); // 총 레코드의 개수를 실행해 가져온다.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				errorPCCount = rs.getInt(1); // 총 예약의 개수를 reservationCount변수에 저장
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return errorPCCount;
	}

	public List<ErrorPCInfo> selectErrorPCList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ErrorPCInfo> errorPCList = null;
		ErrorPCInfo errorPC = null;
		
		try {
			// 중요한 SQL 구문
			// 조건에 맞는 해당 페이지에 출력될 레코드 10개 조회하는 SQL구문(lis2, list1 별칭 사용)
			// subQuery 이용
			// 서브 쿼리 중 FROM 절 뒤에 제공되는 서브쿼리를 "인라인뷰"라고 한다. = 인라인 뷰는 별칭이 필요하다.
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM errorPCInfo ORDER BY e_declarenum ASC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			// 레코드 번호로 조건을 주기 위해 rownum(레코드번호)을 사용한다.
			// ()안의 DESC된 값들을 가져와서 출력하면서 rownum을 붙여준다.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); // 1페이지 일 경우 1번 부터 10번 글 레코드를 가져온다.
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 글이 하나라도 있으면...
				errorPCList = new ArrayList<ErrorPCInfo>(); // 글 목록을 저장할 컬렉션을 만듬.
				do {
					errorPC = new ErrorPCInfo();
					errorPC.setE_declarenum(rs.getInt("e_declarenum"));
					errorPC.setE_declaredate(rs.getTimestamp("e_declaredate"));
					errorPC.setE_errorsymptom(rs.getString("e_errorsymptom"));
					errorPC.setE_unit(rs.getString("p_unit"));
					errorPC.setE_department(rs.getString("p_department"));
					errorPC.setE_lectureRoom(rs.getInt("p_lectureRoom"));
					errorPC.setP_num(rs.getInt("p_num"));
					errorPC.setE_name(rs.getString("e_name"));
					errorPC.setE_phone(rs.getString("e_phone"));
					errorPCList.add(errorPC); // 해당 페이지에 출력될 글 정보 만들어서 articleList에 저장
				} while (rs.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return errorPCList;
	}

	public ErrorPCInfo selectErrorPCInfo(int pcNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ErrorPCInfo errorPCInfo = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM errorPCInfo WHERE p_num = ?");
			pstmt.setInt(1, pcNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				errorPCInfo = new ErrorPCInfo();
				errorPCInfo.setE_declarenum(rs.getInt("e_declarenum"));
				errorPCInfo.setE_declaredate(rs.getTimestamp("e_declaredate"));
				errorPCInfo.setE_errorsymptom(rs.getString("e_errorsymptom"));
				errorPCInfo.setE_unit(rs.getString("p_unit"));
				errorPCInfo.setE_department(rs.getString("p_department"));
				errorPCInfo.setE_lectureRoom(rs.getInt("p_lectureRoom"));
				errorPCInfo.setP_num(rs.getInt("p_num"));
				errorPCInfo.setE_name(rs.getString("e_name"));
				errorPCInfo.setE_phone(rs.getString("e_phone"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return errorPCInfo;
	}

	public int insertReservation(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "INSERT INTO reservationInfo VALUES(reservation_seq.nextval, ?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reservationInfo.getSubject());
			pstmt.setTimestamp(2, reservationInfo.getDeclareDate());
			pstmt.setString(3, reservationInfo.getErrorSymptom());
			pstmt.setString(4, reservationInfo.getUnit());
			pstmt.setString(5, reservationInfo.getDepartment());
			pstmt.setInt(6, reservationInfo.getLectureRoom());
			pstmt.setInt(7, reservationInfo.getPcNum());
			pstmt.setString(8, reservationInfo.getName());
			pstmt.setString(9, reservationInfo.getPhone());
			pstmt.setString(10, reservationInfo.getTime());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public int updateReservationState(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE pcInfo SET p_isreservation = 1 WHERE p_unit=? AND p_department=? AND p_lectureRoom=? AND p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reservationInfo.getUnit());
			pstmt.setString(2, reservationInfo.getDepartment());
			pstmt.setInt(3, reservationInfo.getLectureRoom());
			pstmt.setInt(4, reservationInfo.getPcNum());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int deleteReservation(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		StringBuffer sqlStr = new StringBuffer("DELETE reservationInfo "); // StringBuffer를 만들어줌(동적으로 동작할수 있도록)
		for(int i = 0; i < deleteNumArray.length; i++) {
			if(i == 0 && i == deleteNumArray.length-1) {
				sqlStr.append("WHERE e_reservationnum IN ( '" + Integer.parseInt(deleteNumArray[i]) + "')");
			}
			if(i == 0 && i != deleteNumArray.length-1) {
				sqlStr.append("WHERE e_reservationnum IN ( '" + Integer.parseInt(deleteNumArray[i]) + "'");
			}
			if(i != 0) {
				sqlStr.append(",'" + Integer.parseInt(deleteNumArray[i]) + "'");
			}
			if(i != 0 && i == deleteNumArray.length-1) {
				sqlStr.append(")");
			}
		}
		
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		try {
			pstmt = con.prepareStatement(sqlStr.toString()); // 문자열로 바꿔준다.
			deleteCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public int updatePCInfoState(ReservationInfo reservationPC) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE pcInfo SET p_isreservation = 0, p_isdeclare = 0 WHERE p_unit=? AND p_department=? AND p_lectureRoom=? AND p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reservationPC.getUnit());
			pstmt.setString(2, reservationPC.getDepartment());
			pstmt.setInt(3, reservationPC.getLectureRoom());
			pstmt.setInt(4, reservationPC.getPcNum());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int deleteErrorPCInfo(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		String sql = "DELETE FROM errorPCInfo WHERE p_unit=? AND p_department=? AND p_lectureRoom=? AND p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reservationInfo.getUnit());
			pstmt.setString(2, reservationInfo.getDepartment());
			pstmt.setInt(3, reservationInfo.getLectureRoom());
			pstmt.setInt(4, reservationInfo.getPcNum());
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public ReservationInfo getReservationInfo(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationInfo reservationInfo = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM reservationInfo WHERE e_reservationnum = ?");
			pstmt.setInt(1, Integer.parseInt(deleteNumArray[0]));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				/*
				 * e_reservationnum NUMBER NOT NULL,
					e_subject VARCHAR2(25) NOT NULL,
					e_declaredate TIMESTAMP NOT NULL,
					e_errorsymptom VARCHAR2(15),
					p_unit VARCHAR2(50) NOT NULL,
					p_department VARCHAR2(50) NOT NULL,
					p_lectureroom NUMBER NOT NULL,
					p_num NUMBER NOT NULL,
					e_name VARCHAR2(12) NOT NULL,
					e_phone VARCHAR2(13) NOT NULL,
					e_time VARCHAR2(15) NOT NULL,
					
					private int reservationNum;
					private String subject;
					private Timestamp declareDate;
					private String errorSymptom;
					private String unit;
					private String department;
					private int lectureRoom;
					private int pcNum;
					private String name;
					private String phone;
					private String time;
					
				 * 
				 */
				reservationInfo = new ReservationInfo();
				reservationInfo.setReservationNum(rs.getInt("e_reservationnum"));
				reservationInfo.setSubject(rs.getString("e_subject"));
				reservationInfo.setDeclareDate(rs.getTimestamp("e_declaredate"));
				reservationInfo.setErrorSymptom(rs.getString("e_errorsymptom"));
				reservationInfo.setUnit(rs.getString("p_unit"));
				reservationInfo.setDepartment(rs.getString("p_department"));
				reservationInfo.setLectureRoom(rs.getInt("p_lectureroom"));
				reservationInfo.setPcNum(rs.getInt("p_num"));
				reservationInfo.setName(rs.getString("e_name"));
				reservationInfo.setPhone(rs.getString("e_phone"));
				reservationInfo.setTime(rs.getString("e_time"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reservationInfo;
	}
}
