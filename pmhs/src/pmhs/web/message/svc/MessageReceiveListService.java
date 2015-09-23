package pmhs.web.message.svc;

import java.sql.Connection;
import java.util.List;

import pmhs.web.message.dao.MessageDAO;
import pmhs.web.message.vo.MessageVO;
import static pmhs.db.JdbcUtil.*;

public class MessageReceiveListService {
	   
	public int getArticleCount() {
	      // TODO Auto-generated method stub
	      Connection con = getConnect();
	      MessageDAO messageDAO = MessageDAO.getInstance();
	      messageDAO.setConnection(con);
	      int receiveCount = messageDAO.selectReceiveCount();
	      close(con);
	      return receiveCount;
	   }

	   public List<MessageVO> getReceiveList(int startRow, int pageSize) {
	      // TODO Auto-generated method stub
	      Connection con = getConnect();
	      MessageDAO messageDAO = MessageDAO.getInstance();
	      messageDAO.setConnection(con);
	      List<MessageVO> receiveList = messageDAO.selectReceiveList(startRow, pageSize);
	      close(con);
	      return receiveList;
	   }
	}
