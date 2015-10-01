package pmhs.web.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.message.svc.MessageSendContentService;
import pmhs.web.message.vo.MessageVO;

public class MessageSendContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
	      String pageNum = request.getParameter("pageNum");
	      
	      MessageSendContentService messageSendContentService = new MessageSendContentService();
	      MessageVO article = messageSendContentService.getArticle(num);

	      ActionForward forward = new ActionForward();
	      forward.setUrl("/message/messageSendContent.jsp"); // 페이지 포워딩 지정
	      
	      request.setAttribute("article", article);
	      request.setAttribute("pageNum", pageNum);
	      
	      return forward;
	}

}
