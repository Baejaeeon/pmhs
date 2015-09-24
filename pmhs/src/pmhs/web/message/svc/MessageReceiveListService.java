package pmhs.web.message.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import pmhs.web.message.dao.MessageDAO;
import pmhs.web.message.vo.MessageVO;

import static pmhs.db.JdbcUtil.*;

public class MessageReceiveListService {

	public List<MessageVO> getReceiveList(String m_name) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MessageDAO messageDAO = MessageDAO.getInstance();
		messageDAO.setConnection(con);
		
		List<MessageVO> receiveList = messageDAO.selectReceiveList(m_name);
		close(con);
		return receiveList;
	}
}
