package pmhs.web.member.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.svc.MemberModifyProService;
import pmhs.web.member.vo.Member;

public class MemberModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String m_id = request.getParameter("id");
		String m_name = request.getParameter("name");
		int m_studentNum = Integer.parseInt(request.getParameter("studentNum"));
		String m_passwd = request.getParameter("passwd");
		int m_zipcode1 = Integer.parseInt(request.getParameter("zipcode1"));
		int m_zipcode2 = Integer.parseInt(request.getParameter("zipcode2"));
		String m_address1 = request.getParameter("address1");
		String m_address2 = request.getParameter("address2");
		String m_birthDay = request.getParameter("birthDay");
		String m_email = request.getParameter("email");
		String m_phone = request.getParameter("phone");
		String m_gender = request.getParameter("gender");
		
		Member member = new Member(m_id, m_name, m_studentNum, m_passwd, m_zipcode1, m_zipcode2, m_address1, m_address2, m_birthDay, m_email, m_phone, m_gender);
		
		MemberModifyProService memberModifyProService = new MemberModifyProService();

		boolean modifySuccess = memberModifyProService.modifyMember(member);
		ActionForward forward = null;
		if(modifySuccess){
			session.setAttribute("loginUser", member);
			forward = new ActionForward();
			forward.setUrl("member/memberMain.jsp");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��������')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
