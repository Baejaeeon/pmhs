package pmhs.web.board.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.commit;
import static pmhs.db.JdbcUtil.getConnect;
import static pmhs.db.JdbcUtil.rollback;

import java.sql.Connection;

import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.CommentVO;

public class CommentDeleteService {

	public boolean removeComment(String c_writer, String c_content) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con); 
		
		boolean removeSuccess = false;
		int deleteCount = boardDAO.deleteComment(c_writer,c_content);
		if(deleteCount > 0) {
			commit(con);
			removeSuccess = true;
		} else {
			rollback(con);
		}
		close(con); 
		
		return removeSuccess;
	}
}


