package pmhs.web.message.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
	   
	   public List<MessageVO> selectReceiveList(String m_name) {
		   // TODO Auto-generated method stub
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   List<MessageVO> receiveList = null;
		   MessageVO receive = new MessageVO();
		   String sql = "SELECT * FROM messageInfo WHERE msg_receiver = ? ORDER BY msg_date desc";
		   try {
			   pstmt = con.prepareStatement(sql);
			   pstmt.setString(1, m_name);
			   rs = pstmt.executeQuery();
			   if(rs.next()){
				   receiveList = new ArrayList<MessageVO>();
				   do {
					   receive.setMessageNum(rs.getInt("msg_num"));
					   receive.setMessageReceiver(rs.getString("msg_receiver"));
					   receive.setTitle(rs.getString("msg_title"));
					   receive.setMessageReg_date(rs.getTimestamp("msg_date"));
					   receive.setMessageContent(rs.getString("msg_content"));
					   receive.setMessageWriter(rs.getString("m_id"));
					   receiveList.add(receive);
				   } while (rs.next());
			   }
		   } catch (Exception e) {
			   e.printStackTrace();
		   }finally{
			   close(pstmt);
			   close(rs);
		   }
		   return receiveList;
	   }
	   
	   public int insertMessage(MessageVO sendMessage) {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      int insertCount = 0;
	      String sql = "INSERT INTO messageInfo VALUES(messageInfo_seq.nextval,?,?,?,?,?)";
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, sendMessage.getMessageReceiver());
	         pstmt.setString(2, sendMessage.getTitle());
	         pstmt.setTimestamp(3, sendMessage.getMessageReg_date());
	         pstmt.setString(4, sendMessage.getMessageContent());
	         pstmt.setString(5, sendMessage.getMessageWriter());
	            insertCount = pstmt.executeUpdate();
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return insertCount;
	   }

	   public MessageVO selectArticleContent(int num) {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      MessageVO article = null;
	      try {
	         pstmt = con.prepareStatement("SELECT * FROM messageInfo WHERE msg_num = ?"); // 선택한 글의 번호를 가져온다.
	         pstmt.setInt(1, num);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) { 
	            // 글이 하나라도 있으면...
	               article = new MessageVO();
	               article.setMessageNum(rs.getInt("massageNum"));
	               article.setMessageWriter(rs.getString("messageWriter"));
	               article.setTitle(rs.getString("Title"));
	               article.setMessageContent(rs.getString("messageContent"));
	               article.setMessageReg_date(rs.getTimestamp("messageReg_date"));
	               article.setMessageReceiver(rs.getString("messageReceiver"));
	         }
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      return article;
	   }

	   public int deleteMessageArticle(int messageNum) {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      int deleteCount = 0;
	      String sql = "delete from messageInfo where msg_num = ?";
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, messageNum);
	         deleteCount=pstmt.executeUpdate();

	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally{
	         close(pstmt);
	      }
	      return deleteCount;
	   }

}
