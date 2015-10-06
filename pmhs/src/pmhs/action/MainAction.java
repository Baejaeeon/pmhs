package pmhs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.vo.ActionForward;
import pmhs.web.board.svc.NoticeBoardListService;
import pmhs.web.board.svc.QnABoardListService;
import pmhs.web.board.vo.NoticeBoardVO;
import pmhs.web.board.vo.PageInfo;
import pmhs.web.board.vo.QnABoardVO;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pageSize = 10;
		
		String pageNum = request.getParameter("pageNum"); 
		if(pageNum == null) {
			pageNum = "1"; 
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;			
		int count = 0;
		int number = 0;

		List<NoticeBoardVO> noticeArticleList = null;
		List<QnABoardVO> qnaArticleList = null;
		NoticeBoardListService noticeBoardListService = new NoticeBoardListService();
		QnABoardListService qnABoardListService = new QnABoardListService();
		
		noticeArticleList = noticeBoardListService.getNoticeContentArticleList(); 
		qnaArticleList = qnABoardListService.getQnAContentArticleList();
		HttpSession session = request.getSession();
		
		session.setAttribute("noticeArticleList", noticeArticleList);
		session.setAttribute("qnaArticleList", qnaArticleList);
		session.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("main.jsp");
		
		return forward;
	}

}
