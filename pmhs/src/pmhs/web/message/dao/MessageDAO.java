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
	         pstmt = con.prepareStatement("SELECT COUNT(*) FROM messageInfo"); // 총 레코드의 개수를 실행해 가져온다.
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	         receiveCount = rs.getInt(1); // 총 글의 개수를 articleCount변수에 저장
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
	      MessageVO receive = null; // 글 하나의 정보를 저장할 변수
	      
	      try {
	         // 중요한 SQL 구문
	         // 조건에 맞는 해당 페이지에 출력될 레코드 10개 조회하는 SQL구문(lis2, list1 별칭 사용)
	         // subQuery 이용
	         // 서브 쿼리 중 FROM 절 뒤에 제공되는 서브쿼리를 "인라인뷰"라고 한다. = 인라인 뷰는 별칭이 필요하다.
	         pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
	               + "FROM (SELECT * FROM messageInfo ORDER BY msg_num DESC) list1 ) list2 "
	               + "WHERE r BETWEEN ? AND ?");
	         // 레코드 번호로 조건을 주기 위해 rownum(레코드번호)을 사용한다.
	         // ()안의 DESC된 값들을 가져와서 출력하면서 rownum을 붙여준다.
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, startRow + pageSize - 1); // 1페이지 일 경우 1번 부터 10번 글 레코드를 가져온다.
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) { 
	            // 글이 하나라도 있으면...
	            receiveList = new ArrayList<MessageVO>(); // 글 목록을 저장할 컬렉션을 만듬.
	            do {
	               receive = new MessageVO();
	               receive.setMessageNum(rs.getInt("msg_num"));
	               receive.setMessageReceiver(rs.getString("msg_receiver"));
	               receive.setTitle(rs.getString("msg_title"));
	               receive.setMessageReg_date(rs.getTimestamp("msg_date"));
	               receive.setMessageContent(rs.getString("msg_content"));
	               receive.setMessageWriter(rs.getString("m_id"));
	               receiveList.add(receive); // 해당 페이지에 출력될 글 정보 만들어서 articleList에 저장
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
