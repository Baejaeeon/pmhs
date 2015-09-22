package pmhs.web.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardWriteProService;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		QnABoardVO article = new QnABoardVO();
		article.setContent(request.getParameter("content")); // 각각의 속성을 받아온다.
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		
		// DB작업 수행
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		// System.currentTimeMillis() : 1970년 1월 1일 자정부터 현재까지의 
		// 시간을 밀리세컨 단위로 반환
		article.setIp(request.getRemoteAddr()); // 요청한 클라이언트의 ip주소를 얻어온다.
		
		// 비지니스 로직 처리를 위해 서비스 클래스 생성
		QnABoardWriteProService qnABoardWriteProService = new QnABoardWriteProService();
		
		boolean writeSuccess = qnABoardWriteProService.writeArticle(article);
		ActionForward forward = null;
		if(writeSuccess) { // 글 작성이 성공하면.... 포워딩 될 뷰페이지 설정
			forward = new ActionForward();
			forward.setRedirect(true); // 리다이렉트로 넘어간다는것을 표시
			forward.setUrl("qnAList.bo"); // 글 목록 보는 화면으로 화면을 넘긴다.
		} else { // 실패 시..
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
