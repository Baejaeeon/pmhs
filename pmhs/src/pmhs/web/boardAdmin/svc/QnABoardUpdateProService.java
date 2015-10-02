package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.commit;
import static pmhs.db.JdbcUtil.getConnect;
import static pmhs.db.JdbcUtil.rollback;

import java.sql.Connection;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.QnABoardVO;

public class QnABoardUpdateProService {

	public boolean modifyQnAArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		
		boolean updateSuccess = false;

		int updateCount = boardAdminDAO.updateQnAArticle(article);
		if(updateCount > 0) {
			commit(con);
			updateSuccess = true;
		} else {
			rollback(con);
		}
		close(con); 
		
		return updateSuccess;
	}
}