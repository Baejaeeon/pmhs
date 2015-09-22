package pmhs.web.board.svc;

import java.sql.Connection;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardUpdateProService {

	public boolean modifyQnAArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
			Connection con = getConnect(); // 커넥션 객체 생성
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con); // 커넥션 주입
			
			boolean updateSuccess = false;
			// DB에서 업데이트 수행
			int updateCount = boardDAO.updateQnAArticle(article);
			if(updateCount > 0) {
				commit(con);
				updateSuccess = true;
			} else {
				rollback(con);
			}
			close(con); // 작업을 수행 후 닫아준다.
			
			return updateSuccess;
		}
	}


