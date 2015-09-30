package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardUpdateProService {

	public boolean modifyQnAArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
			Connection con = getConnect(); 
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con); 
			
			boolean updateSuccess = false;

			int updateCount = boardDAO.updateQnAArticle(article);
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


