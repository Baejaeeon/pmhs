<%@page import="pmhs.web.boardAdmin.vo.ReplyInfo"%>
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
		width: 500px;
		height: 500px;
		border: 1px double orange;
	}
	
	h2, #td_command {
		text-align: center;
		border-bottom: 1px dotted red;
	}
	
	table {
		margin: auto;
		width: 480px;
	}
	
	.td_left {
		width: 200px;
	}
	
	.td_right {
		width: 280px;
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
	<section id = "writeFormArea">
		<h2>문의 게시판 글쓰기</h2>
		<form action="adminQnABoardWritePro.boa" method = "POST">
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
						<input type = "text" name = "writer" id = "writer"  value="${loginUser.m_id }" readonly="readonly" />
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
						<input type = "text" name = "subject" id = "subject" value = "[답변]" />
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
						rows = "10" cols = "15"></textarea>
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
				<tr>
					<td colspan = "2" id = "td_command"> <!-- colspan : 셀 합치기 -->
						<input type = "submit" value = "글등록" />
						<input type = "reset" value = "취소" />
						<input type = "button" value = "목록보기" onClick = "window.location.href='adminQnABoardList.boa'" />
						
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>