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
		if (command.equals("/qnABoardList.bo")) { // 공지사항 리스트
			action = new QnABoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardContent.bo")) { // 게시판 상세보기
			action = new QnABoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardWriteForm.bo")) { // 게시판 상세보기
			action = new QnABoardWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardWritePro.bo")) { // 게시판 상세보기
			action = new QnABoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardUpdateForm.bo")) { // 게시판 상세보기
			action = new QnABoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardUpdatePro.bo")) { // 게시판 상세보기
			action = new QnABoardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardDeleteForm.bo")) { // 게시판 상세보기
			action = new QnABoardDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/qnABoardDeletePro.bo")) { // 게시판 상세보기
			action = new QnABoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoardList.bo")) { // 문의사항 리스트
			action = new NoticeBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoardContent.bo")) { // 게시판 상세보기
			action = new NoticeBoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// 비지니스로직 처리가 끝나면 포워딩
		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 방식이면...
				response.sendRedirect(forward.getUrl());
			} else { // 디스패치 방식이면...
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
		request.setCharacterEncoding("UTF-8"); // 요청을 한글 처리
		doProcess(request, response);
	}

}
