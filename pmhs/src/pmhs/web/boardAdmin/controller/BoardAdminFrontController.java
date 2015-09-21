package pmhs.web.boardAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.boardAdmin.action.NoticeBoardListAction;

/**
 * Servlet implementation class BoardAdminFrontController
 */
@WebServlet("*.bo")
public class BoardAdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAdminFrontController() {
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
		if(command.equals("/noticeBoardList.bo")) { // �������� ����Ʈ
			action = new NoticeBoardListAction();
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
