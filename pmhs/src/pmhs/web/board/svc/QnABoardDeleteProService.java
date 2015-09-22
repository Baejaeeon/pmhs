package pmhs.web.board.svc;

import java.sql.Connection;

import pmhs.web.board.dao.BoardDAO;
import static pmhs.db.JdbcUtil.*;
public class QnABoardDeleteProService {

	public boolean removeQnAArticle(int num, String passwd) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // Ŀ�ؼ� ��ü ����
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // Ŀ�ؼ� ����
		
		boolean removeSuccess = false;
		// DB���� ������Ʈ ����
		int deleteCount = boardDAO.deleteQnAArticle(num, passwd);
		if(deleteCount > 0) {
			commit(con);
			removeSuccess = true;
		} else {
			rollback(con);
		}
		close(con); // �۾��� ���� �� �ݾ��ش�.
		
		return removeSuccess;
	}

	}


