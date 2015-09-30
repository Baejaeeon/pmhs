<%@page import="pmhs.web.board.vo.ReplyInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<body>
	<section id = "writeFormArea">
		<h2>공지 게시판 글쓰기</h2>
		<form action="noticeBoardWritePro.boa" method = "POST">
			<input type = "hidden" name = "num" value = "${num }">
			
			<table>
				<tr>
					<td class = "td_left">
						<label for = "writer">작성자</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "writer" id = "writer" />
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "subject">제목</label>
					</td>
					<td class = "td_right">
					
						<input type = "text" name = "subject" id = "subject" />
						
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
					<td colspan = "2" id = "td_command"> 
						<input type = "submit" value = "글등록" />
						<input type = "reset" value = "취소" />
						<input type = "button" value = "목록보기" onClick = "window.location.href='noticeBoardList.boa'" />
					
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>