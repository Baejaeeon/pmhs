package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardUpdateFormService {

	public QnABoardVO getUpdateQnAArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // Ŀ�ؼ� ����
		
		QnABoardVO article = boardDAO.selectUpdateQnAArticle(num);
		close(con); // ��� �� �ݾ��ش�.
		
		return article;
	}
}
