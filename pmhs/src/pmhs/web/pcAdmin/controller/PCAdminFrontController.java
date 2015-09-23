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
		// doGet, doPost�� ����� �۾����� doProcess���� ��û ó��
		// 1. ��û �ľ�(���� ��û�� �Ѿ�Դ��� �ľ�)
		String requestURI = request.getRequestURI();
		// ��û �ּ� : http://localhost:8088/BoardProject/boardWriteForm.bo�� �Ѿ����..
		// reqeustURI : /BoardProject/boardWriteForm.bo
		
		String contextPath = request.getContextPath();
		// /BoardProject�� ��ȯ��.
		
		String command = requestURI.substring(contextPath.length()); // ��� �ľ�
		// contextPath �������� �������� ���ڸ� ������. ----> /boardWriteForm.bo�� �������� ��.
		
		Action action = null; // Action �������̽� ����
		ActionForward forward = null; // �������� �������� ������ ���� foward����
		
		// ������ ��û ó��
		if(command.equals("/errorPCList.pca")) { // ���� PC ����Ʈ
			action = new ErrorPCListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/reservationList.pca")) { // ���� PC ����Ʈ
			action = new ReservationListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		// �����Ͻ����� ó���� ������ ������
		if(forward != null) {
			if(forward.isRedirect()) { // �����̷�Ʈ ����̸�...
				response.sendRedirect(forward.getUrl());
			}
			else { // ����ġ ����̸�...
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
		request.setCharacterEncoding("UTF-8"); // ��û�� �ѱ� ó��
		doProcess(request, response);
	}

}
