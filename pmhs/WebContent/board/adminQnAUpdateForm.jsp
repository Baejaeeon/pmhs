<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
<body>
<jsp:include page="../header.jsp"/>
	<section id = "writeFormArea">
		<h2>문의 사항</h2>
		<form action="adminQnABoardUpdatePro.boa" method = "POST">
			<input type = "hidden" name = "num" value = "${article.num }">
			<input type = "hidden" name = "pageNum" value = "${pageNum }">
			<!-- 원래 페이지로 되 돌아갈 수 있게 pageNum을 던져준다. -->
			<table>
				<tr>
					<td class = "td_left">
						<label for = "writer">작성자</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "writer" id = "writer"
						value = "${article.writer }" readonly="readonly" />
						<!-- 각각의 정보를 value를 통해서 가져와서 보여준다. -->
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "subject">제목</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "subject" id = "subject"
						/>
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "email">이메일</label>
					</td>
					<td class = "td_right">
						<input type = "text" name = "email" id = "email"
						value = "${article.email }" />
					</td>
				</tr>
				<tr>
					<td class = "td_left">
						<label for = "content">내용</label>
					</td>
					<td class = "td_right">
						<textarea name = "content" id = "content" 
							rows = "16" cols = "50">${article.content }</textarea>
						<!-- textArea는 textArea태그 사이에 이전 값을 넣어준다. -->
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
			</form>	
	</section>
		<div id="command">
				 	<input type="image" src="img/boardWrite.jpg" onclick="javaScript:document.form1.submit()" style="float: right; margin-top: 5px; "/>
				 	<a href="adminQnABoardList.boa"><img src ="img/boardCancel.jpg" style="float: right; margin-top: 5px;"/></a>				
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>