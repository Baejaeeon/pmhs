package pmhs.web.boardAdmin.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.svc.QnABoardUpdateProService;
import pmhs.web.boardAdmin.vo.QnABoardVO;

public class QnABoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		QnABoardVO article = new QnABoardVO();
		article.setContent(request.getParameter("content")); 
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));

		article.setReg_date(new Timestamp(System.currentTimeMillis()));

		article.setIp(request.getRemoteAddr()); 

		QnABoardUpdateProService qnABoardUpdateProService = new QnABoardUpdateProService();
		
		boolean updateSuccess = qnABoardUpdateProService.modifyQnAArticle(article);
		ActionForward forward = null;
		if(updateSuccess) { 
			forward = new ActionForward();
			forward.setRedirect(true); 
			forward.setUrl("qnABoardList.bo"); 
		} else { 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
