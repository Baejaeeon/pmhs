<%@page import="pmhs.web.memberAdmin.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.mmain.min.css" rel="stylesheet" type="text/css">
<link href="font-awesome/css/font-awesome.mmain.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.10.2.mmain.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.mmain.js" type="text/javascript"></script>

</head>
<style>

.clearfix {
    clear:both;
}

.rowcolor {
    background-color:#CCCCCC;
}

.padall {
    padding:10px;
}
.checkbox {
  position: relative;
  display: block;
  margin-top: 20px;
  margin-bottom: 10px;
}
.checkbox label {
  min-height: 20px;
  padding-left: 20px;
  margin-bottom: 0;
  font-weight: normal;
  cursor: pointer;
}

.icon {
    font-size:90px;
    color:#424242;
}
.bigicon {
    font-size:25px;
    color:#197BB5;
}
</style>
<script>
 function checkAll(){
	 if(document.forms[0].delete1.length == undefined){
		 document.getElementById("delete1").checked = document.forms[0].allCheck.checked;
	 }
	 else{
		 for(i = 0; i < document.forms[0].delete1.length; i++){
			 document.forms[0].delete1[i].checked = document.forms[0].allCheck.checked;
		 }
	 } 
 }
</script>
</head>
<body>
<jsp:include page="../header.jsp"/>
	<%
		ArrayList<Member> memberList = (ArrayList<Member>) request.getAttribute("memberList");
		if (memberList != null && memberList.size() > 0) {
	%>
   
		<h2>회원목록</h2>
		<fieldset>
		<form action="memberRemove.mema" method ="post">
				<span STYLE ="font-size:20px">전체선택<input type = "checkbox" name = "allCheck" onclick = "checkAll()"/></span>
				
				<%
					for (int i = 0; i < memberList.size(); i++) {
				%>
	<div class="container">
    <div class="row">
    <div class="col-md-12">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
            <div class="panel panel-default">
                    <input type = "checkbox" name = "delete1" id = "delete1" value = "<%=memberList.get(i).getM_id()%>"/>
                <div class="row padall">
                    <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                    <span class ="fa fa-user icon"></span>
                        
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
                        <div class="clearfix">
                            <div class="pull-left">
                                <span class="fa fa-check-circle bigicon"><span STYLE ="font-size: 15px; font-weight:bold;" > 아이디 :</span> <span STYLE = "color:gray;; font-size:15px; font-weight:bold;"><%=memberList.get(i).getM_id()%></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
                        <div class="clearfix">
                            <div class="pull-left">
                                <span class="fa fa-user bigicon"><span STYLE ="font-size: 15px; font-weight:bold;" >이름 :</span> <span STYLE = "color:gray;; font-size:15px; font-weight:bold;"><%=memberList.get(i).getM_name()%></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
                        <div class="clearfix">
                            <div class="pull-left">
                                <span class="fa fa-pencil bigicon"><span STYLE ="font-size: 15px; font-weight:bold;" >학번 :</span> <span STYLE = "color:gray;; font-size:15px; font-weight:bold;"><%=memberList.get(i).getM_studentNum()%></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
                        <div class="clearfix">
                            <div class="pull-left">
                                <span class="fa fa-phone-square bigicon"><span STYLE ="font-size: 15px; font-weight:bold;" >전화번호 :</span> <span STYLE = "color:gray;; font-size:15px; font-weight:bold;"><%=memberList.get(i).getM_phone()%></span></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
				
				<%
					}
				%>
				  <input type = "submit"  value = "삭제"/>
				</fieldset>
				
				 <br>
		        </form>
				  <form action="searchList.mema" method="post">
				  <tr>
					<td class="td_left">
					<label id = "search"><span STYLE ="font-size: 30px"; class= "fa fa-search bigicon"></span> </label>
					</td>
					<td class="td_right">
					<select id = "searchOption" name = "searchOption">
						 <option>아이디</option>
						 <option>이름</option>
						 <option>학번</option>
				 	<input type = "search" name = "search" id ="search"/><input type = "submit" value ="검색" "  /></a>
					</select>
				</td>
				</tr>
				  </form>
				</tr>
			</table>
	</section>
	<%
		}
	%>
<jsp:include page="../footer.jsp"/> 
</body>
</html>