<%@page import="pmhs.web.memberAdmin.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
#memberListArea {
	margin: auto;
	width: 600px;
	height: 600px;
	border: 1px double pink;
}

h2 {
	text-align: center;
}

#tr_title {
	background: pink;
}
#tr_command{
    text-align : right;
}
#memberTable {
 margin : auto;
}
</style>
<script>
 function checkAll(){
	alert("document.forms[0].allCheck.checked");
	 if(document.forms[0].delete1.length == undefined){
		 document.getElementById("delete1").checked = document.forms[0].allCheck.checked;
	 }
	 else{
		 for(i = 0; i < document.forms[0].delete1.length; i++){
			 document.forms[0].delete1[i].checked = document.forms[0].allCheck.checked;
		 }
	 } 
 }
</script>
</head>
<body>
	<%
		ArrayList<Member> memberList = (ArrayList<Member>) request.getAttribute("memberList");
		if (memberList != null && memberList.size() > 0) {
	%>
   
	<section id="memberListArea">
		<h2>회원목록</h2>
		<form action="memberRemove.mema" method ="post">
			<table id = "memberTable">
				<tr id="tr_title">
				<td><input type = "checkbox" name = "allCheck" onclick = "checkAll()"/></td>
					<td>이름</td>
					<td>아이디</td>
					<td>학번</td>
					<td>전화번호</td>
				</tr>
				<%
					for (int i = 0; i < memberList.size(); i++) {
				%>
				<tr>
				    <td><input type = "checkbox" name = "delete1" id = "delete1" value = "<%=memberList.get(i).getM_id()%>"/></td>
					<td><%=memberList.get(i).getM_name()%></td>
					<td>
					<%=memberList.get(i).getM_id()%></a>
					</td>
					<td><%=memberList.get(i).getM_studentNum()%></td>
					<td><%=memberList.get(i).getM_phone()%></td>
				</tr>
				<%
					}
				%>
				<tr>
				<td class="td_left">
				<label id = "search">검색 : </label>
				</td>
				<td class="td_right">
				<select id = "search" name = "search">
				 <option>아이디</option>
				 <option>이름</option>
				 <option>학번</option>
				 <input type = "text" name = "search" id ="search"/><input type = "button" value ="검색"/>
				</select>
				</td>
				</tr>
				<tr>
				<td colspan="6" id = "tr_command">
				  <input type = "submit" value = "삭제"/>
				</tr>
			</table>
		</form>
	</section>
	<%
		}
	%>

</body>
</html>