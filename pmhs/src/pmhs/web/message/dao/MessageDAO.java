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

	   public int insertMessage(MessageVO sendMessage) {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      int insertCount = 0;
	      String sql = "INSERT INTO messageInfo VALUES(message_seq.nextval,?,?,?,?,?)";
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(4, sendMessage.getMessageReceiver());
	         pstmt.setString(2, sendMessage.getTitle());
	         pstmt.setTimestamp(5, sendMessage.getMessageReg_date());
	         pstmt.setString(3, sendMessage.getMessageContent());
	         pstmt.setString(1, sendMessage.getMessageWriter());
	            insertCount = pstmt.executeUpdate();
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return insertCount;
	   }

	   public int selectReceiveCount() {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int receiveCount = 0;
	      try {
	         pstmt = con.prepareStatement("SELECT COUNT(*) FROM messageInfo"); // �� ���ڵ��� ������ ������ �����´�.
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	         receiveCount = rs.getInt(1); // �� ���� ������ articleCount������ ����
	         }
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      
	      return receiveCount;
	   }

	   public List<MessageVO> selectReceiveList(int startRow, int pageSize) {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<MessageVO> receiveList = null;
	      MessageVO receive = null; // �� �ϳ��� ������ ������ ����
	      
	      try {
	         // �߿��� SQL ����
	         // ���ǿ� �´� �ش� �������� ��µ� ���ڵ� 10�� ��ȸ�ϴ� SQL����(lis2, list1 ��Ī ���)
	         // subQuery �̿�
	         // ���� ���� �� FROM �� �ڿ� �����Ǵ� ���������� "�ζ��κ�"��� �Ѵ�. = �ζ��� ��� ��Ī�� �ʿ��ϴ�.
	         pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
	               + "FROM (SELECT * FROM messageInfo ORDER BY msg_num DESC) list1 ) list2 "
	               + "WHERE r BETWEEN ? AND ?");
	         // ���ڵ� ��ȣ�� ������ �ֱ� ���� rownum(���ڵ��ȣ)�� ����Ѵ�.
	         // ()���� DESC�� ������ �����ͼ� ����ϸ鼭 rownum�� �ٿ��ش�.
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, startRow + pageSize - 1); // 1������ �� ��� 1�� ���� 10�� �� ���ڵ带 �����´�.
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) { 
	            // ���� �ϳ��� ������...
	            receiveList = new ArrayList<MessageVO>(); // �� ����� ������ �÷����� ����.
	            do {
	               receive = new MessageVO();
	               receive.setMessageNum(rs.getInt("msg_num"));
	               receive.setMessageReceiver(rs.getString("msg_receiver"));
	               receive.setTitle(rs.getString("msg_title"));
	               receive.setMessageReg_date(rs.getTimestamp("msg_date"));
	               receive.setMessageContent(rs.getString("msg_content"));
	               receive.setMessageWriter(rs.getString("m_id"));
	               receiveList.add(receive); // �ش� �������� ��µ� �� ���� ���� articleList�� ����
	            } while (rs.next());
	         }
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      
	      return receiveList;
	   }

	   public MessageVO selectArticleContent(int num) {
	      // TODO Auto-generated method stub
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      MessageVO article = null;
	      try {
	         pstmt = con.prepareStatement("SELECT * FROM messageInfo WHERE msg_num = ?"); // ������ ���� ��ȣ�� �����´�.
	         pstmt.setInt(1, num);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) { 
	            // ���� �ϳ��� ������...
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
