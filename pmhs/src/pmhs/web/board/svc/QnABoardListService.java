package pmhs.web.board.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;

import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardListService {

	public int getQnAArticleCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); 
		int articleCount = boardDAO.selectQnAArticleCount();
		
		close(con);
		
		return articleCount;
	}

	public List<QnABoardVO> getArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); 

		List<QnABoardVO> articleList = boardDAO.selectQnAArticleList(startRow, pageSize);
		
		close(con);
		
		return articleList;
	}
}
