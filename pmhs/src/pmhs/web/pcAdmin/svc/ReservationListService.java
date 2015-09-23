package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class ReservationListService {

	public int getReservationCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		int articleCount = pcAdminDAO.selectReservationCount();
		
		close(con);
		
		return articleCount;
	}

	public List<ReservationInfo> getReservationList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		
		// ������ �ϳ��� ������ �������� ���� ���� ������
		List<ReservationInfo> reservationList = pcAdminDAO.selectReservationList(startRow, pageSize);
		close(con);
		
		return reservationList;
	}

}
