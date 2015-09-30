package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;
import java.util.List;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.QnABoardVO;


public class QnABoardListService {

	public int getQnAArticleCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		int articleCount = boardAdminDAO.selectQnAArticleCount();
		
		close(con);
		
		return articleCount;
	}

	public List<QnABoardVO> getQnAArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		
		List<QnABoardVO> articleList = boardAdminDAO.selectQnAArticleList(startRow, pageSize);
		close(con);
		
		return articleList;	
		}

}
