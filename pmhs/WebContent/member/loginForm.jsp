<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#loginFormArea{
width : 300px;
height : 200px;
margin:auto;
border : 1px solid orange;
}
h2,fieldset{
  text-align: center;
}
</style>
</head>
<body>
<section id = "loginFormArea">
<h2>로그인</h2>
<form action="loginPro.mem"  method = "post">
<fieldset>
 <label for ="id"> 아이디 : </label>
 <input type = "text" name ="id" id = "id"/><br>
 <label for ="passwd"> 비밀번호 : </label>
 <input type = "password" name ="passwd" id = "passwd"/><br>
 <input type = "submit" value = "로그인">
  </fieldset>
  </form>
  </section>
</body>
</html>