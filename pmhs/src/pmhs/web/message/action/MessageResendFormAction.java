package pmhs.web.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;

public class MessageResendFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String receiver = request.getParameter("receiver");
		request.setAttribute("receiver", receiver);
		
		ActionForward forward = new ActionForward();
	    forward.setUrl("/message/messageResendForm.jsp");
	      
	    return forward;
	}

}
