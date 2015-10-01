package pmhs.web.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.CommentDeleteService;
import pmhs.web.board.svc.CommentService;
import pmhs.web.board.vo.CommentVO;
import pmhs.web.member.vo.Member;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int q_num = Integer.parseInt(request.getParameter("q_num"));
		String c_content = request.getParameter("c_content");
		String c_writer = ((Member)session.getAttribute("loginUser")).getM_id();
	    CommentVO commentVO = new CommentVO(q_num, c_writer, c_content, new Timestamp(System.currentTimeMillis()));
		CommentDeleteService commentDeleteService = new CommentDeleteService();
		boolean isDeleteSuccess = commentDeleteService.removeComment(c_writer,c_content);
		ActionForward forward = new ActionForward();
		if (isDeleteSuccess) {
			forward.setUrl("qnABoardContent.bo?num=" + q_num);
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 삭제 실패.')");
			out.println("history.back()"); 
			out.println("</script>");
		}
		return forward;
   }
	}


