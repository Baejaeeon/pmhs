package pmhs.web.pcAdmin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcInfo.svc.PCSelectFormService;
import pmhs.web.pcInfo.vo.PCInfo;

public class ErrorPCSelectFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String unit = null;
		String department = null;
		int lectureRoom = 0;
		if(request.getParameter("unit") != null || request.getParameter("department") != null || request.getParameter("lectureRoom") != null) {
			unit = request.getParameter("unit");
			department = request.getParameter("department");
			lectureRoom = Integer.parseInt(request.getParameter("lectureRoom"));
		}
		
		PCSelectFormService pcSelectFormService = new PCSelectFormService();
		ArrayList<PCInfo> pcSiteList = pcSelectFormService.getPCSitList(unit, department, lectureRoom);
		
		/*PCInfo pcInfo = new PCInfo();
		pcInfo.setUnit(unit);
		pcInfo.setLectureRoom(lectureRoom);
		pcInfo.setLectureImage(lectureImage);*/
		
		//request.setAttribute("pcInfo", pcInfo);
		//request.setAttribute("pcSiteList", pcSiteList);
		HttpSession session = request.getSession();
		session.setAttribute("pcSiteList", pcSiteList);
		ActionForward forward = new ActionForward();
		forward.setUrl("/pc/errorPCSelectForm.jsp");
		return forward;
	}

}
