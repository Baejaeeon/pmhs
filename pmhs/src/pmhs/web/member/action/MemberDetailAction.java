package pmhs.web.member.action;

import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.svc.MemberDetailService;
import pmhs.web.member.vo.Member;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		System.out.println("AAAAAAAAAAA");
		Member loginUser = (Member)session.getAttribute("loginUser");
		//비지니스로직 요청
		MemberDetailService memberDetailService = new MemberDetailService();
		
		Member member = memberDetailService.getMember(id);
		request.setAttribute("loginUser", loginUser);
		ActionForward forward = new ActionForward();
		forward.setUrl("/member/memberDetail.jsp");
		
		
		
		return forward;
	}

}
