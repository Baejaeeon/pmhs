package pmhs.web.message.svc;

import static pmhs.db.JdbcUtil.close;
import static pmhs.db.JdbcUtil.getConnect;

import java.sql.Connection;

import pmhs.web.message.dao.MessageDAO;
import pmhs.web.message.vo.MessageVO;

public class MessageSendContentService {

	public MessageVO getArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MessageDAO messageDAO = MessageDAO.getInstance();
		messageDAO.setConnection(con);
      
		MessageVO articleContent = messageDAO.selectArticleContent(num);
		close(con);
      
		return articleContent;
	}

}
