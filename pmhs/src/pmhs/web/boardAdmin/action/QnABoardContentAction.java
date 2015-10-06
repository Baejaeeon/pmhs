package pmhs.web.boardAdmin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.svc.CommentService;
import pmhs.web.boardAdmin.svc.QnABoardContentService;
import pmhs.web.boardAdmin.vo.CommentVO;
import pmhs.web.boardAdmin.vo.QnABoardVO;


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
		System.out.println(pageNum);
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/adminQnAContent.jsp"); 
		return forward;
	}

}
