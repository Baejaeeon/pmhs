package pmhs.web.board.svc;

import java.sql.Connection;

import pmhs.web.board.dao.BoardDAO;
import static pmhs.db.JdbcUtil.*;
public class QnABoardDeleteProService {

	public boolean removeQnAArticle(int num, String passwd) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); // 커넥션 객체 생성
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); // 커넥션 주입
		
		boolean removeSuccess = false;
		// DB에서 업데이트 수행
		int deleteCount = boardDAO.deleteQnAArticle(num, passwd);
		if(deleteCount > 0) {
			commit(con);
			removeSuccess = true;
		} else {
			rollback(con);
		}
		close(con); // 작업을 수행 후 닫아준다.
		
		return removeSuccess;
	}

	}


