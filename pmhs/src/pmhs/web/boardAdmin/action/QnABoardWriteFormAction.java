package pmhs.web.boardAdmin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.vo.ReplyInfo;

public class QnABoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = 0, ref = 0, re_step = 0, re_level = 0;
		
		if(request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num")); 
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}
		
		ReplyInfo replyInfo = new ReplyInfo(); 
		replyInfo.setNum(num);
		replyInfo.setRe_step(re_step);
		replyInfo.setRe_level(re_level);
		replyInfo.setRef(ref);

		request.setAttribute("replyInfo", replyInfo);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/adminQnAWriteForm.jsp");
		
		return forward;
	}
	
}
