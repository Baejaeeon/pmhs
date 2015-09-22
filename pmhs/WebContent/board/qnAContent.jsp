<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#basicInfo, #commandList {
		width: 500px;
		height: 40px;
		margin: auto;
		background: orange;
		text-align: center;
	}
	#contentInfo {
		width: 500px;
		height: 300px;
		margin: auto;
		overflow: auto;
		border: 1px solid orange;
	}
</style>
</head>
<body>
<section id = "basicInfo">
작성자 : ${article.writer } 글제목 : ${article.subject } 
작성일 : ${article.reg_date }  조회수 : ${article.readcount } 
</section>
<section id = "contentInfo">
	${article.content } 
</section>
<section id = "commandList">
	<a href = "boardUpdateForm.bo?num=${article.num }&pageNum=${pageNum}">글수정</a>
	<a href = "boardDeleteForm.bo?num=${article.num }&pageNum=${pageNum}">글삭제</a>
	<a href = "list.jsp?pageNum=${pageNum}">글목록보기</a>
	<a href = "boardWriteForm.bo?num=${article.num }&ref=${article.ref }&re_step=${article.re_step }&re_level=${article.re_level}">답변글쓰기</a>
</section>
</body>
</html>