package pmhs.web.message.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.message.svc.MessageDeleteService;

public class MessageDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int messageNum = Integer.parseInt(request.getParameter("num"));
	      String pageNum = request.getParameter("pageNum");

	      MessageDeleteService messageDeleteService = new MessageDeleteService();

	      boolean deleteSuccess = messageDeleteService.removeArticle(messageNum);
	      ActionForward forward = null;

	      if(deleteSuccess){
	         forward = new ActionForward();
	         forward.setRedirect(true);
	         forward.setUrl("messageReceiveList.msg");
	      }
	      else{
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
