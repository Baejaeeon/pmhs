package pmhs.web.boardAdmin.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.commit;
import static pmhs.db.JdbcUtil.getConnect;
import static pmhs.db.JdbcUtil.rollback;

import java.sql.Connection;

import pmhs.web.boardAdmin.dao.BoardAdminDAO;
import pmhs.web.boardAdmin.vo.NoticeBoardVO;

public class NoticeBoardWriteProService {

	public boolean writeNoticeArticle(NoticeBoardVO article) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		BoardAdminDAO boardAdminDAO = BoardAdminDAO.getInstance();
		boardAdminDAO.setConnection(con); 
		
		boolean writeSuccess = false;
		int insertCount = boardAdminDAO.insertNoticeArticle(article);
		if(insertCount > 0) {
			commit(con);
			writeSuccess = true;
		} else {
			rollback(con);
		}
		close(con); 
		
		return writeSuccess;
	}

}
