package pmhs.web.memberAdmin.dao;

import java.sql.Connection;

public class MemberAdminDAO {
	private static MemberAdminDAO instance;
	private Connection con;

	public MemberAdminDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberAdminDAO getInstance() {
		if (instance == null) {
			instance = new MemberAdminDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
}
