package pmhs.web.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;

public class MessageReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String writer = request.getParameter("writer");
		request.setAttribute("writer", writer);
		
		ActionForward forward = new ActionForward();
	    forward.setUrl("/message/messageReplyForm.jsp");
	      
	    return forward;
	}

}
