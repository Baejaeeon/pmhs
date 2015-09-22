package pmhs.web.board.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;

import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardListService {

	public int getQnAArticleCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 커넥션 주입
		int articleCount = boardDAO.selectQnAArticleCount();
		
		close(con);
		
		return articleCount;
	}

	public List<QnABoardVO> getArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 커넥션 주입
		
		// 글이 하나라도 있으면 리스팅할 글 정보 얻어오기
		List<QnABoardVO> articleList = boardDAO.selectQnAArticleList(startRow, pageSize);
		
		close(con);
		
		return articleList;
	}
}
