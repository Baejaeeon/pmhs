package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class ErrorPCFixProService {

	public boolean removeReservation(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
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

	public boolean changeState(ReservationInfo reservationPC) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
		boolean changeSuccess = false;
		
		int updateCount = pcAdminDAO.updatePCInfoState(reservationPC);
		if(updateCount > 0) {
			changeSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		return changeSuccess;
	}

	public ReservationInfo getReservationInfo(String[] deleteNumArray) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
		
		ReservationInfo reservationInfo = pcAdminDAO.getReservationInfo(deleteNumArray);
		close(con);
		
		return reservationInfo;
	}

}
