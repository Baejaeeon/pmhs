package pmhs.web.pcAdmin.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class PCReservationProService {

	public boolean reservationPC(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
		boolean reservationSuccess = false;
		
		int insertCount = pcAdminDAO.insertReservation(reservationInfo);
		if(insertCount > 0) {
			reservationSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		return reservationSuccess;
	}

	public boolean changeState(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
		boolean changeSuccess = false;
		
		int updateCount = pcAdminDAO.updateReservationState(reservationInfo);
		if(updateCount > 0) {
			changeSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		return changeSuccess;
	}

	public boolean removeErrorPCInfo(ReservationInfo reservationInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 目池记 林涝
		boolean removeSuccess = false;
		
		int deleteCount = pcAdminDAO.deleteErrorPCInfo(reservationInfo);
		if(deleteCount > 0) {
			removeSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		return removeSuccess;
	}


}
