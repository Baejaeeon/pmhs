package pmhs.web.sms.dao;

import java.sql.Connection;

public class SMSDAO {
	private static SMSDAO instance;
	private Connection con;

	public SMSDAO() {
		// TODO Auto-generated constructor stub
	}

	public static SMSDAO getInstance() {
		if (instance == null) {
			instance = new SMSDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public static int sendSMSArticle(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
