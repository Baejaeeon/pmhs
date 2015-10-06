package pmhs.web.pcInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pmhs.web.pcInfo.vo.ErrorPCInfo;
import pmhs.web.pcInfo.vo.PCInfo;
import static pmhs.db.JdbcUtil.*;

public class PCInfoDAO {
	private static PCInfoDAO instance;
	private Connection con;

	public PCInfoDAO() {
		// TODO Auto-generated constructor stub
	}

	public static PCInfoDAO getInstance() {
		if (instance == null) {
			instance = new PCInfoDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<PCInfo> getPCList(String unit, String department, int lectureRoom) {
		// TODO Auto-generated method stub
		ArrayList<PCInfo> pcList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			sql = "SELECT * FROM PCInfo";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			pcList = new ArrayList<PCInfo>();
			while(rs.next()) {
				PCInfo pcInfo = new PCInfo();
				pcInfo.setNum(rs.getInt("p_num"));
				pcInfo.setUnit(rs.getString("p_unit"));
				pcInfo.setDepartment(rs.getString("p_department"));
				pcInfo.setLectureRoom(rs.getInt("p_lectureroom"));
				pcInfo.setIsDeclare(rs.getInt("p_isdeclare"));
				pcInfo.setIsReservation(rs.getInt("p_isreservation"));
				pcList.add(pcInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return pcList;
	}

	public int insertErrorPCInfo(ErrorPCInfo errorPCInfo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "INSERT INTO ErrorPCInfo VALUES(errorInfo_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, errorPCInfo.getE_declaredate());
			pstmt.setString(2, errorPCInfo.getE_errorsymptom());
			pstmt.setString(3, errorPCInfo.getE_unit());
			pstmt.setString(4, errorPCInfo.getE_department());
			pstmt.setInt(5, errorPCInfo.getE_lectureRoom());
			pstmt.setInt(6, errorPCInfo.getP_num());
			pstmt.setString(7, errorPCInfo.getE_name());
			pstmt.setString(8, errorPCInfo.getE_phone());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public int updateDeclareState(ErrorPCInfo errorPCInfo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE pcInfo SET p_isdeclare = 1 WHERE p_unit=? AND p_department=? AND p_lectureRoom=? AND p_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, errorPCInfo.getE_unit());
			pstmt.setString(2, errorPCInfo.getE_department());
			pstmt.setInt(3, errorPCInfo.getE_lectureRoom());
			pstmt.setInt(4, errorPCInfo.getP_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
}
