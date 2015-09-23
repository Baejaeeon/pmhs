package pmhs.web.message.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.message.svc.MessageWriteProService;
import pmhs.web.message.vo.MessageVO;

public class MessageWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MessageVO messageArticle = new MessageVO();
	      messageArticle.setMessageNum(Integer.parseInt(request.getParameter("messageNum")));
	      messageArticle.setMessageWriter(request.getParameter("messageWriter"));
	      messageArticle.setTitle(request.getParameter("title"));
	      messageArticle.setMessageReceiver(request.getParameter("receiver"));
	      messageArticle.setMessageContent(request.getParameter("messageContent"));
	      messageArticle.setMessageReg_date(new Timestamp(System.currentTimeMillis()));
	      
	      MessageWriteProService messageWriteProService = new MessageWriteProService();
	      
	      boolean writeSuccess = messageWriteProService.sendMessage(messageArticle);
	      ActionForward forward = null;
	      if(writeSuccess){
	         forward = new ActionForward();
	         forward.setRedirect(true);
	         forward.setUrl("messageReceiveList.msg");
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
