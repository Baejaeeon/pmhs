package pmhs.web.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardDeleteProService;

public class QnABoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		String pageNum = request.getParameter("pageNum"); // ������ ��ȣ�� �޾ƿ´�.

		// �����Ͻ� ������ ó�� �ϱ� ���� ���� ��ü ����
		QnABoardDeleteProService qnABoardDeleteProService = new QnABoardDeleteProService();
		
		boolean removeSuccess = qnABoardDeleteProService.removeQnAArticle(num, passwd);
		ActionForward forward = null;
		if(removeSuccess) { // �� �ۼ��� �����ϸ�.... ������ �� �������� ����
			forward = new ActionForward();
			forward.setRedirect(true); // �����̷�Ʈ�� �Ѿ�ٴ°��� ǥ��
			forward.setUrl("qnABoardList.bo"); // �� ��� ���� ȭ������ ȭ���� �ѱ��.
		} else { // ���� ��..
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��������')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
