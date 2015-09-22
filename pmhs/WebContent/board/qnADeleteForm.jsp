<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#deleteForm {
		width: 300px;
		height: 200px;
		margin: auto;
		border: 1px solid blue;
	}
	fieldset {
		text-align: center;
	}
</style>
</head>
<body>
<section id = "deleteForm">
<form action="boardDeletePro.bo" method="POST">
	<input type = "hidden" name = "num" value = "${article.num }" />
	<input type = "hidden" name = "pageNum" value = "${pageNum }" />
	<!-- 삭제 처리를 위해 글 번호와 페이지 번호를 hidden으로 받아온다. -->
	<fieldset>
		<legend>비밀번호 입력</legend>
		<label id = "passwd">비밀번호 : </label>
		<input type = "password" name = "passwd" id = "passwd" />
		<input type = "submit" value = "삭제" />
	</fieldset>
</form>
</section>
</body>
</html>