package pmhs.web.pcInfo.dao;

import java.sql.Connection;

public class PCInfoDAO {
	private static PCInfoDAO instance;
	private Connection con;

	public PCInfoDAO() {
		// TODO Auto-generated constructor stub
	}

	public static PCInfoDAO getInstance() {
		if (instance == null) {
			instance = new PCInfoDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
}
