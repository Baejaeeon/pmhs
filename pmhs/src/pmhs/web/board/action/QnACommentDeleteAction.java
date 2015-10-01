package pmhs.web.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnACommentDeleteService;


public class QnACommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		QnACommentDeleteService qnACommentDeleteService = new QnACommentDeleteService();
		boolean removeSuccess = qnACommentDeleteService.removeComment(c_num);
		ActionForward forward = new ActionForward();
		if(removeSuccess) {
			forward.setUrl("qnABoardContent.bo?num=" + num + "&pageNum=" + pageNum);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패')"); // 삭제 실패시 알림창 띄워준다.
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
