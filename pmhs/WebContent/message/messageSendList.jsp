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
<title>발신목록보기</title>

<script>
	function checkAll(){
		if(document.forms[0].num.length == undefined){
			document.getElementById("num").checked = document.forms[0].allCheck.checked;}
		else{for(i = 0; i < document.forms[0].num.length; i++){
			document.forms[0].num[i].checked = document.forms[0].allCheck.checked;}}}
	function messageWrite(){
		location.href = "messageWriteForm.msg"
	}
	function messageReceiveList(){
		location.href = "messageReceiveList.msg"
	}
</script>

<style type="text/css">
#top{height:30px; background-color: #424242; text-align: right; color: white; font-size: 130%; text-indent: -50px; font-weight: bolder;}
#notlist{background: #ffca6c; text-align: center;}
#button{text-align: right;}
#h_title{table-layout: auto; margin: auto; text-align: center; width: 430px;}
#h_checkbox{width: 5px; border-bottom-style: inset; border-right-style: inset;}
#h_receiver{width: 50px; border-bottom-style: inset; border-right-style: inset;}
#h_subject{width: 130px; border-bottom-style: inset; border-right-style: inset;}
#h_regdate{width: 80px; border-bottom-style: inset;}
#b_checkbox{width: 5px; border-right-style: inset;}
#b_receiver{width: 50px; border-right-style: inset;}
#b_subject{width: 130px; border-right-style: inset;}
#b_regdate{width: 80px;}
#pagecount{line-height: 3; text-align: center; border-top-style: inset;}
</style>
</head>

<body>
<form action="messageSendDelete.msg" method="post" enctype="multipart/form=data" name="messageDelete" onsubmit="javascript:return checkit(this)">
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm");
	List<MessageVO> sendList = (List<MessageVO>)request.getAttribute("sendList");
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
	if(sendList == null || sendList.size() == 0){
	%>
		<section id=top>
	<pre><font>발신목록보기   </font></pre>
	</section>
	<br>
	
	<section id=button>
	<a href="javascript:messageWrite()"><img src="img/messageWrite.jpg"></a>
	<a href="javascript:messageReceiveList()"><img src="img/messageReceiveList.jpg"></a>
	<input type="image" src="img/messageDelete.jpg"></section>
		<div id=notlist>보낸 메시지가 없습니다.</div>
	<%
	}
	else{
	%>
	
	<section id=top>
	<pre><font>발신목록보기   </font></pre>
	</section>
	<br>
	
	<section id=button>
	<a href="javascript:messageWrite()"><img src="img/messageWrite.jpg"></a>
	<a href="javascript:messageReceiveList()"><img src="img/messageReceiveList.jpg"></a>
	<input type="image" src="img/messageDelete.jpg"></section>
	
	<table id=h_title>
			<tr>
				<td id = h_checkbox><input type="checkbox" name="allCheck" onClick="checkAll()" /></td>
				<td id = h_receiver>받는사람</td>
				<td id = h_subject>제목</td>
				<td id = h_regdate>작성일</td>
			</tr>
	
				<%
					for(int i = 0; i < sendList.size(); i++) {
				%>
				<tr>
				<td id=b_checkbox><input type="checkbox" name="num" id="num" value="<%=sendList.get(i).getMessageNum() %>" /></td>
				<td id=b_receiver><%=sendList.get(i).getMessageReceiver() %></td>
				<td id=b_subject><a href="messageSendContent.msg?num=<%=sendList.get(i).getMessageNum() %>&pageNum=<%=pageNum%>" style="text-decoration: none;"><%=sendList.get(i).getTitle() %></a></td>
					<!-- 게시물 상세보기 요청 링크를 걸어준다. 해당 글을 구분할 수 있는 값인 num값을 파라미터로 던져준다. -->
				<td id=b_regdate><%=sdf.format(sendList.get(i).getMessageReg_date()) %></td>
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
	
	<a href="messageSendList.msg?pageNum=<%=startPage - 10 %>" style="text-decoration: none;">[이전]</a>
	
	<%
		}
		// 이전 그룹의 startPage로 이동
		for(int i = startPage; i < endPage; i++) {
	%>
	
	<a href="messageSendList.msg?pageNum=<%=i %>" style="text-decoration: none;">[<%=i %>]
	</a>
	
	<!-- startPage부터 endPage까지 출력하기 -->
	<%
		}
		if(endPage < pageCount) {
	%>
	
	<a href="messageSendList.msg?pageNum=<%=startPage + 10 %>" style="text-decoration: none;">[다음]</a>

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