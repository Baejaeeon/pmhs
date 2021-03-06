package pmhs.web.message.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.message.action.MessageContentAction;
import pmhs.web.message.action.MessageDeleteAction;
import pmhs.web.message.action.MessageReceiveListAction;
import pmhs.web.message.action.MessageReplyFormAction;
import pmhs.web.message.action.MessageResendFormAction;
import pmhs.web.message.action.MessageResendProAction;
import pmhs.web.message.action.MessageSendContentAction;
import pmhs.web.message.action.MessageSendDeleteAction;
import pmhs.web.message.action.MessageSendListAction;
import pmhs.web.message.action.MessageWriteFormAction;
import pmhs.web.message.action.MessageWriteProAction;

/**
 * Servlet implementation class MessageFrontController
 */
@WebServlet("*.msg")
public class MessageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet, doPost의 공통된 작업들을 doProcess에서 요청 처리
		// 1. 요청 파악(무슨 요청이 넘어왔는지 파악)
		String requestURI = request.getRequestURI();
		// 요청 주소 : http://localhost:8088/BoardProject/boardWriteForm.bo가 넘어오면..
		// reqeustURI : /BoardProject/boardWriteForm.bo
		
		String contextPath = request.getContextPath();
		// /BoardProject가 반환됨.
		
		String command = requestURI.substring(contextPath.length()); // 명령 파악
		// contextPath 다음부터 끝까지의 문자를 가져옴. ----> /boardWriteForm.bo를 가져오게 됨.
		
		Action action = null; // Action 인터페이스 정의
		ActionForward forward = null; // 포워딩될 뷰페이지 정보를 담을 foward정의
		
		// 각각의 요청 처리
		if(command.equals("/messageWriteForm.msg")) {
	         action = new MessageWriteFormAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
	      }
	      else if(command.equals("/messageWritePro.msg")) {
	         action = new MessageWriteProAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
	      }
	      else if(command.equals("/messageReceiveList.msg")) {
	         action = new MessageReceiveListAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
	      }
	      else if(command.equals("/messageSendList.msg")) {
	    	  action = new MessageSendListAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  } catch (Exception e) {
	    		  // TODO: handle exception
	    		  e.printStackTrace();
	    	  }
	      }

	      else if(command.equals("/messageContent.msg")) {
	         action = new MessageContentAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
	      }
	      else if(command.equals("/messageSendContent.msg")) {
	    	  action = new MessageSendContentAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  } catch (Exception e) {
	    		  // TODO: handle exception
	    		  e.printStackTrace();
	    	  }
	      }
	      else if(command.equals("/messageDelete.msg")) {
	         action = new MessageDeleteAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
	      }
	      else if(command.equals("/messageSendDelete.msg")) {
	    	  action = new MessageSendDeleteAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  } catch (Exception e) {
	    		  // TODO: handle exception
	    		  e.printStackTrace();
	    	  }
	      }
	      else if(command.equals("/messageReplyForm.msg")) {
	    	  action = new MessageReplyFormAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  } catch (Exception e) {
	    		  // TODO: handle exception
	    		  e.printStackTrace();
	    	  }
	      }
	      else if(command.equals("/messageResendForm.msg")) {
	    	  action = new MessageResendFormAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  } catch (Exception e) {
	    		  // TODO: handle exception
	    		  e.printStackTrace();
	    	  }
	      }
	      else if(command.equals("/messageResendPro.msg")) {
	    	  action = new MessageResendProAction();
	    	  try {
	    		  forward = action.execute(request, response);
	    	  } catch (Exception e) {
	    		  // TODO: handle exception
	    		  e.printStackTrace();
	    	  }
	      }
		
		// 비지니스로직 처리가 끝나면 포워딩
		if(forward != null) {
			if(forward.isRedirect()) { // 리다이렉트 방식이면...
				response.sendRedirect(forward.getUrl());
			}
			else { // 디스패치 방식이면...
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // 요청을 한글 처리
		doProcess(request, response);
	}

}
