package pmhs.web.board.dao;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.RowMapper;

import pmhs.web.board.vo.CommentVO;
import pmhs.web.board.vo.NoticeBoardVO;
import pmhs.web.board.vo.QnABoardVO;

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

	public int selectQnAArticleCount() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int articleCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM qnABoard"); 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleCount = rs.getInt(1); 
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleCount;
	}

	public List<QnABoardVO> selectQnAArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnABoardVO> articleList = null;
		QnABoardVO article = null; 
		try {
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM qnABoard ORDER BY q_ref DESC, q_re_step ASC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				articleList = new ArrayList<QnABoardVO>(); 
				do {
					article = new QnABoardVO();
					article.setNum(rs.getInt("q_num"));
					article.setWriter(rs.getString("q_writer"));
					article.setEmail(rs.getString("q_email"));
					article.setSubject(rs.getString("q_subject"));
					article.setPasswd(rs.getString("q_passwd"));
					article.setReg_date(rs.getTimestamp("q_reg_date"));
					article.setReadCount(rs.getInt("q_readCount"));
					article.setRef(rs.getInt("q_ref"));
					article.setRe_step(rs.getInt("q_re_step"));
					article.setRe_level(rs.getInt("q_re_level"));
					article.setContent(rs.getString("q_content"));
					article.setIp(rs.getString("q_ip"));
					articleList.add(article);
				} while (rs.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	public QnABoardVO selectQnAArticle(int num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnABoardVO article = null;
		try {
			pstmt = con.prepareStatement("UPDATE qnABoard SET q_readcount = q_readcount + 1 WHERE q_num = ?");
			
			pstmt.setInt(1, num);
			int updateCount = pstmt.executeUpdate(); 
			if(updateCount > 0) {
				commit(con);
			}
			
			
			pstmt = con.prepareStatement("SELECT * FROM qnABoard WHERE q_num = ?"); // 선택한 글의 번호를 가져온다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				article = new QnABoardVO();
				article.setNum(rs.getInt("q_num"));
				article.setWriter(rs.getString("q_writer"));
				article.setEmail(rs.getString("q_email"));
				article.setSubject(rs.getString("q_subject"));
				article.setPasswd(rs.getString("q_passwd"));
				article.setReg_date(rs.getTimestamp("q_reg_date"));
				article.setReadCount(rs.getInt("q_readCount"));
				article.setRef(rs.getInt("q_ref"));
				article.setRe_step(rs.getInt("q_re_step"));
				article.setRe_level(rs.getInt("q_re_level"));
				article.setContent(rs.getString("q_content"));
				article.setIp(rs.getString("q_ip"));
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

	public int insertQnAArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 0;
		String sql = "";
		
		 try {
			 pstmt = con.prepareStatement("SELECT MAX(q_num) FROM qnABoard");
			 rs = pstmt.executeQuery();
			 if(rs.next()) { 
				 number = rs.getInt(1) + 1; 
			 } else {
				 number = 1; 
			 }
			 
			 if(num != 0) {
				 sql = "UPDATE qnABoard SET q_re_step = q_re_step + 1"
				 		+ " WHERE q_ref = ? AND q_re_step > ?";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, ref);
				 pstmt.setInt(2, re_step);
				 pstmt.executeUpdate();
				 re_step = re_step + 1; 
				 re_level = re_level + 1;
			 } else { 
				 ref = number;
				 re_step = 0;
				 re_level = 0;
			 }
			 
			 sql = "INSERT INTO qnABoard"
			 		+ "(q_num, q_writer, q_email, q_subject, q_passwd, q_reg_date, "
			 		+ "q_ref, q_re_step, q_re_level, q_content, q_ip) "
			 		+ "VALUES(qnABoard_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, article.getWriter());
			 pstmt.setString(2, article.getEmail());
			 pstmt.setString(3, article.getSubject());
			 pstmt.setString(4, article.getPasswd());
			 pstmt.setTimestamp(5, article.getReg_date());
			 pstmt.setInt(6, ref);
			 pstmt.setInt(7, re_step);
			 pstmt.setInt(8, re_level);
			 pstmt.setString(9, article.getContent());
			 pstmt.setString(10, article.getIp());
			 insertCount = pstmt.executeUpdate();
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		 
		 return insertCount;
	}

	public QnABoardVO selectUpdateQnAArticle(int num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnABoardVO article = null;
		try {
			
			pstmt = con.prepareStatement("SELECT * FROM qnABoard WHERE q_num = ?"); // 선택한 글의 번호를 가져온다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) { 
		
					article = new QnABoardVO();
					article.setNum(rs.getInt("q_num"));
					article.setWriter(rs.getString("q_writer"));
					article.setEmail(rs.getString("q_email"));
					article.setSubject(rs.getString("q_subject"));
					article.setPasswd(rs.getString("q_passwd"));
					article.setReg_date(rs.getTimestamp("q_reg_date"));
					article.setReadCount(rs.getInt("q_readCount"));
					article.setRef(rs.getInt("q_ref"));
					article.setRe_step(rs.getInt("q_re_step"));
					article.setRe_level(rs.getInt("q_re_level"));
					article.setContent(rs.getString("q_content"));
					article.setIp(rs.getString("q_ip"));
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

	public int updateQnAArticle(QnABoardVO article) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPasswd = ""; 
		String sql = ""; 
		int updateCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT q_passwd FROM qnABoard WHERE q_num = ?");
			pstmt.setInt(1, article.getNum()); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				dbPasswd = rs.getString("q_passwd"); 
				if(dbPasswd.equals(article.getPasswd())) {
					sql = "UPDATE qnABoard SET q_writer = ?, q_email = ?, q_subject = ?, q_passwd = ?, "
							+ "q_content = ? WHERE q_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter()); 
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPasswd());
					pstmt.setString(5, article.getContent());
					pstmt.setInt(6, article.getNum());
					
					updateCount = pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteQnAArticle(int num, String passwd) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPasswd = ""; 
		String sql = ""; 
		int deleteCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT q_passwd FROM qnABoard WHERE q_num = ?"); 
			pstmt.setInt(1, num); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				dbPasswd = rs.getString("q_passwd"); 
				if(dbPasswd.equals(passwd)) { 
					sql = "DELETE qnABoard WHERE q_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num); 
					deleteCount = pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return deleteCount;
	}

	public int selectNoticeArticleCount() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int articleCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM noticeBoard"); 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleCount = rs.getInt(1); 
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleCount;
	}

	public List<NoticeBoardVO> selectNoticeArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeBoardVO> articleList = null;
		NoticeBoardVO article = null; 
		
		try {
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM noticeBoard) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				articleList = new ArrayList<NoticeBoardVO>(); 
				do {
					article = new NoticeBoardVO();
					article.setNum(rs.getInt("n_num"));
					article.setWriter(rs.getString("n_writer"));
					article.setEmail(rs.getString("n_email"));
					article.setSubject(rs.getString("n_subject"));
					article.setPasswd(rs.getString("n_passwd"));
					article.setReg_date(rs.getTimestamp("n_reg_date"));
					article.setReadCount(rs.getInt("n_readCount"));
					article.setContent(rs.getString("n_content"));
					article.setIp(rs.getString("n_ip"));
					articleList.add(article); 
				} while (rs.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	public NoticeBoardVO selectNoticeArticle(int num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeBoardVO article = null;
		try {
			pstmt = con.prepareStatement("UPDATE noticeBoard SET n_readcount = n_readcount + 1 WHERE n_num = ?");
			
			pstmt.setInt(1, num);
			int updateCount = pstmt.executeUpdate();
			if(updateCount > 0) {
				commit(con);
			}
			
			
			pstmt = con.prepareStatement("SELECT * FROM noticeBoard WHERE n_num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 글이 하나라도 있으면...
					article = new NoticeBoardVO();
					article.setNum(rs.getInt("n_num"));
					article.setWriter(rs.getString("n_writer"));
					article.setEmail(rs.getString("n_email"));
					article.setSubject(rs.getString("n_subject"));
					article.setPasswd(rs.getString("n_passwd"));
					article.setReg_date(rs.getTimestamp("n_reg_date"));
					article.setReadCount(rs.getInt("n_readCount"));
					article.setContent(rs.getString("n_content"));
					article.setIp(rs.getString("n_ip"));
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

	public int insertComment(CommentVO commentVO) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "insert into commentInfo values (comment_seq.nextval,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commentVO.getC_content());
			pstmt.setTimestamp(2, commentVO.getC_reg_date());
			pstmt.setInt(3, commentVO.getC_num());
	     	pstmt.setString(4, commentVO.getC_writer());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList<CommentVO> selectcommentList(int num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ArrayList<CommentVO> commentList = null;

		try {
			pstmt = con.prepareStatement("SELECT * FROM commentInfo WHERE q_num= ? ORDER BY c_reg_date desc");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
		
			if(rs.next()){
				commentList = new ArrayList<CommentVO>();
				CommentVO comment = null;
			
				do{
					comment = new CommentVO(rs.getInt("c_seq"), 
							rs.getString("m_id"),
							rs.getString("c_content"),
							rs.getTimestamp("c_reg_date")); 	
					commentList.add(comment);
			}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rs);
		}
		return commentList;
	}
	
	
	public int deleteComment(int c_num) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		int deleteCount = 0;
		
		try {
			pstmt = con.prepareStatement("DELETE FROM commentInfo WHERE c_seq = ?"); 
			pstmt.setInt(1, c_num); 
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public List<NoticeBoardVO> selectNoticeArticleList(int startRow) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeBoardVO> articleList = null;
		NoticeBoardVO article = null; 
		
		try {
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM noticeBoard) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + 5); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				articleList = new ArrayList<NoticeBoardVO>(); 
				do {
					article = new NoticeBoardVO();
					article.setNum(rs.getInt("n_num"));
					article.setWriter(rs.getString("n_writer"));
					article.setEmail(rs.getString("n_email"));
					article.setSubject(rs.getString("n_subject"));
					article.setPasswd(rs.getString("n_passwd"));
					article.setReg_date(rs.getTimestamp("n_reg_date"));
					article.setReadCount(rs.getInt("n_readCount"));
					article.setContent(rs.getString("n_content"));
					article.setIp(rs.getString("n_ip"));
					articleList.add(article); 
				} while (rs.next());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
}


	
	
	
	
	


