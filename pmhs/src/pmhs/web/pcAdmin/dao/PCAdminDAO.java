package pmhs.web.pcAdmin.dao;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
					reservation.setDeclareNum(rs.getInt("e_declarenum"));
					reservation.setSubject(rs.getString("e_subject"));
					reservation.setDeclareDate(rs.getTimestamp("e_declaredate"));
					reservation.setErrorSymptom(rs.getString("e_errorsymptom"));
					reservation.setNum(rs.getInt("p_num"));
					reservation.setId(rs.getString("m_id"));
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
					+ "FROM (SELECT * FROM errorPCInfo ORDER BY e_declarenum DESC) list1 ) list2 "
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
}
