<%@page import="pmhs.web.pcInfo.vo.PCInfo"%>
<%@page import="pmhs.web.pcInfo.vo.ReservationInfo"%>
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
		
		var libSitList = document.getElementById("pcSitList");
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

	function sit(floor,libSitNum){
		//alert(floor+libSitNum);
		
		var url = "libSitApplForm.lib?floor=" + floor + "&libSitNum=" + libSitNum;
		location.href = url;
		//alert(url);
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
 	ArrayList<ReservationInfo> pcSitList = (ArrayList<ReservationInfo>)request.getAttribute("libSitList"); 
	PCInfo pcInfo = (PCInfo)request.getAttribute("pcInfo");
 %>

<form action="libApplForm.lib" method = "post">
	<span style="font-weight: bold; margin-left: 50px">Floor : </span>
	<select id="floor">	
		<option value="">Select Floor</option>
		<option value="1" >B 1</option>
		<option value="2">1 F</option>
		<option value="3">2 F</option>
		<option value="4">3 F</option>
		<option value="5">4 F</option>		
		<option value="6">5 F</option>
	</select>
	</form>
	<div>
	</div>		
	
	<div style="margin-left: 50px">
	<table width=750px border=0>
		<tr>
			<td align=center>
				<table BORDER="0">
					<tr>
						<td>
							<h1>실시간 좌석 정보</h1>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	
	<table border=2 width=750px height=745 cellspacing='0' cellpadding='0' bordercolor='skyblue' bbgcolor='808080'>
		<tr>
			<td valign=top>
	
	<table width='750px' border='0' cellspacing='0' cellpadding='0' bbgcolor='#000000'>
		<tr>
			<td width=100%>
				<table width=100% border=0>
					<tr>
						<td align="right" width=50%>
							<font style='color:black;font-size:9pt;font-weight:900;'> 신고중 색상 &nbsp; 정상 색상 &nbsp; 예약중 색상 </font>
							<table width=230 height=21 border='1' cellpadding='0' cellspacing='0'>
								<tr>
									<td width=70 bgcolor='red' align='center' valign='middle'>
										<font style='color:white;font-size:8pt;font-weight:900;'>신고중</font>
									</td>
									<td width=25></td>
									<td width=70 bgcolor='green' align='center' valign='middle'>
										<font style='color:white;font-size:8pt;font-weight:900;'>정상</font>
									</td>
									<td width=25></td>
									<td width=70 bgcolor='purple' align='center' valign='middle'>
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
			<div class="sit" onclick="sit('1','001')" id="Layer1" style="position:absolute; left:480.314960629921px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:1"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td id='a' bgcolor="<%if(pcSitList.get(13).getDeclareNum()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>001</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','002')" id="Layer2" style="position:absolute; left:480.314960629921px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:2"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(14).getId()==null){%>green<%}else{%>red<%}%>"align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>002</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','003')"id="Layer3" style="position:absolute; left:435.433070866142px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:3"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(15).getId()==null){%>green<%}else{%>red<%}%>"align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>003</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','004')"id="Layer4" style="position:absolute; left:435.433070866142px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:4"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(16).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>004</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','005')"id="Layer5" style="position:absolute; left:480.314960629921px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:5"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(17).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>005</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','006')"id="Layer6" style="position:absolute; left:480.314960629921px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:6"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(18).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>006</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','007')"id="Layer7" style="position:absolute; left:435.433070866142px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:7"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(19).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>007</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','008')"id="Layer8" style="position:absolute; left:435.433070866142px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:8"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(20).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>008</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','009')"id="Layer9" style="position:absolute; left:359.055118110236px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:9"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(21).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>009</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','010')"id="Layer10" style="position:absolute; left:359.055118110236px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:10"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(22).getId()==null){%>green<%}else{%>red<%}%>" align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>010</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','011')"id="Layer11" style="position:absolute; left:314.173228346457px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:11"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(0).getId()==null){%>green<%}else{%>red<%}%>"   align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>11</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','012')"id="Layer12" style="position:absolute; left:314.173228346457px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:12"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(1).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>12</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','013')"id="Layer13" style="position:absolute; left:359.055118110236px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:13"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(2).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>13</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','014')"id="Layer14" style="position:absolute; left:359.055118110236px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:14"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(3).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>14</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','015')"id="Layer15" style="position:absolute; left:314.173228346457px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:15"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(4).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>15</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','016')"id="Layer16" style="position:absolute; left:314.173228346457px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:16"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(5).getId()==null){%>green<%}else{%>red<%}%>"   align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>16</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','017')"id="Layer17" style="position:absolute; left:237.795275590551px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:17"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(6).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>17</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','018')"id="Layer18" style="position:absolute; left:237.795275590551px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:18"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(7).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>18</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','019')"id="Layer19" style="position:absolute; left:192.913385826772px; top:97.4869109947644px; width:44.8818897637795px; height:32.2513089005236px; z-index:19"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(8).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>19</font></td></tr></table></div>
			<div class="sit" onclick="sit('1','020')"id="Layer20" style="position:absolute; left:192.913385826772px; top:129.738219895288px; width:44.8818897637795px; height:32.2513089005236px; z-index:20"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor="<%if(libSitList.get(9).getId()==null){%>green<%}else{%>red<%}%>"  align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>20</font></td></tr></table></div>
			<div class="sit" id="Layer21" style="position:absolute; left:237.795275590551px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:21"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>21</font></td></tr></table></div>
			<div class="sit" id="Layer22" style="position:absolute; left:237.795275590551px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:22"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>22</font></td></tr></table></div>
			<div class="sit" id="Layer23" style="position:absolute; left:192.913385826772px; top:25.6544502617801px; width:44.8818897637795px; height:32.2513089005236px; z-index:23"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>23</font></td></tr></table></div>
			<div class="sit" id="Layer24" style="position:absolute; left:192.913385826772px; top:57.9057591623037px; width:44.8818897637795px; height:32.2513089005236px; z-index:24"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>24</font></td></tr></table></div>
			<div class="sit" id="Layer25" style="position:absolute; left:192.913385826772px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:25"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>25</font></td></tr></table></div>
			<div class="sit" id="Layer26" style="position:absolute; left:237.795275590551px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:26"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>26</font></td></tr></table></div>
			<div class="sit" id="Layer27" style="position:absolute; left:314.173228346457px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:27"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>27</font></td></tr></table></div>
			<div class="sit" id="Layer28" style="position:absolute; left:359.055118110236px; top:176.649214659686px; width:44.8818897637795px; height:30.7853403141361px; z-index:28"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>28</font></td></tr></table></div>
			<div class="sit" id="Layer29" style="position:absolute; left:117.322834645669px; top:315.916230366492px; width:44.8818897637795px; height:30.7853403141361px; z-index:29"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>29</font></td></tr></table></div>
			<div class="sit" id="Layer30" style="position:absolute; left:117.322834645669px; top:346.701570680628px; width:44.8818897637795px; height:30.7853403141361px; z-index:30"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>30</font></td></tr></table></div>
			<div class="sit" id="Layer31" style="position:absolute; left:117.322834645669px; top:384.816753926702px; width:44.8818897637795px; height:30.7853403141361px; z-index:31"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>31</font></td></tr></table></div>
			<div class="sit" id="Layer32" style="position:absolute; left:117.322834645669px; top:415.602094240838px; width:44.8818897637795px; height:30.7853403141361px; z-index:32"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>32</font></td></tr></table></div>
			<div class="sit" id="Layer33" style="position:absolute; left:117.322834645669px; top:453.717277486911px; width:44.8818897637795px; height:30.7853403141361px; z-index:33"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>33</font></td></tr></table></div>
			<div class="sit" id="Layer34" style="position:absolute; left:117.322834645669px; top:484.502617801047px; width:44.8818897637795px; height:30.7853403141361px; z-index:34"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>34</font></td></tr></table></div>
			<div class="sit" id="Layer35" style="position:absolute; left:192.913385826772px; top:528.48167539267px; width:44.8818897637795px; height:30.7853403141361px; z-index:35"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>35</font></td></tr></table></div>
			<div class="sit" id="Layer36" style="position:absolute; left:237.795275590551px; top:528.48167539267px; width:44.8818897637795px; height:30.7853403141361px; z-index:36"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>36</font></td></tr></table></div>
			<div class="sit" id="Layer37" style="position:absolute; left:192.913385826772px; top:569.528795811518px; width:44.8818897637795px; height:32.2513089005236px; z-index:37"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>37</font></td></tr></table></div>
			<div class="sit" id="Layer38" style="position:absolute; left:192.913385826772px; top:601.780104712042px; width:44.8818897637795px; height:32.2513089005236px; z-index:38"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>38</font></td></tr></table></div>
			<div class="sit" id="Layer39" style="position:absolute; left:237.795275590551px; top:569.528795811518px; width:44.8818897637795px; height:32.2513089005236px; z-index:39"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>39</font></td></tr></table></div>
			<div class="sit" id="Layer40" style="position:absolute; left:237.795275590551px; top:601.780104712042px; width:44.8818897637795px; height:32.2513089005236px; z-index:40"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>40</font></td></tr></table></div>
			<div class="sit" id="Layer41" style="position:absolute; left:314.173228346457px; top:569.528795811518px; width:44.8818897637795px; height:32.2513089005236px; z-index:41"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>41</font></td></tr></table></div>
			<div class="sit" id="Layer42" style="position:absolute; left:314.173228346457px; top:601.780104712042px; width:44.8818897637795px; height:32.2513089005236px; z-index:42"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>42</font></td></tr></table></div>
			<div class="sit" id="Layer43" style="position:absolute; left:359.055118110236px; top:569.528795811518px; width:44.8818897637795px; height:32.2513089005236px; z-index:43"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>43</font></td></tr></table></div>
			<div class="sit" id="Layer44" style="position:absolute; left:359.055118110236px; top:601.780104712042px; width:44.8818897637795px; height:32.2513089005236px; z-index:44"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>44</font></td></tr></table></div>
			<div class="sit" id="Layer45" style="position:absolute; left:420.472440944882px; top:374.55497382199px; width:47.244094488189px; height:32.2513089005236px; z-index:45"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>45</font></td></tr></table></div>
			<div class="sit" id="Layer46" style="position:absolute; left:467.716535433071px; top:374.55497382199px; width:47.244094488189px; height:32.2513089005236px; z-index:46"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>46</font></td></tr></table></div>
			<div class="sit" id="Layer47" style="position:absolute; left:467.716535433071px; top:342.303664921466px; width:47.244094488189px; height:32.2513089005236px; z-index:47"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>47</font></td></tr></table></div>
			<div class="sit" id="Layer48" style="position:absolute; left:420.472440944882px; top:342.303664921466px; width:47.244094488189px; height:32.2513089005236px; z-index:48"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>48</font></td></tr></table></div>
			<div class="sit" id="Layer49" style="position:absolute; left:544.094488188976px; top:375.287958115183px; width:47.244094488189px; height:32.2513089005236px; z-index:49"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>49</font></td></tr></table></div>
			<div class="sit" id="Layer50" style="position:absolute; left:591.338582677165px; top:375.287958115183px; width:47.244094488189px; height:32.2513089005236px; z-index:50"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>50</font></td></tr></table></div>
			<div class="sit" id="Layer51" style="position:absolute; left:590.551181102362px; top:343.03664921466px; width:48.0314960629921px; height:32.2513089005236px; z-index:51"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>51</font></td></tr></table></div>
			<div class="sit" id="Layer52" style="position:absolute; left:543.307086614173px; top:343.03664921466px; width:47.244094488189px; height:32.2513089005236px; z-index:52"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>52</font></td></tr></table></div>
			<div class="sit" id="Layer53" style="position:absolute; left:544.307086614173px; top:343.03664921466px; width:47.244094488189px; height:32.2513089005236px; z-index:52"><table width=96% height=96% border='1' cellpadding='0' cellspacing='0'><tr><td bgcolor='green' align='center' valign='middle'><font style='color:white;font-size:10pt;font-weight:900;'>53</font></td></tr></table></div>
			</div>
			<!-- ======================================= MAPTEMP 끝 =====================================-->
			</td>			
		</tr>	
	</table>	
			</td>
		</td>
	</table>	
	</div>
</body>
</html>