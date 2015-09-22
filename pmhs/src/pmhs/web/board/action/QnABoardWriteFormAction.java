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
		// ��2 ����� jsp���� ��û�� �޴°� �ƴϰ� ��Ʈ�ѷ� ��, �׼ǿ��� �޾ƿ´�.
		int num = 0, ref = 0, re_step = 0, re_level = 0;
		// �亯 ��û�� ������, �Ķ���ͷ� �Ѿ�� ������ ������ ���� ����
		if(request.getParameter("num") != null) {
			// �亯�۾��� ��û�� �Ѿ������....
			num = Integer.parseInt(request.getParameter("num")); // ������ ���������� �ٲ��ش�.
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}
		
		ReplyInfo replyInfo = new ReplyInfo(); // �亯�ۿ� ���� ���� ��ü ����
		replyInfo.setNum(num);
		replyInfo.setRe_step(re_step);
		replyInfo.setRe_level(re_level);
		replyInfo.setRef(ref);
		
		// replyInfo��� �̸����� replyInfo�� ���� �����ϱ� ���� ����
		request.setAttribute("replyInfo", replyInfo);
		
		// ������ �� ���������� ������ ������ Ŭ���� ����
		ActionForward forward = new ActionForward();
		forward.setUrl("/board/qnAWriteForm.jsp");
		
		return forward;
	}

}
