<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#basicInfo {
		width: 80%;
		height: 70px;
		margin: auto;
		background: #424242;
		text-align: left;
		font-family: "맑은고딕";
		color:white;
		font-size: 16px;
	}

   #commandList{
        width: 80%;
		height: 70px;
		margin: auto;
		border-top-style: outset;
		text-align: center;
		font-family: "맑은고딕";
		font-size: 16px;
  
   }
	#contentInfo{
		width: 80%;
		height: 300px;
		margin: auto;
		overflow: auto;
		border: 1px solid ;
		font-family: "맑은고딕";
		font-size: 16px;
	}
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<section id = "basicInfo">
글제목 : ${article.subject } <br>
작성자 : ${article.writer } 작성일 : ${article.reg_date }  조회수 : ${article.readCount } 
</section>
<section id = "contentInfo">
	${article.content } 
</section>
<section id = "commandList">
	<a href = "noticeBoardUpdateForm.boa?num=${article.num }&pageNum=${pageNum}&writer=${article.writer }"><img src ="img/boardUpdate.jpg" style="margin-top: 5px; "/></a>
	<a href = "noticeBoardDeleteForm.boa?num=${article.num }&pageNum=${pageNum}"><img src ="img/boardDelete.jpg" style="margin-top: 5px; "/></a>
	<a href = "adminNoticeBoardList.boa?pageNum=${pageNum}"><img src ="img/boardList.jpg" style="margin-top: 5px; "/></a>
</section>
<jsp:include page="../footer.jsp"/>	
</body>
</html>
</body>
</html>