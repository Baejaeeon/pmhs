package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.commit;
import static pmhs.db.JdbcUtil.getConnect;
import static pmhs.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.QnABoardVO;


public class QnABoardDeleteProService {

	public boolean removeQnAArticle(String[] deleteArray) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con);
		boolean deleteSuccess = false;
		
		int deleteCount = boardAdminDAO.deleteQnAArticle(deleteArray);
		if(deleteCount > 0) {
			deleteSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		return deleteSuccess;
	}
}


