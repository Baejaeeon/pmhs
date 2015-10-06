package pmhs.web.message.svc;

import java.sql.Connection;

import pmhs.web.message.dao.MessageDAO;

import static pmhs.db.JdbcUtil.*;

public class MessageDeleteService {

	public boolean removeArticle(String[] deleteArray) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
	    MessageDAO messageDAO = MessageDAO.getInstance();
	    messageDAO.setConnection(con);
	    boolean isRemoveSuccess = false;
	    int deleteCount = messageDAO.deleteMessageArticle(deleteArray);
	    if(deleteCount>0){
	       commit(con);
	       isRemoveSuccess=true;
	    }else{
	       rollback(con);
	    }
	    return isRemoveSuccess;
	}

}
