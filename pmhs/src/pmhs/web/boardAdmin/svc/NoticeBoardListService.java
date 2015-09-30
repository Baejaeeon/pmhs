package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;
import java.util.List;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.NoticeBoardVO;


public class NoticeBoardListService {

	public int getNoticeArticleCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con);
		int articleCount = boardAdminDAO.selectNoticeArticleCount();
		
		close(con);
		
		return articleCount;
	}

	public List<NoticeBoardVO> getNoticeArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect(); 
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		
		List<NoticeBoardVO> articleList = boardAdminDAO.selectNoticeArticleList(startRow, pageSize);
		
		close(con);
		
		return articleList;
	}
	

}
