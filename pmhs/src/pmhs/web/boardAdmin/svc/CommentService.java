package pmhs.web.boardAdmin.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.CommentVO;

public class CommentService {

	public boolean registComment(CommentVO commentVO) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con);
		boolean isRegistSuccess = false;
		int insertCount = boardAdminDAO.insertComment(commentVO);
		if (insertCount>0) {
			commit(con);
			isRegistSuccess = true;
		}else{
			rollback(con);
		}
		return isRegistSuccess;
	}
	

	public ArrayList<CommentVO> selectqnaReplyList(int num) {

		Connection con = getConnect();
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con);
		ArrayList<CommentVO> commentList = boardAdminDAO.selectBoardCommentList(num);
		close(con);
		return commentList;
	}
	

}
