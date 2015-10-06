<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재작성</title>

<script>
	function messageCancel(){<%--목록--%>
		location.href = "messageSendList.msg"
	}
</script>

<style>
#top{height:30px; background-color: #424242; text-align: right; color: white; font-size: 130%; font-weight: bolder;}
#button{text-align: right;}
#subject{margin: auto; float: center;}
#small{color: white; font-size: 50%; text-align: right;}
#sb_title{height:30px; background-color: #bdbdbd; color: white; font-weight: bolder;}
#sb_content{width: 450px; border-style: inset; background-color: white;}
#receiver{margin: auto; float: center;}
#r_title{height:30px; background-color: #bdbdbd; color: white; font-weight: bolder;}
#r_content{width: 450px; border-style: inset; background-color: white;}
#content{margin: auto; float: center;}
#c_title{height:30px; background-color: #bdbdbd; color: white; font-weight: bolder;}
#c_content{width: 450px; height: 100px;}
</style>
</head>
<body>
	<%
      String pageNum = (String) request.getAttribute("pageNum");
	%>
	
	<form action="messageResendPro.msg" method = "POST"  enctype="multipart/form=data" name="messageWrite" onsubmit="javascript:return checkit(this)">
      
	<div id=top>
	<pre><font>재작성   </font></pre>
	</div>
	<br>
      
    <section id=button>
	<input type="image" src="img/messageResend.jpg">
	<a href="javascript:messageCancel()"><img src="img/messageCancel.jpg"></a>
	</section>
	
	<section id=sb_title><font>제목</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <font id=small>최대 10글자 입력 가능</font></section>
	
	<table id=subject>
   	<tr><td><input type = "text" name = "title" id = "sb_content" /></td></tr>
   	</table><br>
   	
   	<section id=r_title><font>받는사람</font></section>
   	
   	<table id=receiver>
   	<tr>
   		<td><input type = "text" name = "receiver" id = "r_content" value="${receiver }" readonly="readonly" /></td>
   	</tr>
    </table><br>
   	
   	<section id=c_title><font>내용</font></section>
   	
   	<table id=content>
    <tr>
    	<td><textarea name = "content" id = "c_content" rows = "10" cols = "25"></textarea></td>
    </tr>
    </table>           
      </form>
</body>
</html>