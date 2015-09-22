package pmhs.web.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.vo.ReplyInfo;

public class QnABoardWriteFormAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 모델2 방식은 jsp에서 요청을 받는게 아니고 컨트롤러 즉, 액션에서 받아온다.
		int num = 0, ref = 0, re_step = 0, re_level = 0;
		// 답변 요청이 있을때, 파라미터로 넘어올 값들을 저장할 변수 선언
		if(request.getParameter("num") != null) {
			// 답변글쓰기 요청이 넘어왔으면....
			num = Integer.parseInt(request.getParameter("num")); // 각각을 정수값으로 바꿔준다.
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}
		
		ReplyInfo replyInfo = new ReplyInfo(); // 답변글에 대한 정보 객체 생성
		replyInfo.setNum(num);
		replyInfo.setRe_step(re_step);
		replyInfo.setRe_level(re_level);
		replyInfo.setRef(ref);
		
		// replyInfo라는 이름으로 replyInfo의 정보 공유하기 위해 지정
		request.setAttribute("replyInfo", replyInfo);
		
		// 포워딩 될 뷰페이지의 정보를 저장할 클래스 생성
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/qnAWriteForm.jsp");
		
		return forward;
	}

}
