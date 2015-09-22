package pmhs.web.member.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.member.dao.MemberDAO;
import pmhs.web.member.vo.Member;

public class MemberRegistService {

	public boolean registMember(Member member) {
		// TODO Auto-generated method stub
		boolean registSuccess = false;

		Connection con = getConnect();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int insertCount = memberDAO.insertMember(member);
		if(insertCount > 0) {
			registSuccess = true;
			commit(con); // 성공하면 커밋
		} else {
			rollback(con); // 실패하면 롤백
		}
		close(con); // 클로즈(close)는 DAO가 아니라 서비스에서 처리해주어야 한다. 
		
		return registSuccess;
	
	}

}
