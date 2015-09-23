package pmhs.web.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.action.NoticeBoardContentAction;
import pmhs.web.board.action.NoticeBoardListAction;
import pmhs.web.board.action.QnABoardContentAction;
import pmhs.web.board.action.QnABoardDeleteFormAction;
import pmhs.web.board.action.QnABoardDeleteProAction;
import pmhs.web.board.action.QnABoardListAction;
import pmhs.web.board.action.QnABoardUpdateFormAction;
import pmhs.web.board.action.QnABoardUpdateProAction;
import pmhs.web.board.action.QnABoardWriteFormAction;
import pmhs.web.board.action.QnABoardWriteProAction;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		if (command.equals("/qnABoardList.bo")) { // �������� ����Ʈ
			action = new QnABoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardContent.bo")) { // �Խ��� �󼼺���
			action = new QnABoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardWriteForm.bo")) { // �Խ��� �󼼺���
			action = new QnABoardWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardWritePro.bo")) { // �Խ��� �󼼺���
			action = new QnABoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardUpdateForm.bo")) { // �Խ��� �󼼺���
			action = new QnABoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardUpdatePro.bo")) { // �Խ��� �󼼺���
			action = new QnABoardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardDeleteForm.bo")) { // �Խ��� �󼼺���
			action = new QnABoardDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardDeletePro.bo")) { // �Խ��� �󼼺���
			action = new QnABoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoardList.bo")) { // ���ǻ��� ����Ʈ
			action = new NoticeBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoardContent.bo")) { // �Խ��� �󼼺���
			action = new NoticeBoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// �����Ͻ����� ó���� ������ ������
		if (forward != null) {
			if (forward.isRedirect()) { // �����̷�Ʈ ����̸�...
				response.sendRedirect(forward.getUrl());
			} else { // ����ġ ����̸�...
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // ��û�� �ѱ� ó��
		doProcess(request, response);
	}

}
