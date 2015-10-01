package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import pmhs.web.pcAdmin.dao.PCAdminDAO;

public class ErrorPCFixProService {

	public boolean removeReservation(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		boolean deleteSuccess = false;
		
		int deleteCount = pcAdminDAO.deleteReservation(deleteNumArray);
		if(deleteCount > 0) {
			deleteSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		return deleteSuccess;
	}

	public boolean changeState(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		boolean changeSuccess = false;
		
		int updateCount = pcAdminDAO.updatePCInfoState(deleteNumArray);
		if(updateCount > 0) {
			changeSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		return changeSuccess;
	}

}
