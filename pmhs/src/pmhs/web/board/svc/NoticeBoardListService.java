package pmhs.web.board.svc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.NoticeBoardVO;

public class NoticeBoardListService {

	public int getNoticeArticleCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 커넥션 주입
		int articleCount = boardDAO.selectNoticeArticleCount();
		
		close(con);
		
		return articleCount;
	}

	public List<NoticeBoardVO> getNoticeArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 커넥션 주입
		
		// 글이 하나라도 있으면 리스팅할 글 정보 얻어오기
		List<NoticeBoardVO> articleList = boardDAO.selectNoticeArticleList(startRow, pageSize);
		
		close(con);
		
		return articleList;
	}
	

}
