package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardWriteProService {
	
	public boolean writeArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // Ŀ�ؼ� ����
		
		boolean writeSuccess = false;
		int insertCount = boardDAO.insertQnAArticle(article);
		if(insertCount > 0) {
			commit(con);
			writeSuccess = true;
		} else {
			rollback(con);
		}
		close(con); // �۾��� ���� �� �ݾ��ش�.
		
		return writeSuccess;
	}
}
