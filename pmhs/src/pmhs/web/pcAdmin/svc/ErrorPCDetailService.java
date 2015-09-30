package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ErrorPCInfo;

public class ErrorPCDetailService {

	public ErrorPCInfo getErrorPCInfo(int pcNum) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		
		ErrorPCInfo errorPCInfo = pcAdminDAO.selectErrorPCInfo(pcNum);
		close(con);
		
		return errorPCInfo;
	}

}
