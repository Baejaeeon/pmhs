package pmhs.web.member.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.member.dao.MemberDAO;
import pmhs.web.member.vo.Member;

public class MemberDetailService {

	public Member getMember(String id) {
		// TODO Auto-generated method stub
		Connection con = getConnect();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con); // Ŀ�ؼ� ����
		Member member = memberDAO.selectMember(id);
		close(con);
		return member;
	}

}
