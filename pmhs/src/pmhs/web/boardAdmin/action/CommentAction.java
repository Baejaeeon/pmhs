package pmhs.web.boardAdmin.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.CommentService;
import pmhs.web.board.vo.CommentVO;
import pmhs.web.member.vo.Member;

public class CommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		int q_num = Integer.parseInt(request.getParameter("q_num"));
		String c_content = request.getParameter("reply_content");
		String c_writer = ((Member)session.getAttribute("loginUser")).getM_id();
	    CommentVO commentVO = new CommentVO(q_num, c_writer, c_content, new Timestamp(System.currentTimeMillis()));
		CommentService commentService = new CommentService();
		boolean isRegistSuccess = commentService.registComment(commentVO);
		ActionForward forward = new ActionForward();
		if (isRegistSuccess) {
			forward.setUrl("adminQnABoardContent.boa?num=" + q_num);
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 등록 실패.')");
			out.println("history.back()"); 
			out.println("</script>");
		}
		return forward;
   }

}
