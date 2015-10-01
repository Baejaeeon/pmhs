<%@page import="pmhs.web.boardAdmin.vo.CommentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pmhs.web.board.vo.QnABoardVO"%>
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
	table{
		width: 500px;
		margin: auto;
		text-align: center;
	}
</style>
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
ArrayList<CommentVO> comment = (ArrayList<CommentVO>)request.getAttribute("comment");
%>
</head>
<body>
<section id = "basicInfo">
작성자 : ${article.writer } 글제목 : ${article.subject } 
작성일 : ${article.reg_date }  조회수 : ${article.readCount } 
</section>
<section id = "contentInfo">
	${article.content }
</section>
<form action='qnAComment.boa' method="post">

						<%
					if (comment != null && comment.size() > 0) {
				%>

				<table id="reply">
					<tr>
						<td width="40px" class="name"><font color="black" style="font-family:a탈영고딕L">번호</font></td>
						<td width="50px" class="name"><font color="black" style="font-family:a탈영고딕L">ID</font></td>
						<td width="270px" class="name"><font color="black" style="font-family:a탈영고딕L">내용</font></td>
						<td width="140px" class="name"><font color="black" style="font-family:a탈영고딕L">등록일자</font></td>
						
					</tr>
					<%
						for (int i = 0; i < comment.size(); i++) {
					%>
					<tr>
						<td><font color="black" style="font-family:a탈영고딕L"><%=comment.get(i).getC_num()%></font></td>
						<td><font color="black" style="font-family:a탈영고딕L"><%=comment.get(i).getC_writer()%></font></td>
						<td><font color="black" style="font-family:a탈영고딕L"><%=comment.get(i).getC_content()%></font></td>
						<td><font color="black" style="font-family:a탈영고딕L"><%=sdf.format(comment.get(i).getC_reg_date())%></font></td>
					</tr>
					<%
						}
					%>
					<%
						}
					%>
				</table>
<section id = "commandList">
	<a href = "qnABoardDeleteForm.boa?num=${article.num }&pageNum=${pageNum}">글삭제</a>
	<a href = "qnABoardList.boa?pageNum=${pageNum}">글목록보기</a>
	<a href = "qnABoardWriteForm.bo?num=${article.num }&ref=${article.ref }&re_step=${article.re_step }&re_level=${article.re_level}">답변글쓰기</a>
</section>
</body>
</html>