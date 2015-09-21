package pmhs.web.member.dao;

import java.sql.Connection;

public class MemberDAO {
	private static MemberDAO instance;
	private Connection con;

	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
}
