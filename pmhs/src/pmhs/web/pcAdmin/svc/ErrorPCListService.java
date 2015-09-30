package pmhs.web.pcAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;
import java.util.List;

import pmhs.web.pcAdmin.dao.PCAdminDAO;
import pmhs.web.pcAdmin.vo.ErrorPCInfo;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class ErrorPCListService {

	public int getErrorPCCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		int articleCount = pcAdminDAO.selectErrorPCCount();
		
		close(con);
		
		return articleCount;
	}

	public List<ErrorPCInfo> getErrorPCList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		PCAdminDAO pcAdminDAO = PCAdminDAO.getInstance();
		pcAdminDAO.setConnection(con); // Ŀ�ؼ� ����
		
		// ������ �ϳ��� ������ �������� ���� ���� ������
		List<ErrorPCInfo> errorPCList = pcAdminDAO.selectErrorPCList(startRow, pageSize);
		close(con);
		
		return errorPCList;
	}

}
