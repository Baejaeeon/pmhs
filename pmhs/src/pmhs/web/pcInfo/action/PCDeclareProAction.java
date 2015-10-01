package pmhs.web.pcInfo.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcInfo.svc.PCDeClareProService;
import pmhs.web.pcInfo.vo.ErrorPCInfo;

public class PCDeclareProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ErrorPCInfo errorPCInfo = new ErrorPCInfo();
		errorPCInfo.setE_declaredate(new Timestamp(System.currentTimeMillis()));
		errorPCInfo.setE_errorsymptom(request.getParameter("symptom"));
		errorPCInfo.setE_unit(request.getParameter("unit"));
		errorPCInfo.setE_department(request.getParameter("department"));
		errorPCInfo.setE_lectureRoom(Integer.parseInt(request.getParameter("lectureRoom")));
		errorPCInfo.setP_num(Integer.parseInt(request.getParameter("pcNum")));
		errorPCInfo.setE_name(request.getParameter("name"));
		errorPCInfo.setE_phone(request.getParameter("phone"));
		
		PCDeClareProService pcDeClareProService = new PCDeClareProService();
		boolean declareSuccess = pcDeClareProService.declarePC(errorPCInfo);
		boolean changeSuccess = pcDeClareProService.changeState(errorPCInfo);
		
		ActionForward forward = null;
	    if(declareSuccess && changeSuccess){
	    	PrintWriter out = response.getWriter();
	        forward = new ActionForward();
	        forward.setUrl("index.jsp");
	     }
	     else{
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("alert('신고 실패')");
	        out.println("history.back()");
	        out.println("</script>");
	      }
	      return forward;
	}

}
