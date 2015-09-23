package pmhs.web.member.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.member.svc.IdCheckService;

public class IdCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id"); // 아이디 값을 받아온다.
		IdCheckService idCheckService = new IdCheckService();
		
		boolean idExists = idCheckService.existsId(id); // 중복 체크
		request.setAttribute("id", id);
		request.setAttribute("idExists", idExists);
		
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("member/idCheck.jsp");
		dispatcher.forward(request, response);*/
		ActionForward forward = new ActionForward();
		forward.setUrl("member/idCheck.jsp");
		return forward;
	}

}
