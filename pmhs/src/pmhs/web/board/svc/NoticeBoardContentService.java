package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.NoticeBoardVO;

public class NoticeBoardContentService {

	public NoticeBoardVO getNoticeArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // Ŀ�ؼ� ����
		
		NoticeBoardVO article = boardDAO.selectNoticeArticle(num);
		close(con);
		
		return article;
	}

}
