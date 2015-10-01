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
		request.setCharacterEncoding("UTF-8"); // 한글처리
		String[] deleteNumArray = request.getParameterValues("delete1"); // 삭제할 id를 배열로 가져온다.
	
		ErrorPCFixProService errorPCFixProService = new ErrorPCFixProService();
		boolean removeSuccess = errorPCFixProService.removeReservation(deleteNumArray);
		
		boolean changeSuccess = errorPCFixProService.changeState(deleteNumArray);
		
		ActionForward action = new ActionForward();
		if(removeSuccess && changeSuccess) { // 삭제 성공시..
			action.setUrl("reservationList.pca");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수리 실패')"); // 삭제 실패시 알림창 띄워준다.
			out.println("history.back();");
			out.println("</script>");
		}
		
		return action;
	}

}
