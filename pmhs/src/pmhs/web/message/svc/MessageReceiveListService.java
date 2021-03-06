package pmhs.web.message.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;
import pmhs.web.message.dao.MessageDAO;
import pmhs.web.message.vo.MessageVO;


public class MessageReceiveListService {

	public int getReceiveCount() {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MessageDAO messageDAO = MessageDAO.getInstance();
		messageDAO.setConnection(con);
		int receiveCount = messageDAO.selectReceiveCount();
		close(con);
		return receiveCount;
	}
	
	public List<MessageVO> getReceiveList(String m_id, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MessageDAO messageDAO = MessageDAO.getInstance();
		messageDAO.setConnection(con);
		
		List<MessageVO> receiveList = messageDAO.selectReceiveList(m_id, startRow, pageSize);
		close(con);
		return receiveList;
	}

}
