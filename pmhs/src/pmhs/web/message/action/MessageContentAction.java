package pmhs.web.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.message.svc.MessageContentService;
import pmhs.web.message.vo.MessageVO;

public class MessageContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
	      String pageNum = request.getParameter("pageNum");
	      
	      MessageContentService messageContentService = new MessageContentService();
	      MessageVO article = messageContentService.getArticle(num);

	      ActionForward forward = new ActionForward();
	      forward.setUrl("/message/messageContent.jsp"); // 페이지 포워딩 지정
	      
	      request.setAttribute("article", article);
	      request.setAttribute("pageNum", pageNum);
	      
	      return forward;
	}

}
