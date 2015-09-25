package pmhs.web.pcInfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.vo.Member;
import pmhs.web.pcInfo.vo.PCInfo;

public class PCDeclareFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String floor = request.getParameter("floor");
		int pcNum = Integer.parseInt(request.getParameter("pcSitNum"));
		ArrayList<PCInfo> pcSiteList = (ArrayList<PCInfo>)request.getAttribute("pcSiteList"); 
	 	HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		
		request.setAttribute("pcNum", pcNum);
		request.setAttribute("pcSiteList", pcSiteList);
		request.setAttribute("member", member);
		ActionForward forward = new ActionForward();
		forward.setUrl("/pc/pcDeclareForm.jsp");
		return forward;
	}

}
