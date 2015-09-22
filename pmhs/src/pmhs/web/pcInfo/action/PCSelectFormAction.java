package pmhs.web.pcInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcInfo.vo.PCInfo;

public class PCSelectFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = 0;
		String unit = null;
		String department = null;
		int lectureRoom = 0;
		String lectureImage = null;
		if(request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
			unit = request.getParameter("unit");
			department = request.getParameter("department");
			lectureRoom = Integer.parseInt(request.getParameter("lectureImage"));
		}
		
		PCInfo pcInfo = new PCInfo();
		pcInfo.setNum(num);
		pcInfo.setUnit(unit);
		pcInfo.setLectureRoom(lectureRoom);
		pcInfo.setLectureImage(lectureImage);
		
		request.setAttribute("pcInfo", pcInfo);
		ActionForward forward = new ActionForward();
		forward.setUrl("pc/pcSelectForm.jsp");
		return forward;
	}
}
