<%@page import="pmhs.web.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.mmain.min.css" rel="stylesheet" type="text/css">
<link href="font-awesome/css/font-awesome.mmain.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.10.2.mmain.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.mmain.js" type="text/javascript"></script>
<script>
function winMemberList(){
	window.open("memberList.mema")
}
</script>
</head>



<body>
<jsp:include page="../header.jsp"/>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
%>
<% if(loginUser != null){
	%>
<%
}
%>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
                <form action ="logout.mem" class="form-horizontal" method="post">
                    <fieldset>
                        <legend class="text-center header">회원 관리</legend>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                                 <span STYLE ="font-size: 20px; font-weight:bold; font-color: #4169E1;"> ${loginUser.m_name }</span> 님<br>
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
                            <div class="col-md-8">
                            <span STYLE ="font-size: 20px; font-weight:bold;">  ${loginUser.m_email } </span><br>
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                           <span STYLE ="font-size: 20px; font-weight:bold;">  ${loginUser.m_phone } </span><br>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <input type="button" class="btn btn-primary btn-lg" value = "회원 목록보기" onclick = "winMemberList() ">
                                <button type="submit" class="btn btn-primary btn-lg">로그 아웃</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<style>
    .header {
        color: #36A0FF;
        font-size: 27px;
        padding: 10px;
    }

    .bigicon {
        font-size: 35px;
        color: #36A0FF;
    }
</style>
<jsp:include page="../footer.jsp"/>         
</body>
</html>