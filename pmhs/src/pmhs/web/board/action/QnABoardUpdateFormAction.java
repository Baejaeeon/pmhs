package pmhs.web.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardUpdateFormService;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardUpdateFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num")); 
		String pageNum = request.getParameter("pageNum"); 

		QnABoardUpdateFormService qnABoardUpdateFormService = new QnABoardUpdateFormService();
		QnABoardVO article = qnABoardUpdateFormService.getUpdateQnAArticle(num);

		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/qnAUpdateForm.jsp");
		
		return forward;
	}

}
