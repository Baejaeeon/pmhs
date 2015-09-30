package pmhs.web.board.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static pmhs.db.JdbcUtil.*;
import pmhs.web.board.dao.BoardDAO;
import pmhs.web.board.vo.CommentVO;

public class CommentService {

	public boolean registComment(CommentVO commentVO) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boolean isRegistSuccess = false;
		int insertCount = boardDAO.insertComment(commentVO);
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
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ArrayList<CommentVO> commentList = boardDAO.selectcommentList(num);
		close(con);
		return commentList;
	}
	

}
