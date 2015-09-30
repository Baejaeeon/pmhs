package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.QnABoardVO;


public class QnABoardContentService {
	
	public QnABoardVO getQnAArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		
		QnABoardVO article = boardAdminDAO.selectQnAArticle(num);
		close(con);
		
		return article;
	}
}
