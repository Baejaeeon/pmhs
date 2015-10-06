<%@page import="pmhs.web.message.vo.PageInfo"%>
<%@page import="pmhs.web.message.vo.MessageVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>수신목록보기</title>

<script>
	function checkAll(){
		if(document.forms[0].num.length == undefined){
			document.getElementById("num").checked = document.forms[0].allCheck.checked;}
		else{for(i = 0; i < document.forms[0].num.length; i++){
			document.forms[0].num[i].checked = document.forms[0].allCheck.checked;}}}
	function messageWrite(){
		location.href = "messageWriteForm.msg"
	}
	function messageSendList(){
		location.href = "messageSendList.msg"
	}
</script>

<style type="text/css">
#top{height:30px; background-color: #424242; text-align: right; color: white; font-size: 130%; text-indent: -50px; font-weight: bolder;}
#notlist{background: #c8ffff; text-align: center;}
#button{text-align: right;}
#h_title{table-layout: auto; margin: auto; text-align: center; width: 430px;}
#h_checkbox{width: 5px; border-bottom-style: inset; border-right-style: inset;}
#h_sender{width: 50px; border-bottom-style: inset; border-right-style: inset;}
#h_subject{width: 130px; border-bottom-style: inset; border-right-style: inset;}
#h_regdate{width: 80px; border-bottom-style: inset;}
#b_checkbox{width: 5px; border-right-style: inset;}
#b_sender{width: 50px; border-right-style: inset;}
#b_subject{width: 130px; border-right-style: inset;}
#b_regdate{width: 80px;}
#pagecount{line-height: 3; text-align: center; border-top-style: inset;}
#minitabs a:hover{
  border-bottom:4px soild #696;
  padding-bottom:2px;
  color:#363;
}
</style>
</head>

<body>
<form action="messageDelete.msg" method="post" enctype="multipart/form=data" name="messageDelete" onsubmit="javascript:return checkit(this)">
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd  hh:mm");
	List<MessageVO> receiveList = (List<MessageVO>)request.getAttribute("receiveList");
	int pageNum = Integer.parseInt((String)request.getAttribute("pageNum"));
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int number = pageInfo.getNumber();
	int count = pageInfo.getCount();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int pageCount = pageInfo.getPageCount();
	int currentPage = pageInfo.getCurrentPage();
	%>

	<%
	if(receiveList == null || receiveList.size() == 0){
	%>
		<section id=top>
	<pre><font>수신목록보기   </font></pre>
	</section>
	<br>
	
	<section id=button>
	<a href="javascript:messageWrite()"><img src="img/messageWrite.jpg"></a>
	<a href="javascript:messageSendList()"><img src="img/messageSendList.jpg"></a>
	<input type="image" src="img/messageDelete.jpg"></section>
		<div id=notlist>받은 메시지가 없습니다.</div>
	<%
	}
	else{
	%>
	
	<section id=top>
	<pre><font>수신목록보기   </font></pre>
	</section>
	<br>
	
	<section id=button>
	<a href="javascript:messageWrite()"><img src="img/messageWrite.jpg"></a>
	<a href="javascript:messageSendList()"><img src="img/messageSendList.jpg"></a>
	<input type="image" src="img/messageDelete.jpg"></section>
	
	
	
		<table id=h_title>
			<tr>
				<td id = h_checkbox><input type="checkbox" name="allCheck" onClick="checkAll()" /></td>
				<td id = h_sender><font id=font>보낸사람</font></td>
				<td id = h_subject><font>제목</font></td>
				<td id = h_regdate><font>작성일</font></td>
			</tr>
		
		
				<%
					for(int i = 0; i < receiveList.size(); i++) {
				%>
				
			<tr>
				<td id=b_checkbox><input type="checkbox" name="num" id="num" value="<%=receiveList.get(i).getMessageNum() %>" /></td>
				<td id=b_sender><%=receiveList.get(i).getMessageWriter() %></td>
				<td id=b_subject><a href="messageContent.msg?num=<%=receiveList.get(i).getMessageNum() %>&pageNum=<%=pageNum%>" style="text-decoration: none;"><%=receiveList.get(i).getTitle() %></a></td>
				<td id=b_regdate><%=sdf.format(receiveList.get(i).getMessageReg_date()) %></td>
			</tr>
				<%
					}
				%>
		</table>
			<%
				}
			%>
	
	<section id=pagecount>
	<%
		if(count > 0) { // 카운터가 0보다 클때만(글이 있을떄만 페이징 처리)
	%>
	
	<%
		// [이전] 페이지 출력
		if(startPage > 10) { 
			// 첫 번째 페이지 그룹이 아닐 때..
	%>
	
	<a href="messageReceiveList.msg?pageNum=<%=startPage - 10 %>" style="text-decoration: none;">[이전]</a>
	
	<%
		}
		// 이전 그룹의 startPage로 이동
		for(int i = startPage; i < endPage; i++) {
	%>
	
	<a href="messageReceiveList.msg?pageNum=<%=i %>" style="text-decoration: none;">[<%=i %>]
	</a>
	
	<!-- startPage부터 endPage까지 출력하기 -->
	<%
		}
		if(endPage < pageCount) {
	%>
	
	<a href="messageReceiveList.msg?pageNum=<%=startPage + 10 %>" style="text-decoration: none;">[다음]</a>

	<%
		// 다음 그룹의 페이지로 넘어간다.
		}
	%>

	<%
		}
	%>
	</section>
</form>
</body>
</html>