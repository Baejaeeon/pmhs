<%@page import="pmhs.web.member.vo.Member"%>
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
		height: 250px;
		margin: auto;
		overflow: auto;
		border: 1px solid ;
		font-family: "맑은고딕";
		font-size: 16px;
	}
	table{
		width: 80%;
		margin: auto;
		text-align: center;
		margin-bottom: 60px;
		border-bottom: 1px dotted;
	}
	#reply_content{
	margin-left: 10%;
	margin-top:1%;
	border: solid 1px;
	font-family: "맑은고딕";
	font-size: 16px;
	}
	#reply{
	width: 80%;
	font-family: "맑은고딕";
	font-size: 16px;
    border-bottom-style: inset;
    border-bottom-color: white;
	}
	#title {
		background: #424242;
		color: white;
		text-align: center;
		height: 20px;
		font-size: 18px;
	}
	#commentList{
	border-bottom-style: inset;
    border-bottom-color: white;
	}
	.num {
		width: 55px;
		text-align: center;
	}
	.content {
		width: 500px;
	}
	.writer {
		width: 70px;
		text-align: center;
	}
	.regdate {
		width: 200px;
		text-align: center;
	}
	.delete {
		width: 55px;
		text-align: center;
	}
</style>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	ArrayList<CommentVO> comment = (ArrayList<CommentVO>)request.getAttribute("comment");
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
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
	
			<form action='qnAComment.boa' method="post">
				<table id="reply">
					<tr style="display: inline;">
						<input type="hidden" name="q_num" id="q_num" value="${article.num }">
						<textarea rows="3" cols="128" name="reply_content" id = "reply_content"></textarea>
							<input type="image" src="img/commentRegist.jpg" onclick="javaScript:document.form2.submit()" style="width: 90px;height:65px;margin:3px;margin-top:7px;"/>
					</tr>
					<%
					if (comment != null && comment.size() > 0) {
					%>
					
					<tr id="title">
						<td class="num">번호</td>
						<td class="writer">작성자</td>
						<td class="content">내용</td>
						<td class="regdate">등록일자</td>
						<td class="delete"></td>
					</tr>
					
					
					<%
						for (int i = 0; i < comment.size(); i++) {
					%>
					<tr id="commentList">
						<td><%=comment.get(i).getC_num()%></td>
						<td><%=comment.get(i).getC_writer()%></td>
						<td><%=comment.get(i).getC_content()%></td>
						<td><%=sdf.format(comment.get(i).getC_reg_date())%></td>
						<%
						 if(loginUser.getM_name().equals(comment.get(i).getC_writer())) {
						%>
							<td><a href="qnACommentDelete.boa?c_num=<%=comment.get(i).getC_num()%>&num=${article.num }&pageNum=${pageNum }"><img src="img/com_del.gif" /></a></td>
					   
					   <%
					   }
					   %>
					</tr>
					<%
						}
					%>
				</table>
					<%
						}
					%>
				</form>					
<section id = "commandList">
	<a href = "adminQnABoardUpdateForm.boa?num=${article.num }&pageNum=${pageNum}&writer=${article.writer }"><img src ="img/boardUpdate.jpg" style="margin-top: 5px; "/></a>
	<a href = "adminQnABoardList.boa?pageNum=${pageNum }"><img src ="img/boardList.jpg" style="margin-top: 5px;"/></a>
	<a href = "adminQnABoardWriteForm.boa?num=${article.num }&ref=${article.ref }&re_step=${article.re_step }&re_level=${article.re_level}"><img src ="img/boardRe.jpg" style="margin-top: 5px; "/></a>
</section>
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>