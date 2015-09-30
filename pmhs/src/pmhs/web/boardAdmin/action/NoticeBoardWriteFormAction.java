package pmhs.web.boardAdmin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;


public class NoticeBoardWriteFormAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = 0;

		if(request.getParameter("num") != null) {
	
			num = Integer.parseInt(request.getParameter("num")); 

		}
		request.setAttribute("num", num);
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/noticeWriteForm.jsp");		
		return forward;
	}

}
