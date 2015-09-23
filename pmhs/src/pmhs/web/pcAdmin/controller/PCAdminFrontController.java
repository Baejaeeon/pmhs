package pmhs.web.pcAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.pcAdmin.action.ErrorPCListAction;
import pmhs.web.pcAdmin.action.ReservationListAction;

/**
 * Servlet implementation class PCAdminFrontController
 */
@WebServlet("*.pca")
public class PCAdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PCAdminFrontController() {
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
		if(command.equals("/errorPCList.pca")) { // 고장 PC 리스트
			action = new ErrorPCListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/reservationList.pca")) { // 고장 PC 리스트
			action = new ReservationListAction();
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
