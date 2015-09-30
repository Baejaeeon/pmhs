package pmhs.web.boardAdmin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.svc.QnABoardDeleteProService;


public class QnABoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			String[] deleteArray = request.getParameterValues("delete1");
			QnABoardDeleteProService qnABoardDeleteProService = new QnABoardDeleteProService();
			
			boolean removeSuccess = qnABoardDeleteProService.removeQnAArticle(deleteArray);
			ActionForward forward = new ActionForward();
			if(removeSuccess) {
				forward.setRedirect(true);
				forward.setUrl("adminQnABoardList.boa");
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
