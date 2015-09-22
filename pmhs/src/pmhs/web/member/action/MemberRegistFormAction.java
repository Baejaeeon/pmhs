package pmhs.web.member.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.svc.MemberRegistService;
import pmhs.web.member.vo.Member;

public class MemberRegistFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		String m_id = request.getParameter("m_id");
		String m_name = request.getParameter("m_name");
		int m_studentNum = Integer.parseInt(request.getParameter("m_studentNum"));
		String m_passwd = request.getParameter("m_passwd");
		int m_zipcode1 = Integer.parseInt(request.getParameter("m_zipcode1"));
		int m_zipcode2 = Integer.parseInt(request.getParameter("m_zipcode2"));
		String m_address1 = request.getParameter("m_address1");
		String m_address2 = request.getParameter("m_address2");
		String m_email = request.getParameter("m_email");
		String m_phone = request.getParameter("m_phone");
		String m_gender = request.getParameter("m_gender");
		
		Member member = new Member(m_num, m_id, m_name, m_studentNum, m_passwd, m_zipcode1, m_zipcode2, m_address1, m_address2, m_email, m_phone, m_gender);
		MemberRegistService memberRegistService = new MemberRegistService();
		boolean registSuccess = memberRegistService.registMember(member);
		if(registSuccess){
			response.sendRedirect("memberList");
		}
	}
		return null;
	}

}
