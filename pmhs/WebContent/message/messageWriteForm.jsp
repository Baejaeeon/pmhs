<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 작성</title>
</head>
<body>
<section id = "writeFormArea">
		<h2>메시지 작성</h2>
		<form action="messageWritePro.msg" method = "POST">
			<table>
				<tr>
					<td class = "td_left">
						<label for = "writer">제목</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "title" id = "title" />
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "subject">수신자</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "receiver" id = "receiver" />
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "content">내용</label>
					</td>
					<td class = "td_right">
						<textarea name = "text" id = "text" 
						rows = "10" cols = "25"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan = "2" id = "td_command"> <!-- colspan : 셀 합치기 -->
						<input type = "submit" value = "전송" />
						<input type = "button" value = "취소" onClick = "window.location.href='messageReceiveList.jsp'" />
						<!-- location : 현재 url을 가지고 있다. 취소를 누르면 수신목록보기로 페이지를 이동한다. -->
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>