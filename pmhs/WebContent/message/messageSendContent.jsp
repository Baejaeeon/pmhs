<%@page import="pmhs.web.message.vo.MessageVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>발신내용보기</title>

<style>
#top{height:30px; background-color: #424242; text-align: right; color: white; font-size: 130%; font-weight: bolder;}
#button{text-align: right;}
#subject{margin: auto; float: center;}
#sb_title{height:30px; background-color: #bdbdbd; color: white; font-weight: bolder;}
#sb_content{width: 450px; border-style: inset; background-color: white;}
#sender{margin: auto; float: center;}
#s_title{height:30px; background-color: #bdbdbd; color: white; font-weight: bolder;}
#s_content{width: 220px; border-style: inset; background-color: white;}
#s_datetitle{width: 55px; text-align: left;}
#s_datecontent{width: 220px; border-style: inset; background-color: white;}
#content{margin: auto; float: center;}
#c_title{height:30px; background-color: #bdbdbd; color: white; font-weight: bolder;}
#c_content{width: 450px; height: 100px;}
</style>
</head>

<body>
   <%
      MessageVO content = (MessageVO) request.getAttribute("article");
      String pageNum = (String) request.getAttribute("pageNum");
      SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm"); // 날짜 포맷 지정
   %>
   <div id=top>
	<pre><font>발신내용보기   </font></pre>
	</div>
	<br>
	
	<section id=button>
	<a href="messageResendForm.msg?receiver=<%=content.getMessageReceiver()%>"><img src="img/messageResend.jpg"></a>
	<a href="messageSendDelete.msg?num=<%=content.getMessageNum()%>"><img src="img/messageDelete.jpg"></a>
	<a href="messageSendList.msg?pageNum=<%=pageNum%>"><img src="img/messageSendList.jpg"></a>
	</section>
	
	<section id=sb_title><font>제목</font></section>
	
	<table id=subject>
   	<tr><td id=sb_content><%=content.getTitle()%></td></tr>
   	</table>
   	
   	<section id=s_title><pre><font>받는사람				   작성일</font></pre></section>
   	
   	<table id=sender>
   	<tr>
   		<td id=s_content><%=content.getMessageReceiver()%></td>
   		<td id=s_datecontent><%=sdf.format(content.getMessageReg_date())%></td>
   	</tr>
    </table><br>
    
    <section id=c_title><font>내용</font></section>
	
	<table id=content>
    <tr>
    	<td><textarea id=c_content style="border-style: inset;"><%=content.getMessageContent()%></textarea></td>
    </tr>
    </table>
</body>
</html>