<%@page import="pmhs.web.member.vo.Member"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="pmhs.web.pcInfo.vo.PCInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script>
	var xmlHttp;
	
	window.onload = function(){
		var floor = document.getElementById("floor");
		
		floor.onchange = function() {
			refreshLibSitList();
		};
	};
	
	function createXMLHttpRequest(){
		if(window.ActiveXObject){ // IE 7 미만 버전인 경우
			xmlHttp = new ActiveXObject("Mircosoft.XMLHTTP");
		} else if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}
	}
	
	function refreshLibSitList() {
		var floor = document.getElementById("floor").value;
		
		if (floor == "") {
			clearLibSitList();
				// 하단에 출력된 목록을 다 지워주는 함수
			return;
		}
		
		var url = "libRefreshLibSitList.lib?" + createQueryString(floor)
				+ "&ts=" + new Date().getTime();
		
		createXMLHttpRequest();
		
		xmlHttp.onreadystatechange = handleStateChange;
		
		//--- 요청 초기화
		xmlHttp.open("GET", url, true);
			// true : 요청을 비 동기방식으로 처리 하겠다는 의미
		
		//--- 요청 전송
		xmlHttp.send(null);
			// GET 방식으로 요청을 전송할 때는 파라미터 값은 null
	}
	
	function createQueryString(floor) {
		var queryString = "floor=" + floor;
		return queryString;
	}
	
	function handleStateChange(){
		if(xmlHttp.readyState == 4){ // readyState : Ajax 통신상태
			// 4 : 서버에서 응답이 완전하게 반환된 경우
			
			if(xmlHttp.status == 200){ // status : 응답 상태 코드
				updateLibSitList();
			}
		}
	}
	
	function updateLibSitList() {
		clearLibSitList();
		
		var libSitList = document.getElementById("libSitList");
		var results = xmlHttp.responseXML.getElementsByTagName("libSit");
		var option = null;
		
		for (var i = 0; i < results.length; i++) {
			option = document.createElement("option");
			option.appendChild(document.createTextNode(results[i].firstChild.nodeValue));
			libSitList.appendChild(option);
		}
	}
	
	function clearLibSitList() {
		var libSitList = document.getElementById("libSitList");
		
		while (libSitList.childNodes.length > 0) {
			libSitList.removeChild(libSitList.childNodes[0]);
		}
		
		option = document.createElement("option");
		option.appendChild(document.createTextNode("Select Sit"));
		libSitList.appendChild(option);
	}

	function sit(floor,pcSitNum){
		//alert(floor+libSitNum);
		window.open("pcDeclareForm.pc?floor=" + floor + "&pcSitNum=" + pcSitNum,"window1","width=550,height=300");
		//var url = "pcDeclareForm.pc?floor=" + floor + "&pcSitNum=" + pcSitNum;
		//location.href = url;
		//alert(url);
	}
	function errorMsg(){
		alert("이미 고장신고, 예약 접수가 되어있습니다.");	
	}
	/* $(document).ready(function(){
		$('.sit').click(function(){
			 $.ajax({
				url:"/libSitAppl.lib?",
				cache:false,
				dataType: "text",
				success : function(data){
					$("div").html(data);
				}
			});
		});
	}); */
</script>
</head>
<body>
 <%
 	ArrayList<PCInfo> pcSiteList = (ArrayList<PCInfo>)session.getAttribute("pcSiteList"); 
 	Member member = (Member)session.getAttribute("loginUser");
 %>
