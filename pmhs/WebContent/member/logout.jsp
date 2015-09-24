<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
쿠키 정보를 제거하려면 해당 쿠키의 생존기간을 0으로 주면 됨.
 -->
 <%
session.invalidate();
 response.sendRedirect("index.jsp");
 %>