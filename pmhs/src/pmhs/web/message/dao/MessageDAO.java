package pmhs.web.message.dao;

import java.sql.Connection;

public class MessageDAO {
	private static MessageDAO instance;
	private Connection con;

	public MessageDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MessageDAO getInstance() {
		if (instance == null) {
			instance = new MessageDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
}
