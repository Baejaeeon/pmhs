<%@page import="pmhs.web.board.vo.ReplyInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    	이 페이지에서 원글을 작성할 수도 있고 답변글을 작성할 수도 있다.
    	따라서, 작성한 글이 원글인지 답글인지를 서블릿에서 판단할 수 있어야 된다.
    	사용자가 답변글쓰기 요청을 하고 해당 페이지가 실행되었을 때는
    	답변을 할 대상글의 num, ref, re_step, re_level 값이 파라미터로 넘어온다.
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#writeFormArea {	
		margin: auto;
		width: 80%;
		height: 550px;
		border: 1px;
		border-bottom: 1px dotted; 
		font-family: "맑은고딕";
		font-size: 15px;
		
	}

	h2{
		text-align: left;
		border-bottom: 1px dotted;
	}
	table {

		margin: auto;
		width: 80%;
		
	}	
	.td_left {
		width: 50px;
		font-size: 15px; 
	
	}
	
	.td_right {
		width: 300px;
		margin: auto;
		
	}
	#command{

		margin: auto;
		width: 60%;
		
	}

</style>
</head>
<%
	ReplyInfo replyInfo = (ReplyInfo)request.getAttribute("replyInfo"); 
	int num = replyInfo.getNum();
	int ref = replyInfo.getRef();
	int re_step = replyInfo.getRe_step();
	int re_level = replyInfo.getRe_level();
%>
<body>
<jsp:include page="../header.jsp"/>
	<section id = "writeFormArea">
		<h2>문의 사항</h2>
		<form action="qnABoardWritePro.bo" name="form1" method = "POST">
			<input type = "hidden" name = "num" value = "${replyInfo.num }">
			<input type = "hidden" name = "ref" value = "${replyInfo.ref}">
			<input type = "hidden" name = "re_step" value = "${replyInfo.re_step }">
			<input type = "hidden" name = "re_level" value = "${replyInfo.re_level}">
			<!-- 원글인지 답글인지 구분을 위해 원글일 경우에도 hidden타입으로 파라미터들을 넘겨준다. -->
			<table>
				<tr>
					<td class = "td_left">
						<label for = "writer">작성자</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "writer" id = "writer" value="${loginUser.m_id }" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "subject">제목</label>
					</td>
					<td class = "td_right">
						<%
							if(request.getParameter("num") != null) { 
						
						%>
						<input type = "text" name = "subject" id = "subject" />
						<%
							}
							else {
						%>
						<input type = "text" name = "subject" id = "subject" />
						<%
							}
						%>
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "email">이메일</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "email" id = "email" />
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "content">내용</label>
					</td>
					<td class = "td_right">
						<textarea name = "content" id = "content" 
						rows = "16" cols = "50"></textarea>
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "passwd">비밀번호</label>
					</td>
					<td class = "td_right">
						<input type = "password" name = "passwd" id = "passwd" />
					</td>
				</tr>
			</table>
			
		
	</section>
	<div id="command">
				 	<input type="image" src="img/boardWrite.jpg" onclick="javaScript:document.form1.submit()" style="float: right; margin-top: 5px;" />
				 	<a href="qnABoardList.bo"><img src ="img/boardCancel.jpg" style="float: right; margin-top: 5px;"/></a>				
	</div>	
	</form>	
<jsp:include page="../footer.jsp"/>		
</body>
</html>