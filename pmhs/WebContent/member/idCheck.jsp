<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean idExists = (boolean)request.getAttribute("idExists");
	String id = (String)request.getAttribute("id");
	// 자바스크립트에서 써야 하기 때문에 위에 선언
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section {
		margin: auto;
		width: 210px;
		border: 1px solid black;
	}
	
	h2 {
	    
	    font-size: 15px;
		text-align: center;
	}
</style>
<script>
	function winClose() {
		opener.document.forms[0].id.value = '<%=id%>';
			window.close();
		// 현재 팝업창을 연 것은 opener객체 이다.
	}
</script>
</head>
<body>
<%
	if(idExists) {
%>
	<section>
	<h2> <%=id %> 
	<br>아이디가 이미 존재합니다.  
 	  새로운아이디를 입력하세요.</h2>
	<form action="idCheckPro.mem">
		<input type = "text" name = "id" id = "id" placeholder="아이디" /><br>
		<input type = "submit" value = "아이디중복체크" />
	</form>
	</section>
<%
	}
	else {
%>
	<section>
		<h2><%=id %>
		<br>아이디는 사용가능한 아이디 입니다.</h2>
		<a href = "javascript:winClose()">닫기</a>
		<!-- 자바 스크립트로 링크를 걸어준다 -->
	</section>
<%
	}
%>
</body>
</html>