package pmhs.web.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.CommentService;
import pmhs.web.board.svc.QnABoardContentService;
import pmhs.web.board.vo.CommentVO;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		QnABoardContentService qnABoardContentService = new QnABoardContentService();
		
		QnABoardVO article = qnABoardContentService.getQnAArticle(num);
		CommentService commentService = new CommentService();
		ArrayList<CommentVO> comment = commentService.selectqnaReplyList(num);
		
		request.setAttribute("comment", comment);
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/qnAContent.jsp"); 
		return forward;
	}

}
