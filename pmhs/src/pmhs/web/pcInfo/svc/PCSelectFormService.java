package pmhs.web.pcInfo.svc;

import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;
import java.util.ArrayList;

import pmhs.web.pcInfo.dao.PCInfoDAO;
import pmhs.web.pcInfo.vo.PCInfo;
import static pmhs.db.JdbcUtil.*;

public class PCSelectFormService {

	public ArrayList<PCInfo> getPCSitList(String unit, String department, int lectureRoom) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCInfoDAO pcInfoDAO = PCInfoDAO.getInstance();
		pcInfoDAO.setConnection(con); // 目池记 林涝
		
		ArrayList<PCInfo> pcList = pcInfoDAO.getPCList(unit, department, lectureRoom);
		close(con);
		
		return pcList;
	}


}
