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
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM reservationInfo"); // �� ���ڵ��� ������ ������ �����´�.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservationCount = rs.getInt(1); // �� ������ ������ reservationCount������ ����
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

	// �ش� �������� ���� ����� �����ͼ� ����ϴ� �޼ҵ�
	public List<ReservationInfo> selectReservationList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationInfo> reservationList = null;
		ReservationInfo reservation = null;
		
		try {
			// �߿��� SQL ����
			// ���ǿ� �´� �ش� �������� ��µ� ���ڵ� 10�� ��ȸ�ϴ� SQL����(lis2, list1 ��Ī ���)
			// subQuery �̿�
			// ���� ���� �� FROM �� �ڿ� �����Ǵ� ���������� "�ζ��κ�"��� �Ѵ�. = �ζ��� ��� ��Ī�� �ʿ��ϴ�.
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM reservationInfo ORDER BY e_time DESC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			// ���ڵ� ��ȣ�� ������ �ֱ� ���� rownum(���ڵ��ȣ)�� ����Ѵ�.
			// ()���� DESC�� ������ �����ͼ� ����ϸ鼭 rownum�� �ٿ��ش�.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); // 1������ �� ��� 1�� ���� 10�� �� ���ڵ带 �����´�.
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// ���� �ϳ��� ������...
				reservationList = new ArrayList<ReservationInfo>(); // �� ����� ������ �÷����� ����.
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
					reservationList.add(reservation); // �ش� �������� ��µ� �� ���� ���� articleList�� ����
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
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM errorPCInfo"); // �� ���ڵ��� ������ ������ �����´�.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				errorPCCount = rs.getInt(1); // �� ������ ������ reservationCount������ ����
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
			// �߿��� SQL ����
			// ���ǿ� �´� �ش� �������� ��µ� ���ڵ� 10�� ��ȸ�ϴ� SQL����(lis2, list1 ��Ī ���)
			// subQuery �̿�
			// ���� ���� �� FROM �� �ڿ� �����Ǵ� ���������� "�ζ��κ�"��� �Ѵ�. = �ζ��� ��� ��Ī�� �ʿ��ϴ�.
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM errorPCInfo ORDER BY e_declarenum ASC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			// ���ڵ� ��ȣ�� ������ �ֱ� ���� rownum(���ڵ��ȣ)�� ����Ѵ�.
			// ()���� DESC�� ������ �����ͼ� ����ϸ鼭 rownum�� �ٿ��ش�.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); // 1������ �� ��� 1�� ���� 10�� �� ���ڵ带 �����´�.
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// ���� �ϳ��� ������...
				errorPCList = new ArrayList<ErrorPCInfo>(); // �� ����� ������ �÷����� ����.
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
					errorPCList.add(errorPC); // �ش� �������� ��µ� �� ���� ���� articleList�� ����
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
		String sql = "UPDATE pcInfo SET p_isreservation = 1 WHERE p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reservationInfo.getPcNum());
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
		StringBuffer sqlStr = new StringBuffer("DELETE reservationInfo "); // StringBuffer�� �������(�������� �����Ҽ� �ֵ���)
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
			pstmt = con.prepareStatement(sqlStr.toString()); // ���ڿ��� �ٲ��ش�.
			deleteCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public int updatePCInfoState(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE pcInfo SET p_isreservation = 0, p_isdeclare = 0 WHERE p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(deleteNumArray[0]));
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
		String sql = "DELETE FROM errorPCInfo WHERE p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reservationInfo.getPcNum());
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
}
