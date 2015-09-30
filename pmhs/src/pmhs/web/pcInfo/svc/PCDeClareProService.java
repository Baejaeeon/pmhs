package pmhs.web.pcInfo.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.pcInfo.dao.PCInfoDAO;
import pmhs.web.pcInfo.vo.ErrorPCInfo;

public class PCDeClareProService {

	public boolean declarePC(ErrorPCInfo errorPCInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCInfoDAO pcInfoDAO = PCInfoDAO.getInstance();
		pcInfoDAO.setConnection(con); // 目池记 林涝
		boolean declareSuccess = false;
		
		int insertCount = pcInfoDAO.insertErrorPCInfo(errorPCInfo);
		
		if(insertCount > 0) {
			declareSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		return declareSuccess;
	}

	public boolean changeState(ErrorPCInfo errorPCInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCInfoDAO pcInfoDAO = PCInfoDAO.getInstance();
		pcInfoDAO.setConnection(con); // 目池记 林涝
		boolean changeSuccess = false;
		
		int updateCount = pcInfoDAO.updateDeclareState(errorPCInfo);
		if(updateCount > 0) {
			changeSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		return changeSuccess;
	}

}
