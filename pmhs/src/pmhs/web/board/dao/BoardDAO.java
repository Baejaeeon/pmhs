package pmhs.web.board.dao;

import static pmhs.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM qnABoard"); // 총 레코드의 개수를 실행해 가져온다.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleCount = rs.getInt(1); // 총 글의 개수를 articleCount변수에 저장
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
		QnABoardVO article = null; // 글 하나의 정보를 저장할 변수
		
		try {
			// 중요한 SQL 구문
			// 조건에 맞는 해당 페이지에 출력될 레코드 10개 조회하는 SQL구문(lis2, list1 별칭 사용)
			// subQuery 이용
			// 서브 쿼리 중 FROM 절 뒤에 제공되는 서브쿼리를 "인라인뷰"라고 한다. = 인라인 뷰는 별칭이 필요하다.
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM qnABoard ORDER BY q_ref DESC, q_re_step ASC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			// 레코드 번호로 조건을 주기 위해 rownum(레코드번호)을 사용한다.
			// ()안의 DESC된 값들을 가져와서 출력하면서 rownum을 붙여준다.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); // 1페이지 일 경우 1번 부터 10번 글 레코드를 가져온다.
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 글이 하나라도 있으면...
				articleList = new ArrayList<QnABoardVO>(); // 글 목록을 저장할 컬렉션을 만듬.
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
					articleList.add(article); // 해당 페이지에 출력될 글 정보 만들어서 articleList에 저장
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
			
			// 조회수 증가 처리
			// 지금 파라미터로 넘어온 num값과 동일한 값을 업데이트
			pstmt = con.prepareStatement("UPDATE qnABoard SET readcount = readcount + 1 WHERE num = ?");
			
			pstmt.setInt(1, num);
			int updateCount = pstmt.executeUpdate(); // 조회수 증가
			if(updateCount > 0) {
				commit(con);
			}
			
			
			pstmt = con.prepareStatement("SELECT * FROM qnABoard WHERE q_num = ?"); // 선택한 글의 번호를 가져온다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 글이 하나라도 있으면...
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
		
		// 답변글 처리할 때 필요한 속성값들 변수에 저장
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		
		int number = 0;
		// 원글을 작성했을 때 새롭게 부여될 관련글 번호를 저장할 변수
		String sql = "";
		
		 try { // DB작업은 예외처리 해준다.
			 // 작성한 글이 원글일 때 새로운 관련글 번호를 구하는 부분
			 pstmt = con.prepareStatement("SELECT MAX(num) FROM qnABoard");
			 // board 테이블의 현재 num의 최대값 가져온다.
			 rs = pstmt.executeQuery();
			 if(rs.next()) { 
				// 이전에 작성된 글이 있으면...
				// 지금 작성한 글이 첫번째 글이 아니면..
				 number = rs.getInt(1) + 1; // 가져온 최대값에 1을 더해서 number에 넣어준다.
			 } else {
				 number = 1; // 첫번째 글이면 1로 세팅
			 }
			 
			 if(num != 0) {
				 // 답변글이면...
				 sql = "UPDATE qnABoard SET q_re_step = q_re_step + 1"
				 		+ " WHERE q_ref = ? AND q_re_step > ?";
				 // 지금 답변을 달려는 대상글의 ref와 같고 re_step보다 크면 업데이트
				 pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, ref);
				 pstmt.setInt(2, re_step);
				 pstmt.executeUpdate();
				 re_step = re_step + 1;  // 각각 증가시켜준다.
				 re_level = re_level + 1;
			 } else { // 지금 작성한 글이 원글이면...
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
				// 글이 하나라도 있으면...
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
		
		String dbPasswd = ""; // 데이터베이스에서 가져온 비밀번호를 저장할 변수
		String sql = ""; // sql구문을 저장할 변수
		int updateCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT q_passwd FROM qnABoard WHERE q_num = ?");
			// 조건에서 명시한 글번호의 비밀번호를 DB에서 가져온다.
			pstmt.setInt(1, article.getNum()); // 파라미터로 넘어온 article객체의 num값 바인딩(수정할 대상의 글)
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 해당 글이 있으면...
				dbPasswd = rs.getString("q_passwd"); // DB에 저장된 비밀번호 할당
				if(dbPasswd.equals(article.getPasswd())) { // 비밀번호가 같으면... 수정 작업 처리
					sql = "UPDATE qnABoard SET q_writer = ?, q_email = ?, q_subject = ?, q_passwd = ?, "
							+ "q_content = ? WHERE q_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter()); // 각각을 바인딩 시켜준다.
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
		
		String dbPasswd = ""; // 데이터베이스에서 가져온 비밀번호를 저장할 변수
		String sql = ""; // sql구문을 저장할 변수
		int deleteCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT q_passwd FROM qnABoard WHERE q_num = ?"); // 선택한 글의 번호를 가져온다.
			pstmt.setInt(1, num); // 파라미터로 넘어온 article객체의 num값 바인딩(수정할 대상의 글)
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 해당 글이 있으면...
				dbPasswd = rs.getString("q_passwd"); // DB에 저장된 비밀번호 할당
				if(dbPasswd.equals(passwd)) { // 비밀번호가 같으면... 삭제 작업 처리
					sql = "DELETE qnABoard WHERE q_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num); // 각각을 바인딩 시켜준다.
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
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM noticeBoard"); // 총 레코드의 개수를 실행해 가져온다.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleCount = rs.getInt(1); // 총 글의 개수를 articleCount변수에 저장
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
		NoticeBoardVO article = null; // 글 하나의 정보를 저장할 변수
		
		try {
			// 중요한 SQL 구문
			// 조건에 맞는 해당 페이지에 출력될 레코드 10개 조회하는 SQL구문(lis2, list1 별칭 사용)
			// subQuery 이용
			// 서브 쿼리 중 FROM 절 뒤에 제공되는 서브쿼리를 "인라인뷰"라고 한다. = 인라인 뷰는 별칭이 필요하다.
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT rownum r, list1.* "
					+ "FROM (SELECT * FROM noticeBoard ORDER BY n_ref DESC, n_re_step ASC) list1 ) list2 "
					+ "WHERE r BETWEEN ? AND ?");
			// 레코드 번호로 조건을 주기 위해 rownum(레코드번호)을 사용한다.
			// ()안의 DESC된 값들을 가져와서 출력하면서 rownum을 붙여준다.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1); // 1페이지 일 경우 1번 부터 10번 글 레코드를 가져온다.
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// 글이 하나라도 있으면...
				articleList = new ArrayList<NoticeBoardVO>(); // 글 목록을 저장할 컬렉션을 만듬.
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
					articleList.add(article); // 해당 페이지에 출력될 글 정보 만들어서 articleList에 저장
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
			
			// 조회수 증가 처리
			// 지금 파라미터로 넘어온 num값과 동일한 값을 업데이트
			pstmt = con.prepareStatement("UPDATE noticeBoard SET n_readcount = n_readcount + 1 WHERE n_num = ?");
			
			pstmt.setInt(1, num);
			int updateCount = pstmt.executeUpdate(); // 조회수 증가
			if(updateCount > 0) {
				commit(con);
			}
			
			
			pstmt = con.prepareStatement("SELECT * FROM board WHERE num = ?"); // 선택한 글의 번호를 가져온다.
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
	
	
	
}
	
	
	
	
	
	


