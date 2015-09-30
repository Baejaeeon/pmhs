package pmhs.web.boardAdmin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.svc.NoticeBoardContentService;
import pmhs.web.boardAdmin.vo.NoticeBoardVO;


public class NoticeBoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeBoardContentService noticeBoardContentService = new NoticeBoardContentService();
		
		NoticeBoardVO article = noticeBoardContentService.getNoticeArticle(num);
		
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/adminNoticeContent.jsp");
		return forward;
	}

}
