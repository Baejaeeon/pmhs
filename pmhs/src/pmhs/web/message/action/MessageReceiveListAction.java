package pmhs.web.message.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.vo.Member;
import pmhs.web.message.svc.MessageReceiveListService;
import pmhs.web.message.vo.MessageVO;
import pmhs.web.message.vo.PageInfo;

public class MessageReceiveListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		MessageReceiveListService messageReceiveListService = new MessageReceiveListService();
		ActionForward forward = new ActionForward();
		
		if(session.getAttribute("loginUser") != null) { // 로그인된 유저가 있으면..
			List<MessageVO> receiveList = messageReceiveListService.getReceiveList(((Member)session.getAttribute("loginUser")).getM_name());
			request.setAttribute("receiveList", receiveList);
			forward.setUrl("/message/messageReceiveList.jsp");
		}
		else { // 아니면..
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 해주세요.')");
			out.println("history.back()");
			out.println("</script>");
			forward.setUrl("/member/loginForm.jsp");
		}
		return forward;
	}
}
