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
		int num = Integer.parseInt(request.getParameter("num")); // 글 번호를 받아온다.
		String pageNum = request.getParameter("pageNum"); // 페이지 번호를 받아온다.
		
		// 비지니스 로직 처리
		QnABoardUpdateFormService qnABoardUpdateFormService = new QnABoardUpdateFormService();
		QnABoardVO article = qnABoardUpdateFormService.getUpdateQnAArticle(num);
		
		// article 이라는 이름으로 글 정보, 페이지 번호 공유
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/qnAUpdateForm.jsp");
		
		return forward;
	}

}
