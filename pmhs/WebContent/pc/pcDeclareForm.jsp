<%@page import="pmhs.web.member.vo.Member"%>
<%@page import="pmhs.web.pcInfo.vo.PCInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int pcNum = (int) request.getAttribute("pcNum");
	ArrayList<PCInfo> pcSiteList = (ArrayList<PCInfo>) session.getAttribute("pcSiteList");
	Member member = (Member) session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#declareFormArea {
		margin: auto;
		width: 540px;
		border-top: 1px double black;
	}
	#td_command {
		text-align: center;
	}
	table {
		margin: auto;
	}
</style>
</head>
<body>
	<h2>고장 신고서 작성</h2>
	<section id="declareFormArea">
	<form action="pcDeclarePro.pc" method="post">
		<table>
			<tr>
				<td>
					<label id="unit">단대</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="unit" id="unit" value="${pcSiteList.get(pcNum).unit }" readonly="readonly" />
				</td>
				<td width="15px" />
				<td>
					<label id="department">학과</label>
				</td>
				<td width="6px" />
				<td>
				<input type="text" name="department" id="department" value="${pcSiteList.get(pcNum).department }" readonly="readonly" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="lectureRoom">강의실</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="lectureRoom" id="lectureRoom" value="${pcSiteList.get(pcNum).lectureRoom }" readonly="readonly" />
				</td>
				<td width="15px" />
				<td>
					<label id="pcNum">PC번호</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="pcNum" id="pcNum" value="${pcNum }" readonly="readonly" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="name">신고자</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="name" id="name" value="${member.m_name }" readonly="readonly" />
				</td>
				<td width="15px" />
				<td>
					<label id="id">전화번호</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="pcNum" id="pcNum" value="${member.m_phone }" readonly="readonly" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="symptom">고장증상</label>
				</td>
				<td width="6px" />
				<td colspan="3">
					<input type="radio" name="symptom" id="symptom" value="H/W" />H/W
					<input type="radio" name="symptom" id="symptom" value="S/W" />S/W
					<input type="radio" name="symptom" id="symptom" value="N/W" />N/W
					<input type="radio" name="symptom" id="symptom" value="ETC" />ETC
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td colspan="10" id = "td_command">
					<input type = "submit" value = "작성"/>
					<input type = "reset" value = "취소"/>
				</td>
			</tr>
		</table>
	</form>
	</section>
</body>
</html>