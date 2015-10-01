package pmhs.web.pcAdmin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcAdmin.svc.ErrorPCFixProService;

public class ErrorPCFixProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // �ѱ�ó��
		String[] deleteNumArray = request.getParameterValues("delete1"); // ������ id�� �迭�� �����´�.
	
		ErrorPCFixProService errorPCFixProService = new ErrorPCFixProService();
		boolean removeSuccess = errorPCFixProService.removeReservation(deleteNumArray);
		
		boolean changeSuccess = errorPCFixProService.changeState(deleteNumArray);
		
		ActionForward action = new ActionForward();
		if(removeSuccess && changeSuccess) { // ���� ������..
			action.setUrl("reservationList.pca");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���� ����')"); // ���� ���н� �˸�â ����ش�.
			out.println("history.back();");
			out.println("</script>");
		}
		
		return action;
	}

}
