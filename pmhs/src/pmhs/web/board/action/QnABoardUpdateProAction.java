package pmhs.web.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardUpdateProService;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardUpdateProAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		QnABoardVO article = new QnABoardVO();
		article.setContent(request.getParameter("content")); // ������ �Ӽ��� �޾ƿ´�.
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		
		// DB�۾� ����
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		// System.currentTimeMillis() : 1970�� 1�� 1�� �������� ��������� 
		// �ð��� �и����� ������ ��ȯ
		article.setIp(request.getRemoteAddr()); // ��û�� Ŭ���̾�Ʈ�� ip�ּҸ� ���´�.
		
		// �����Ͻ� ���� ó���� ���� ���� Ŭ���� ����
		QnABoardUpdateProService qnABoardUpdateProService = new QnABoardUpdateProService();
		
		boolean updateSuccess = qnABoardUpdateProService.modifyQnAArticle(article);
		ActionForward forward = null;
		if(updateSuccess) { // �� �ۼ��� �����ϸ�.... ������ �� �������� ����
			forward = new ActionForward();
			forward.setRedirect(true); // �����̷�Ʈ�� �Ѿ�ٴ°��� ǥ��
			forward.setUrl("boardList.bo"); // �� ��� ���� ȭ������ ȭ���� �ѱ��.
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
