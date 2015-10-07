package pmhs.web.sms.svc;

import static pmhs.db.JdbcUtil.commit;
import static pmhs.db.JdbcUtil.getConnect;
import static pmhs.db.JdbcUtil.rollback;

import java.sql.Connection;

import pmhs.web.message.dao.MessageDAO;
import pmhs.web.sms.dao.SMSDAO;
import pmhs.web.sms.gcm.server.GCMTest;

public class SMSSendProService {

	//public boolean sendSuccess(String content) {
//		
//		// TODO Auto-generated method stub
//		Connection con = getConnect();
//	    MessageDAO messageDAO = MessageDAO.getInstance();
//	    messageDAO.setConnection(con);
//		System.out.println("222");
//
//		GCMTest gcmTest = new GCMTest(content);
//
//	    return gcmTest;
//	  
//}
}
