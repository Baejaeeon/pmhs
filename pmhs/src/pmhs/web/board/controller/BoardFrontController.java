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
import pmhs.web.board.action.CommentAction;
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
import pmhs.web.board.action.QnACommentDeleteAction;

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
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()); 
	
		Action action = null; 
		ActionForward forward = null; 

		if (command.equals("/qnABoardList.bo")) {
			action = new QnABoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardContent.bo")) { 
			action = new QnABoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardWriteForm.bo")) { 
			action = new QnABoardWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardWritePro.bo")) { 
			action = new QnABoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardUpdateForm.bo")) { 
			action = new QnABoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardUpdatePro.bo")) { 
			action = new QnABoardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardDeleteForm.bo")) { 
			action = new QnABoardDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardDeletePro.bo")) { 
			action = new QnABoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoardList.bo")) { 
			action = new NoticeBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoardContent.bo")) {
			action = new NoticeBoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnAComment.bo")) {
			action = new CommentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnACommentDelete.bo")) {
			action = new QnACommentDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		
		if (forward != null) {
			if (forward.isRedirect()) { 
				response.sendRedirect(forward.getUrl());
			} else { 
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
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}
