<%@page import="pmhs.web.message.vo.MessageVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#basicInfo, #commandList {
   width: 500px;
   height: 40px;
   margin: auto;
   background: gray;
   text-align: center;
}

#contentInfo {
   width: 500px;
   height: 300px;
   margin: auto;
   overflow: auto;
   border: 1px solid gray;
}
</style>
</head>
<body>
   <%
      MessageVO content = (MessageVO) request.getAttribute("article");
      String pageNum = (String) request.getAttribute("pageNum");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 지정
   %>
   <section id="basicInfo">
      보낸사람 :
      <%=content.getMessageWriter()%>
      글제목 :
      <%=content.getTitle()%>
      작성일 :
      <%=sdf.format(content.getMessageReg_date())%>
   </section>
   <section id="contentInfo">
      <%=content.getMessageContent()%>
   </section>
   <section id="commandList">
	  <a href = "messageReplyForm.msg?writer=<%=content.getMessageWriter() %>">답장</a>
      <a href="messageDelete.msg?num=<%=content.getMessageNum()%>">글삭제</a>
      <a href="messageReceiveList.msg?pageNum=<%=pageNum%>">목록보기</a>
   </section>
</body>
</html>