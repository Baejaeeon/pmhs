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
					reservation.setDeclareNum(rs.getInt("e_declarenum"));
					reservation.setSubject(rs.getString("e_subject"));
					reservation.setDeclareDate(rs.getTimestamp("e_declaredate"));
					reservation.setErrorSymptom(rs.getString("e_errorsymptom"));
					reservation.setNum(rs.getInt("p_num"));
					reservation.setId(rs.getString("m_id"));
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
					+ "FROM (SELECT * FROM errorPCInfo ORDER BY e_declarenum DESC) list1 ) list2 "
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
}
