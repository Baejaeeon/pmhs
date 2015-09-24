package pmhs.web.member.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.member.dao.MemberDAO;
import pmhs.web.member.vo.Member;

public class LoginService {

	public Member getLoginUser(String id, String passwd) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		Member loginMember = memberDAO.selectLoginMember(id, passwd);
		close(con);
		return loginMember;
	}

}
