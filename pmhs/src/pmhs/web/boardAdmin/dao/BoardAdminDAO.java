package pmhs.web.boardAdmin.dao;

import java.sql.Connection;

public class BoardAdminDAO {
	private static BoardAdminDAO instance;
	private Connection con;

	public BoardAdminDAO() {
		// TODO Auto-generated constructor stub
	}

	public static BoardAdminDAO getInstance() {
		if (instance == null) {
			instance = new BoardAdminDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
}
