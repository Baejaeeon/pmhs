<%@page import="pmhs.web.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href = "index.jsp">Home</a>
<a href = "introduceForm.jsp">시스템 소개</a>
<a href = "adminNoticeBoardList.boa">공지사항</a>
<a href = "adminQnABoardList.boa">문의사항</a>
<a href = "noticeBoardList.bo">공지사항</a>
<a href = "qnABoardList.bo">문의사항</a>
<a href = "memberMain.mem">회원정보</a>
<a href = "memberAdminMain.mema">회원관리</a>
<a href = "errorPcList.pca">PC정보</a>
<a href = "memberRegistForm.mem">회원가입</a>
<a href = "reservationList.pca">예약정보</a>
<a href = "messageReceiveList.msg">메시지</a>
<a href = "pcSelectForm.pc">PC선택하기</a>
<%
   Member loginUser = (Member)session.getAttribute("loginUser");

if(loginUser != null){
%>
${loginUser.m_id }님이 로그인 하셨습니다.<br>
   <a href = "logout.mem">로그아웃</a>
<%
   }
   else{
%>	   

   <a href = "loginForm.mem">로그인</a>
<%
   }
%>
</body>
</html>