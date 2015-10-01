package pmhs.web.pcAdmin.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcAdmin.svc.PCReservationProService;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class PCReservationProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ReservationInfo reservationInfo = new ReservationInfo();
		reservationInfo.setSubject(request.getParameter("lectureRoom") + "호 예약");
		reservationInfo.setDeclareDate(new Timestamp(System.currentTimeMillis()));
		reservationInfo.setErrorSymptom(request.getParameter("symptom"));
		reservationInfo.setUnit(request.getParameter("unit"));
		reservationInfo.setDepartment(request.getParameter("department"));
		reservationInfo.setLectureRoom(Integer.parseInt(request.getParameter("lectureRoom")));
		reservationInfo.setPcNum(Integer.parseInt(request.getParameter("pcNum")));
		reservationInfo.setName(request.getParameter("name"));
		reservationInfo.setPhone(request.getParameter("phone"));
		reservationInfo.setTime(request.getParameter("time"));
		
		PCReservationProService pcReservationProService = new PCReservationProService();
		boolean reservationSuccess = pcReservationProService.reservationPC(reservationInfo);
		boolean removeSuccess = pcReservationProService.removeErrorPCInfo(reservationInfo);
		boolean changeSuccess = pcReservationProService.changeState(reservationInfo);
		
		ActionForward forward = null;
	    if(reservationSuccess && removeSuccess && changeSuccess){
	    	PrintWriter out = response.getWriter();
	        forward = new ActionForward();
	        forward.setUrl("index.jsp");
	     }
	     else{
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("alert('예약 실패')");
	        out.println("history.back()");
	        out.println("</script>");
	      }
	      return forward;
	}

}
