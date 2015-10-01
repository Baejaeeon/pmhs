<%@page import="pmhs.web.message.vo.PageInfo"%>
<%@page import="pmhs.web.message.vo.MessageVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>발신목록보기</title>
<style type="text/css">
#sendlistArea {
		margin: auto;
		width: 600px;
		height: 370px;
		border: 1px double gray;
	}
#pageArea {
		margin: auto;
		width: 600px;
		text-align: center;
	}
	table {
		margin: auto;
		width: 600px;
		border : 1px dotted gray;
		text-align: center;
	}
</style>
<script>
	function checkAll(){
		if(document.forms[0].deleteMessage.length == undefined){
			document.getElementById("deleteMessage").checked = document.forms[0].allCheck.checked;
		}
		else{
			for(i = 0; i < document.forms[0].deleteMessage.length; i++){
				document.forms[0].deleteMessage[i].checked = document.forms[0].allCheck.checked;
			}
		}
	}
</script>
</head>
<body>
	<a href = "index.jsp">Home</a>
	<a href = "messageWriteForm.msg">작성</a>
	<a href = "messageReceiveList.msg">수신목록보기</a>
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
	
<section id = "sendlistArea">
	<%
	if(sendList == null || sendList.size() == 0){
	%>
		<h2>발신한 메시지가 없습니다.</h2>
	<%
	}
	else{
	%>
	<h2>발신목록보기</h2>
	<form action = "messageSendDelete.msg" method = "post">
		<tr>
			<td><input type = "submit" value = "삭제"/> 
		</tr>
			<table>
				<tr id = "tr_title">
				<td><input type = "checkbox" name = "allCheck" onClick = "checkAll()"/></td>
					<td class = "td_subject">
						제목
					</td>
					<td class = "td_receiver">
						받는사람
					</td>
					<td class = "td_regdate">
						작성일
					</td>
				</tr>
	<%
					for(int i = 0; i < sendList.size(); i++) {
				%>
				<tr>
				<td><input type = "checkbox" name = "num" id = "deleteMessage" value="<%=sendList.get(i).getMessageNum() %>"/></td>
					<td class = "td_subject">
						<a href = "messageSendContent.msg?num=<%=sendList.get(i).getMessageNum() %>&pageNum=<%=pageNum%>"><%=sendList.get(i).getTitle() %></a>
						<!-- 게시물 상세보기 요청 링크를 걸어준다. 해당 글을 구분할 수 있는 값인 num값을 파라미터로 던져준다. -->
					</td>
					<td class = "td_receiver">
						<%=sendList.get(i).getMessageReceiver() %>
					</td>
					<td class = "td_regdate">
						<%=sdf.format(sendList.get(i).getMessageReg_date()) %>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<%
			}
			%>
			</form>
			</section>
			
			<%
		if(count > 0) { // 카운터가 0보다 클때만(글이 있을떄만 페이징 처리)
	%>
	<section id = "pageArea">
	<%
		
		
		// [이전] 페이지 출력
		if(startPage > 10) { 
			// 첫 번째 페이지 그룹이 아닐 때..
	%>
		<a href = "messageSendList.msg?pageNum=<%=startPage - 10 %>">[이전]</a>
	<%
		}
		// 이전 그룹의 startPage로 이동
		for(int i = startPage; i <= endPage; i++) {
	%>
		<a href = "messageSendList.msg?pageNum=<%=i %>">[<%=i %>]</a>
		<!-- startPage부터 endPage까지 출력하기 -->
	<%
		}
		if(endPage < pageCount) {
	%>
		<a href = "messageSendList.msg?pageNum=<%=startPage + 10 %>">[다음]</a>
	<%
		// 다음 그룹의 페이지로 넘어간다.
		}
	%>
	</section>
	<%
		}
	%>
</body>
</html>