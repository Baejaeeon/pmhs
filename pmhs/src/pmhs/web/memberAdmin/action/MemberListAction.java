package pmhs.web.memberAdmin.action;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.memberAdmin.svc.MemberListService;
import pmhs.web.memberAdmin.vo.Member;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
MemberListService memberListService  = new MemberListService();
		
		ArrayList<Member> memberList =
				memberListService.getMemberList();
		request.setAttribute("memberList", memberList);
		ActionForward forward = new ActionForward();
		forward.setUrl("/member/memberList.jsp");
		return forward;
	}

}
