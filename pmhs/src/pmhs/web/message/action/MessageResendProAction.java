package pmhs.web.message.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.vo.Member;
import pmhs.web.message.svc.MessageResendProService;
import pmhs.web.message.vo.MessageVO;

public class MessageResendProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		MessageVO messageArticle = new MessageVO();
	    messageArticle.setMessageWriter(((Member)session.getAttribute("loginUser")).getM_id());
	    messageArticle.setTitle(request.getParameter("title"));
        messageArticle.setMessageReceiver(request.getParameter("receiver"));
        messageArticle.setMessageContent(request.getParameter("content"));
        messageArticle.setMessageReg_date(new Timestamp(System.currentTimeMillis()));
	      
	      MessageResendProService messageResendProService = new MessageResendProService();
	      
	      boolean resendSuccess = messageResendProService.resendMessage(messageArticle);
	      ActionForward forward = null;
	      if(resendSuccess){
	         forward = new ActionForward();
	         forward.setRedirect(true);
	         forward.setUrl("messageSendList.msg");
	      }
	      else{
	         response.setContentType("text/html;charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('전송실패')");
	         out.println("history.back()");
	         out.println("</script>");
	      }
	      return forward;
	}

}
