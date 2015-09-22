<%@page import="java.sql.Timestamp"%>
<%@page import="pmhs.web.message.dao.MessageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	request.setCharacterEncoding("UTF-8");
	%>
<jsp:useBean id="article" class = "pmhs.web.message.vo.MessageVO" /> <!-- 게시판 글 하나를 담을 Bean객체 생성 -->
<!-- scope 안주면 default로 page로 설정 -->
<jsp:setProperty property="*" name="article" />
<%
	// DB작업 수행
	article.setMessageReg_date(new Timestamp(System.currentTimeMillis()));
	// System.currentTimeMillis() : 1970년 1월 1일 자정부터 현재까지의 
	// 시간을 밀리세컨 단위로 반환
	
	// 데이터베이스 작업을 위해 DAO 클래스 객체 얻어오기
	MessageDAO messageDAO = MessageDAO.getInstance();
	messageDAO.insertMessage(article);
	
	// 글 등록을 한 후 목록보기 요청을 바로 실행
	response.sendRedirect("messageReceiveList.jsp");
%>