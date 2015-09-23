package pmhs.web.member.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.member.dao.*;

public class IdCheckService {

	public boolean existsId(String id) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean idExists = false;
		
		int idCount = memberDAO.selectIdCount(id);
		if(idCount > 0) {
			idExists = true;
		}
		
		close(con); // Ä¿³Ø¼Ç ´Ý±â
		
		return idExists;
	}
	
}
