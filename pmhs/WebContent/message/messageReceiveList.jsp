<%@page import="pmhs.web.message.vo.MessageVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수신목록보기</title>
</head>
<body>
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	List<MessageVO> receiveList = (List<MessageVO>)request.getAttribute("receiveList");
	
	if(receiveList == null || receiveList.size() == 0){
	%>
		<h2>수신된 메시지가 없습니다.</h2>
	<%
	}
	else{
	%>
	<h2>수신목록보기</h2>
			<table>
				<tr id = "tr_title">
					<td class = "td_subject">
						제목
					</td>
					<td class = "td_writer">
						작성자
					</td>
					<td class = "td_regdate">
						작성일
					</td>
				</tr>
	<%
					for(int i = 0; i < receiveList.size(); i++) {
				%>
				<tr>
					<td class = "td_subject">
						<a href = "messageContent.msg?num=<%=receiveList.get(i).getMessageNum() %>"><%=receiveList.get(i).getTitle() %></a>
						<!-- 게시물 상세보기 요청 링크를 걸어준다. 해당 글을 구분할 수 있는 값인 num값을 파라미터로 던져준다. -->
					</td>
					<td class = "td_writer">
						<%=receiveList.get(i).getMessageWriter() %>
					</td>
					<td class = "td_regdate">
						<%=sdf.format(receiveList.get(i).getMessageReg_date()) %>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<%
			}
			%>
</body>
</html>