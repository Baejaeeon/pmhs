package pmhs.web.boardAdmin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.svc.NoticeBoardUpdateFormService;
import pmhs.web.boardAdmin.vo.NoticeBoardVO;


public class NoticeBoardUpdateFormAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num")); 
		String pageNum = request.getParameter("pageNum");

		NoticeBoardUpdateFormService noticeBoardUpdateFormService = new NoticeBoardUpdateFormService();
		NoticeBoardVO article = noticeBoardUpdateFormService.getUpdateNoticeArticle(num);
		
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/noticeUpdateForm.jsp");
		
		return forward;
	}
}
