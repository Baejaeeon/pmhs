<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
#registFormArea {
	margin: auto;
	width: 500px;
	height: 500px;
	border: 1px double orange;
}

h2,#td_command {
	text-align: center;
	border-bottom: 1px dotted red;
}

table {
	width: 480px;
	margin: auto;
}

.td_left {
	width: 200px;
}

.td_right {
	width: 280px;
}
 #id {
   width : 100px;
 }
 #zip1,#zip2{
   width : 50px;
 }
</style>
<script>
  function winOpen(){
	//자바스크립트는 화면을 제어 할수 있는 수많은 내장 객체들을 제공하고, 각 내장 객체들은 
	//속성과 메소드가 제공된다.
	window.open("idCheckPro.mem?id=" + document.forms[0].id.value,"window1","width=300,height=300");
  }
  function zipSearch() {
	  window.open("zipcodeSearch.mem","window2","width=300,height=400");
  }
</script>
<body> 
	<section id="registFormArea">
	<form action = "memberRegistPro.mem" method="POST">
		
		<h2>회원가입</h2>
		<table>
			<tr>
				<td class="td_left">
				<label id = "id">회원아이디</label>
				</td>
				<td class="td_right">
				<input type = "text" name = "id" id = "id"/>
				<input type = "button" value ="아이디 중복체크" onclick="winOpen()"/>
				</td>
				</tr>
			<tr>
				<td class="td_left">
				<label id = "name">회원이름</label>
				</td>
				<td class="td_right">
				<input type = "text" name = "name" id = "name" required = "required"/>
				</td>
				</tr>
				<tr>
				<td class="td_left">
				<label id = "studentNum">학번</label>
				</td>
				<td class="td_right">
				<input type = "text" name = "studentNum" id = "studentNum" required = "required"/>
				</td>
				</tr>
			<tr>
				<td class="td_left">
				<label id = "passwd">비밀번호</label>
				</td>
				<td class="td_right">
				<input type = "password" name = "passwd" id = "passwd" required = "required"/>
				</td>
				</tr>
				
			<tr>
				<td class="td_left">
				<label id = "passwd2">비밀번호확인</label>
				</td>
				<td class="td_right">
				<input type = "password" name = "passwd2" id = "passwd2" required = "required"/>
				</td>
				</tr>
				
			<tr>
	            <td class="td_left">
	            <label id = "zipcode1">우편번호</label>
	            </td>
	            <td class="td_right">
	            <input type = "text" name = "zipcode1" id = "zipcode1" required = "required"/>-
	            <input type = "text" name = "zipcode2" id = "zipcode2" required = "required"/>
	            <input type = "button" value = "우편번호 찾기" onclick = "zipSearch()"/>
	            </td>
            </tr>
        	<tr>
	            <td class="td_left">
	            <label id = "address1">기본주소</label>
	            </td>
	            <td class="td_right">
	            <input type = "text" name = "address1" id = "address1" required = "required" 
	            readonly="readonly"/>
	            </td>
            </tr>
			<tr>
				<td class="td_left">
				<label id = "address2">상세주소</label>
				</td>
				<td class="td_right">
				<input type = "text" name = "address2" id = "address2" required = "required" />
				</td>
				</tr>
				
				<tr>
				<td class="td_left">
				<label id = "birthDay">생년월일</label>
				</td>
				<td class="td_right">
				<input type = "date" name = "birthDay" id = "birthDay" required = "required"/>
				
				</td>
				</tr>
				
				<tr>
				<td class="td_left">
				<label id = "email">이메일</label>
				</td>
				<td class="td_right">
				<input type = "email" name = "email" id = "email" required = "required"/>
				
				</td>
				</tr>
				
				<tr>
				<td class="td_left">
				<label id = "phone">전화번호</label>
				</td>
				<td class="td_right">
				<input type = "text" name = "phone" id = "phone" required = "required"/>
				
				</td>
				</tr>
				
				<tr>
				<td class="td_left">
				<label id = "gender">성별</label>
				</td>
				<td class="td_right">
				<input type = "radio" name = "gender" id = "gender" value = "M"/>남자
				<input type = "radio" name = "gender" id = "gender" value = "F"/>여자
				
				</td>
				</tr>
				
				<tr>
				   <td colspan="2" id = "td_command">
				      <input type = "submit" value = "회원가입"/>
				   </td>
				
		</table>
</form>

	</section>
</body>
</html>