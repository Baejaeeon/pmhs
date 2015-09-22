package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardWriteProService {
	
	public boolean writeArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 目池记 林涝
		
		boolean writeSuccess = false;
		int insertCount = boardDAO.insertQnAArticle(article);
		if(insertCount > 0) {
			commit(con);
			writeSuccess = true;
		} else {
			rollback(con);
		}
		close(con); // 累诀阑 荐青 饶 摧酒霖促.
		
		return writeSuccess;
	}
}
