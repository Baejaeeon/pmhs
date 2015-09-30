package pmhs.web.boardAdmin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardDeleteProService;
import pmhs.web.boardAdmin.svc.NoticeBoardDeleteProService;

public class NoticeBoardDeleteProAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		String pageNum = request.getParameter("pageNum"); 

		NoticeBoardDeleteProService noticeBoardDeleteProService = new NoticeBoardDeleteProService();
		
		boolean removeSuccess = noticeBoardDeleteProService.removeNoticeArticle(num, passwd);
		ActionForward forward = null;
		if(removeSuccess) {
			forward = new ActionForward();
			forward.setRedirect(true); 
			forward.setUrl("adminNoticeBoardList.boa"); 
		} else { 
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
