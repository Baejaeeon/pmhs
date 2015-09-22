package pmhs.web.message.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import static pmhs.db.JdbcUtil.*;
import pmhs.web.message.vo.MessageVO;

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

	public int insertMessage(MessageVO sendMessage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "INSERT INTO message VALUES(message_seq.nextval,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendMessage.getMessageWriter());
			pstmt.setString(2, sendMessage.getTitle());
	        pstmt.setString(3, sendMessage.getMessageText());
	        pstmt.setString(4, sendMessage.getMessageReceiver());
	        pstmt.setTimestamp(5, sendMessage.getMessageReg_date());
            insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
}
