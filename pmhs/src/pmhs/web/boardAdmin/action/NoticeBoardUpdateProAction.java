package pmhs.web.boardAdmin.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.svc.NoticeBoardUpdateProService;
import pmhs.web.boardAdmin.vo.NoticeBoardVO;


public class NoticeBoardUpdateProAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		NoticeBoardVO article = new NoticeBoardVO();
		article.setContent(request.getParameter("content")); 
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		article.setIp(request.getRemoteAddr()); 
		
		NoticeBoardUpdateProService noticeBoardUpdateProService = new NoticeBoardUpdateProService();
		
		boolean updateSuccess = noticeBoardUpdateProService.modifyNoticeArticle(article);
		ActionForward forward = null;
		if(updateSuccess) { 
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setUrl("adminNoticeBoardList.boa"); 
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
