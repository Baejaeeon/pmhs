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
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // Ŀ�ؼ� ����
		int articleCount = boardDAO.selectNoticeArticleCount();
		
		close(con);
		
		return articleCount;
	}

	public List<NoticeBoardVO> getNoticeArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // Ŀ�ؼ� ����
		
		// ���� �ϳ��� ������ �������� �� ���� ������
		List<NoticeBoardVO> articleList = boardDAO.selectNoticeArticleList(startRow, pageSize);
		
		close(con);
		
		return articleList;
	}
	

}
