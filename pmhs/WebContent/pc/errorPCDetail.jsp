<%@page import="pmhs.web.pcAdmin.vo.ErrorPCInfo"%>
<%@page import="pmhs.web.member.vo.Member"%>
<%@page import="pmhs.web.pcInfo.vo.PCInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ErrorPCInfo errorPCInfo = (ErrorPCInfo)request.getAttribute("errorPCInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#declareFormArea {
		margin: auto;
		width: 560px;
		border-top: 1px double black;
	}
	#td_command {
		text-align: center;
	}
	table {
		margin: auto;
	}
</style>
<script>
function reservationSuccess() {
		/* document.tofile.action='pcDelarePro.pc';
		document.tofile.submit();*/
		alert("신고 완료.")
	    parent.window.opener.document.location.href="index.jsp"; 
	    window.close();
	}
function winClose() {
	    window.close();
	}
</script>
</head>
<body>
	<h2>고장PC 상세보기</h2>
	<section id="declareFormArea">
	<form action="pcReservationPro.pca" method="post">
		<table>
			<tr>
				<td>
					<label id="unit">단대</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="unit" id="unit" value="${errorPCInfo.e_unit }" readonly="readonly" />
				</td>
				<td width="15px" />
				<td>
					<label id="department">학과</label>
				</td>
				<td width="6px" />
				<td>
				<input type="text" name="department" id="department" value="${errorPCInfo.e_department }" readonly="readonly" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="lectureRoom">강의실</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="lectureRoom" id="lectureRoom" value="${errorPCInfo.e_lectureRoom }" readonly="readonly" />
				</td>
				<td width="15px" />
				<td>
					<label id="pcNum">PC번호</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="pcNum" id="pcNum" value="${errorPCInfo.p_num }" readonly="readonly" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="name">신고자</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="name" id="name" value="${errorPCInfo.e_name }" readonly="readonly" />
				</td>
				<td width="15px" />
				<td>
					<label id="id">전화번호</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="phone" id="phone" value="${errorPCInfo.e_phone }" readonly="readonly" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="symptom">고장증상</label>
				</td>
				<td width="6px" />
				<td>
					<input type="text" name="symptom" id="symptom" value="${errorPCInfo.e_errorsymptom }" readonly="readonly" />
				</td>
				<td width="6px" />
				<td>
					<label id="symptom">방문시간</label>
				</td>
				<td width="6px" />
				<td>
					<select name="time" id="time" />
						<option>9시</option>
						<option>9시 30분</option>
						<option>10시</option>
						<option>10시 30분</option>
						<option>11시</option>
						<option>11시 30분</option>
						<option>12시</option>
						<option>12시 30분</option>
						<option>13시</option>
						<option>13시 30분</option>
						<option>14시</option>
						<option>14시 30분</option>
						<option>15시</option>
						<option>15시 30분</option>
						<option>16시</option>
						<option>16시 30분</option>
						<option>17시</option>
						<option>17시 30분</option>
						<option>18시</option>
						<option>18시 30분</option>
					</select>
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td>
					<label id="content">문자내용</label>
				</td>
				<td width="6px" />
				<td colspan="5">
					<select name="content" id="content">
						<option>9시에 방문 수리 예약되었습니다.</option>
						<option>9시 30분에 방문 수리 예약되었습니다.</option>
						<option>10시에 방문 수리 예약되었습니다.</option>
						<option>10시 30분에 방문 수리 예약되었습니다.</option>
						<option>11시에 방문 수리 예약되었습니다.</option>
						<option>11시 30분에 방문 수리 예약되었습니다.</option>
						<option>12시에 방문 수리 예약되었습니다.</option>
						<option>12시 30분에 방문 수리 예약되었습니다.</option>
						<option>13시에 방문 수리 예약되었습니다.</option>
						<option>13시 30분에 방문 수리 예약되었습니다.</option>
						<option>14시에 방문 수리 예약되었습니다.</option>
						<option>14시 30분에 방문 수리 예약되었습니다.</option>
						<option>15시에 방문 수리 예약되었습니다.</option>
						<option>15시 30분에 방문 수리 예약되었습니다.</option>
						<option>16시에 방문 수리 예약되었습니다.</option>
						<option>16시 30분에 방문 수리 예약되었습니다.</option>
						<option>17시에 방문 수리 예약되었습니다.</option>
						<option>17시 30분에 방문 수리 예약되었습니다.</option>
						<option>18시분에 방문 수리 예약되었습니다.</option>
						<option>18시 30분에 방문 수리 예약되었습니다.</option>
					</select>
					<input type = "button" value="전송" />
				</td>
			</tr>
			<tr height="8px" />
			<tr>
				<td colspan="10" id = "td_command">
					<input type = "submit" value = "예약" onclick="javaScript:reservationSuccess();"/>
					<input type = "reset" value = "취소" onclick="javaScript:winClose()"/>
				</td>
			</tr>
		</table>
	</form>
	</section>
</body>
</html>