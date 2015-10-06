<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#deleteForm {
		margin: auto;
		width: 80%;
		height: 500px;
		border: 1px;
		border-bottom: 1px dotted; 
		font-family: "맑은고딕";
		font-size: 15px;
	    font-weight: bold;
	
	}
	fieldset {
		text-align: center;
		}
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<section id = "deleteForm">
<form action="qnABoardDeletePro.bo" method="POST">
	<input type = "hidden" name = "num" value = "${num }" />
	<input type = "hidden" name = "pageNum" value = "${pageNum }" />
	<!-- 삭제 처리를 위해 글 번호와 페이지 번호를 hidden으로 받아온다. -->
	<fieldset>
		<legend>비밀번호를 입력해주세요</legend>
		<label id = "passwd">비밀번호 : </label>
		<input type = "password" name = "passwd" id = "passwd" />
		<input type = "submit" value = "삭제" />
	</fieldset>
</form>
</section>
<jsp:include page="../footer.jsp"/>	
</body>
</html>