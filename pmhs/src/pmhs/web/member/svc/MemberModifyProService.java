package pmhs.web.member.svc;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;

import pmhs.web.member.dao.MemberDAO;
import pmhs.web.member.vo.Member;

public class MemberModifyProService {

	public boolean modifyMember(Member member) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con); // Ä¿³Ø¼Ç ÁÖÀÔ
		boolean modifySuccess = false;
		int updateCount = memberDAO.updateMember(member);
		if(updateCount > 0){
			modifySuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		
	
		return modifySuccess;
	}

	

	}