<jsp:include page="../header.jsp"/>
<form action="pcSelectForm.pc" method = "post">
	<span style="font-weight: bold; margin-left: 50px">PC 선택 : </span>
	<select id="unit">	
		<option value="">공과 대학</option>
		<option value="1" >B 1</option>
		<option value="2">1 F</option>
		<option value="3">2 F</option>
		<option value="4">3 F</option>
		<option value="5">4 F</option>		
		<option value="6">5 F</option>
	</select>
	<select id="department">	
		<option value="">IT 공학부</option>
		<option value="1" >B 1</option>
		<option value="2">1 F</option>
		<option value="3">2 F</option>
		<option value="4">3 F</option>
		<option value="5">4 F</option>		
		<option value="6">5 F</option>
	</select>
	<select id="lectureRoom">	
		<option value="">504</option>
		<option value="1" >B 1</option>
		<option value="2">1 F</option>
		<option value="3">2 F</option>
		<option value="4">3 F</option>
		<option value="5">4 F</option>		
		<option value="6">5 F</option>
	</select>
	<input type = "submit" value = "검색" />
	</form>
	<div>
	</div>		
	
	<div style="margin-left: 250px">
	<table width=750px border=0">
		<tr>
			<td align=center>
				<table BORDER="0">
					<tr>
						<td>
							<h1>PC 정보</h1><font color="red" size="3px">해당 고장 PC 자리를 클릭해서 고장신고서를 작성하면 됩니다.</font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	
	<table border=2 width=750px height="400px" cellspacing='0' cellpadding='0' bordercolor='#424242' bbgcolor='808080'>
		<tr>
			<td valign=top>
	
	<table width='750px' border='0' cellspacing='0' cellpadding='0' bbgcolor='#000000'>
		<tr>
			<td width=100%>
				<table width=100% border=0>
					<tr>
						<td align="right" width=50%>
							<font style='color:black;font-size:9pt;font-weight:900;'>신고 색상 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 정상 색상  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 예약 색상 </font>
							<table width=220 height=21 border='1' cellpadding='0' cellspacing='0'>
								<tr>
									<td width=70 bgcolor='red' align='center' valign='middle'>
										<font style='color:white;font-size:8pt;font-weight:900;'>신고중</font>
									</td>
									<td width=25></td>
									<td width=70 bgcolor='green' align='center' valign='middle'>
										<font style='color:white;font-size:8pt;font-weight:900;'>정상</font>
									</td>
									<td width=25></td>
									<td width=70 bgcolor='blue' align='center' valign='middle'>
										<font style='color:white;font-size:8pt;font-weight:900;'>예약중</font>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width='100%'>
			<!-- ======================================= MAPTEMP 시작 =====================================-->
			<div id="maptemp" style="position:relative; left:0px; top:0px;">
			<div class="sit" onclick="<%if(pcSiteList.get(0).getIsReservation() == 1 || pcSiteList.get(0).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','1')<%} %>"id="Layer1" style="position:absolute; left:192.913385826772px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:23"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(0).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(0).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>1</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(1).getIsReservation() == 1 || pcSiteList.get(1).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','2')<%} %>"id="Layer2" style="position:absolute; left:192.913385826772px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:24"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(1).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(1).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>2</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(2).getIsReservation() == 1 || pcSiteList.get(2).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','3')<%} %>"id="Layer3" style="position:absolute; left:237.795275590551px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:21"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(2).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(2).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>3</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(3).getIsReservation() == 1 || pcSiteList.get(3).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','4')<%} %>"id="Layer4" style="position:absolute; left:314.173228346457px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:16"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(3).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(3).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"   align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>4</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(4).getIsReservation() == 1 || pcSiteList.get(4).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','5')<%} %>"id="Layer5" style="position:absolute; left:359.055118110236px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:13"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(4).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(4).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>5</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(5).getIsReservation() == 1 || pcSiteList.get(5).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','6')<%} %>"id="Layer6" style="position:absolute; left:435.433070866142px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:8"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(5).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(5).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>6</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(6).getIsReservation() == 1 || pcSiteList.get(6).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','7')<%} %>"id="Layer7" style="position:absolute; left:480.314960629921px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:5"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(6).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(6).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>7</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(7).getIsReservation() == 1 || pcSiteList.get(7).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','8')<%} %>"id="Layer8" style="position:absolute; left:192.913385826772px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:19"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(7).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(7).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>8</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(8).getIsReservation() == 1 || pcSiteList.get(8).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','9')<%} %>"id="Layer9" style="position:absolute; left:237.795275590551px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:18"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(8).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(8).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>9</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(9).getIsReservation() == 1 || pcSiteList.get(9).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','10')<%} %>"id="Layer10" style="position:absolute; left:314.173228346457px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:11"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(9).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(9).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"   align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>10</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(10).getIsReservation() == 1 || pcSiteList.get(10).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','11')<%} %>"id="Layer11" style="position:absolute; left:359.055118110236px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:10"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(10).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(10).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>11</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(11).getIsReservation() == 1 || pcSiteList.get(11).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','12')<%} %>"id="Layer12" style="position:absolute; left:435.433070866142px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:3"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(11).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(11).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>12</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(12).getIsReservation() == 1 || pcSiteList.get(12).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','13')<%} %>" id="Layer13" style="position:absolute; left:480.314960629921px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:2"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(12).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(12).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>13</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(13).getIsReservation() == 1 || pcSiteList.get(13).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','14')<%} %>"id="Layer14" style="position:absolute; left:192.913385826772px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:20"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(13).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(13).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>14</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(14).getIsReservation() == 1 || pcSiteList.get(14).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','15')<%} %>"id="Layer15" style="position:absolute; left:237.795275590551px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:17"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(14).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(14).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>15</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(15).getIsReservation() == 1 || pcSiteList.get(15).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','16')<%} %>"id="Layer16" style="position:absolute; left:314.173228346457px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:12"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(15).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(15).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>16</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(16).getIsReservation() == 1 || pcSiteList.get(16).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','17')<%} %>"id="Layer17" style="position:absolute; left:359.055118110236px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:9"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(16).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(16).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>17</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(17).getIsReservation() == 1 || pcSiteList.get(17).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','18')<%} %>"id="Layer18" style="position:absolute; left:435.433070866142px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:4"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(17).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(17).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>18</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(18).getIsReservation() == 1 || pcSiteList.get(18).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','19')<%} %>" id="Layer19" style="position:absolute; left:480.314960629921px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:1"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(18).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(18).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>19</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(19).getIsReservation() == 1 || pcSiteList.get(19).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','20')<%} %>"id="Layer20" style="position:absolute; left:192.913385826772px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:25"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(19).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(19).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>20</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(20).getIsReservation() == 1 || pcSiteList.get(20).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','21')<%} %>"id="Layer21" style="position:absolute; left:237.795275590551px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:26"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(20).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(20).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>21</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(21).getIsReservation() == 1 || pcSiteList.get(21).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','22')<%} %>"id="Layer22" style="position:absolute; left:314.173228346457px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:27"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(21).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(21).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>22</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(22).getIsReservation() == 1 || pcSiteList.get(22).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','23')<%} %>"id="Layer23" style="position:absolute; left:359.055118110236px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:28"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(22).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(22).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>23</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(23).getIsReservation() == 1 || pcSiteList.get(23).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','24')<%} %>"id="Layer24" style="position:absolute; left:435.433070866142px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:29"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(23).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(23).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>24</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(24).getIsReservation() == 1 || pcSiteList.get(24).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','25')<%} %>"id="Layer25" style="position:absolute; left:480.314960629921px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:30"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(24).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(24).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>25</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(25).getIsReservation() == 1 || pcSiteList.get(25).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','26')<%} %>"id="Layer26" style="position:absolute; left:192.913385826772px; top:208.816753926702px; width:44.8818897637795px; height:30.7853403141361px; z-index:31"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(25).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(25).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>26</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(26).getIsReservation() == 1 || pcSiteList.get(26).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','27')<%} %>"id="Layer27" style="position:absolute; left:237.795275590551px; top:208.816753926702px; width:44.8818897637795px; height:32.2513089005236px; z-index:22"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(26).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(26).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>27</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(27).getIsReservation() == 1 || pcSiteList.get(27).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','28')<%} %>"id="Layer28" style="position:absolute; left:314.173228346457px; top:208.816753926702px; width:44.8818897637795px; height:32.2513089005236px; z-index:15"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(27).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(27).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>28</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(28).getIsReservation() == 1 || pcSiteList.get(28).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','29')<%} %>"id="Layer29" style="position:absolute; left:359.055118110236px; top:208.816753926702px; width:44.8818897637795px; height:32.2513089005236px; z-index:14"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(28).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(28).getIsDeclare() == 1){%>red<%} else {%>green<%} %>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>29</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(29).getIsReservation() == 1 || pcSiteList.get(29).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','30')<%} %>"id="Layer30" style="position:absolute; left:435.433070866142px; top:208.816753926702px; width:44.8818897637795px; height:32.2513089005236px; z-index:7"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(29).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(29).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>30</font></td></tr></table></div>
			<div class="sit" onclick="<%if(pcSiteList.get(30).getIsReservation() == 1 || pcSiteList.get(30).getIsDeclare() == 1){ %>errorMsg() <%} else{ %>sit('1','31')<%} %>"id="Layer31" style="position:absolute; left:480.314960629921px; top:208.816753926702px; width:44.8818897637795px; height:32.2513089005236px; z-index:6"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(pcSiteList.get(30).getIsReservation() == 1){%>blue<%}else if(pcSiteList.get(30).getIsDeclare() == 1){%>red<%} else {%>green<%} %>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>31</font></td></tr></table></div>
			</div>
			<!-- ======================================= MAPTEMP 끝 =====================================-->
			</td>			
		</tr>	
	</table>	
			</td>
		</td>
	</table>	
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>