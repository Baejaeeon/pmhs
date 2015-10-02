package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.QnABoardVO;

public class QnABoardUpdateFormService {

	public QnABoardVO getUpdateQnAArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		
		QnABoardVO article = boardAdminDAO.selectUpdateQnAArticle(num);
		close(con); 
		
		return article;
	}

}
