package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardUpdateFormService {

	public QnABoardVO getUpdateQnAArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 目池记 按眉 积己
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 目池记 林涝
		
		QnABoardVO article = boardDAO.selectUpdateQnAArticle(num);
		close(con); // 荤侩 饶 摧酒霖促.
		
		return article;
	}
}
