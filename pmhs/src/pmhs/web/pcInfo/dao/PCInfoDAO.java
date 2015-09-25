package pmhs.web.pcInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
}
