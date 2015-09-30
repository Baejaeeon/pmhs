<%@page import="pmhs.web.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
%>
<% if(loginUser != null){
	%>
<h2>회원 정보</h2>
  ${loginUser.m_name }님<br>  
  ${loginUser.m_email }<br>
         <br>
         <br>
         <a href = "memberDetail.mem">회원정보상세보기</a>
         <a href = "logout.mem">로그아웃</a>
<%
}
%>
         
</body>
</html>