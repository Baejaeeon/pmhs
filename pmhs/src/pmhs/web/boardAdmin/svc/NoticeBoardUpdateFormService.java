package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.NoticeBoardVO;

public class NoticeBoardUpdateFormService {
	public NoticeBoardVO getUpdateNoticeArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con);
		
		NoticeBoardVO article = boardAdminDAO.selectUpdateNoticeArticle(num);
		close(con); 
		
		return article;
	}
}
