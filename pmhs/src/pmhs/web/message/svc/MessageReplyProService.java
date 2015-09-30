package pmhs.web.message.svc;

import static pmhs.db.JdbcUtil.commit;
import static pmhs.db.JdbcUtil.getConnect;
import static pmhs.db.JdbcUtil.rollback;

import java.sql.Connection;

import pmhs.web.message.dao.MessageDAO;
import pmhs.web.message.vo.MessageVO;

public class MessageReplyProService {

	public boolean replyMessage(MessageVO replyMessage) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MessageDAO messageDAO = MessageDAO.getInstance();
		messageDAO.setConnection(con);
		boolean isSendSuccess = false;
		int insertCount = messageDAO.insertMessage(replyMessage);
		if(insertCount > 0 ){
			commit(con);
			isSendSuccess = true;
		}else{
			rollback(con);
		}
		return isSendSuccess;
	}

}
