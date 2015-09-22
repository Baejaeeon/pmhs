package pmhs.web.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardWriteProService;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		QnABoardVO article = new QnABoardVO();
		article.setContent(request.getParameter("content")); // ������ �Ӽ��� �޾ƿ´�.
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		
		// DB�۾� ����
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		// System.currentTimeMillis() : 1970�� 1�� 1�� �������� ��������� 
		// �ð��� �и����� ������ ��ȯ
		article.setIp(request.getRemoteAddr()); // ��û�� Ŭ���̾�Ʈ�� ip�ּҸ� ���´�.
		
		// �����Ͻ� ���� ó���� ���� ���� Ŭ���� ����
		QnABoardWriteProService qnABoardWriteProService = new QnABoardWriteProService();
		
		boolean writeSuccess = qnABoardWriteProService.writeArticle(article);
		ActionForward forward = null;
		if(writeSuccess) { // �� �ۼ��� �����ϸ�.... ������ �� �������� ����
			forward = new ActionForward();
			forward.setRedirect(true); // �����̷�Ʈ�� �Ѿ�ٴ°��� ǥ��
			forward.setUrl("qnAList.bo"); // �� ��� ���� ȭ������ ȭ���� �ѱ��.
		} else { // ���� ��..
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��Ͻ���')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
