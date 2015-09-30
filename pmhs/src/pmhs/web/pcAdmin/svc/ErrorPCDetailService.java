package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ErrorPCInfo;

public class ErrorPCDetailService {

	public ErrorPCInfo getErrorPCInfo(int pcNum) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
		
		ErrorPCInfo errorPCInfo = pcAdminDAO.selectErrorPCInfo(pcNum);
		close(con);
		
		return errorPCInfo;
	}

}
