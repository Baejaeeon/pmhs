package pmhs.web.board.dao;

import java.sql.Connection;

public class BoardDAO {
	private static BoardDAO instance;
	private Connection con;

	public BoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
}
