<%@page import="pmhs.web.message.vo.MessageVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function messageReceiveListHome() {
		location.href = "messageReceiveList.msg";
	}
	function messageWriteForm() {
		location.href = "messageWriteForm.msg";
	}
	function checkAll(theForm) {
		if (theForm.deleteMessage.length == undefined) {
			theForm.deleteMessage.checked = theForm.allCheck.checked;
		} else {
			for (var i = 0; i < theForm.deleteMessage.length; i++) {
				theForm.deleteMessage[i].checked = theForm.allCheck.checked;
			}
		}
	}
</script>
<style>
.tr_top {
	background-color: #D5D5D5;
}
table{
	border:solid 1px #D5D5D5;
}
</style>
</head>
<body>
	<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//글 등록일을 출력할 때 사용될 포멧

		List<MessageVO> articleList = (List<MessageVO>) request
				.getAttribute("messageList");
	%>
	<div style="width: 100%;">
		<div class="bbs_top_info">
		<img width="30%"  src="image/messageList.png">
			<img id="hr" width="100%" height="5px" src="image/hr1.png">
			<div style="height: 425px; background-color: #3bbc9d">
			
		<br>
		<div style="height:330px; margin: 40px; background-color: white;">
		<br>
			<img src="image/icon_list.gif" align="middle" alt="" /><font style="font-family: a탈영고딕L">전체</font>
			<b class="extra02"><%=(articleList == null) ? 0: articleList.size()%></b>
		


		<section id="listForm"> 
		<div align="right"> 
		<a href="javascript:messageWriteForm()"><img
							src="image/message/bu_message_send.jpg" border="0" alt="목록"></a> 
		</div>
		<%
 	if (articleList == null) {
 %>
 <div class="div_empty" style="text-align: center;font-size: 14px;"><font style="font-family: a탈영고딕L">수신된 쪽지가 없습니다.</font></div>
		<%
			} else {
 		int number = articleList.size();
		%>
		<form method="post" action="messageRemove.msg">

			
			<table class="list_bar0" summary="목록">
			
				
				<thead>
					<tr>
						<td class="tr_top" width="6%" class="list_bar1"><input type="checkbox"
							id="allCheck" onClick="checkAll(this.form)"></td>
						<td class="tr_top" width="6%" class="list_bar1"><font style="font-family: a탈영고딕L">번호</font></td>
						<td class="tr_top" width="*" class="list_bar1 list_line"><font style="font-family: a탈영고딕L">내용</font></td>
						<td class="tr_top" width="15%" class="list_bar1 list_line"><font style="font-family: a탈영고딕L">발신자</font></td>
						<td class="tr_top" width="20%" class="list_bar1 list_line"><font style="font-family: a탈영고딕L">날짜</font></td>
					</tr>
				</thead>
				<tbody>

					<%
						for (int i = 0; i < articleList.size(); i++) {
					%>

					<tr align="center">
						<td class="list"><font style="font-family: a탈영고딕L"><input type="checkbox" name="deleteMessage"
							id="deleteMessage" value="<%=articleList.get(i).getMessageNum()%>"></font></td>
						<td class="list"><font style="font-family: a탈영고딕L"><%=number--%></font></td>
						<td align="left" class="list"><font style="font-family: a탈영고딕L"><a
							href="messageContent.msg?m_num=<%=articleList.get(i).getMessageNum()%>"><%=(articleList.get(i).getMessageText().length() > 20) ? articleList
							.get(i).getMessageText().substring(0, 20)
							: articleList.get(i).getMessageText()%></a></font>
						</td>
						<td class="list"><font style="font-family: a탈영고딕L"><%=articleList.get(i).getMessageWriter()%></font></td>
						<td class="list"><font style="font-family: a탈영고딕L"><%=sdf.format(articleList.get(i).getMessageReg_date())%></font></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<br>
			<br>
			
			<div class="bbs_area_view"  align="center">
			
						<a href="javascript:messageReceiveListHome()"><img
							src="image/bu_list.gif" border="0" alt="목록"></a> 
						<input type="image" src="image/bu_delete.gif" alt="삭제">

			</div>
			
		</form>
		
		
		<%
			}
		%> </section>
		
		</div>
		</div>
		</div>
	</div>
</body>
</html>