package pmhs.web.pcAdmin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcAdmin.svc.ErrorPCDetailService;
import pmhs.web.pcAdmin.vo.ErrorPCInfo;

public class ErrorPCDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pcNum = Integer.parseInt(request.getParameter("pcSitNum"));
		
		ErrorPCDetailService ErrorPCDetailService = new ErrorPCDetailService();
		ErrorPCInfo errorPCInfo = ErrorPCDetailService.getErrorPCInfo(pcNum);
		
		request.setAttribute("errorPCInfo", errorPCInfo); // 에러 PC 정보 공유
		ActionForward forward = new ActionForward();
		forward.setUrl("/pc/errorPCDetail.jsp");
		return forward;
	}

}
