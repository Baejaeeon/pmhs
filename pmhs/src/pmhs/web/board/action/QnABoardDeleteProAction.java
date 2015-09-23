package pmhs.web.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardDeleteProService;

public class QnABoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		String pageNum = request.getParameter("pageNum"); // 페이지 번호를 받아온다.

		// 비지니스 로직을 처리 하기 위해 서비스 객체 생성
		QnABoardDeleteProService qnABoardDeleteProService = new QnABoardDeleteProService();
		
		boolean removeSuccess = qnABoardDeleteProService.removeQnAArticle(num, passwd);
		ActionForward forward = null;
		if(removeSuccess) { // 글 작성이 성공하면.... 포워딩 될 뷰페이지 설정
			forward = new ActionForward();
			forward.setRedirect(true); // 리다이렉트로 넘어간다는것을 표시
			forward.setUrl("qnABoardList.bo"); // 글 목록 보는 화면으로 화면을 넘긴다.
		} else { // 실패 시..
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
