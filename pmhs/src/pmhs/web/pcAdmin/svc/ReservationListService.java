package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class ReservationListService {

	public int getReservationCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 커넥션 주입
		int articleCount = pcAdminDAO.selectReservationCount();
		
		close(con);
		
		return articleCount;
	}

	public List<ReservationInfo> getReservationList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // 커넥션 주입
		
		// 예약이 하나라도 있으면 리스팅할 예약 정보 얻어오기
		List<ReservationInfo> reservationList = pcAdminDAO.selectReservationList(startRow, pageSize);
		close(con);
		
		return reservationList;
	}

}
