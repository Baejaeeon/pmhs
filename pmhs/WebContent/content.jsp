<%@page import="pmhs.web.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>PC Manage Helper</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/clean-blog.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<script>
	function regist(){
	   	window.open("memberRegistForm.mem");
	   }
	function winMemberDetail(){
		href.location="memberDetail.mem";
	}
</script>
<body>
<jsp:forward page="noticeContentList.bo"/>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <%
                if(loginUser == null){
                %>
                <div class="post-preview">
              <form action="loginPro.mem" name="forms" method="post">
            		<div class="container">
              			 <div class="row colored">
                  		<div class="contcustom">
                     	<h2>Log-In</h2>
                     	<div>
	                        <input type="text" name="id" id="id" placeholder="Enter your ID">
	                        <input type="password" name="passwd" id="passwd" placeholder="Enter your Password">
	                        <input type="submit" style="background-color: #424242; color: #ffffff;" class="btn" value="LOG-IN" onclick="javaScript:document.forms.submit()">
	                        <input type="button" style="background-color: #424242; color: #ffffff;" class="btn" value="회원가입" onclick = "regist()">
                     </div>
                  </div>
               </div>
               </div>
                </form>
                </div>
                <%
                	}
                	else{
                %>
                	<div class="container" style="height: 100%; width: 100%;">
					    <div class="row">
					        <div class="col-md-12">
					            <div class="well well-sm">
					                <form action ="logout.mem" name="logout" class="form-horizontal" method="post">
					                    <fieldset>
					                        <div class="form-group">
					                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
					                            <div class="col-md-8">
					                                 <span STYLE ="font-size: 20px; font-weight:bold; font-color: #4169E1;"> ${loginUser.m_name }</span> 님 로그인하셨습니다.<br>
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
					                                <a href="memberDetail.mem"><input type="button" style="background-color: #424242;" class="btn btn-primary btn-lg" value = "회원 정보상세보기"></a>
					                                <input type="submit" style="background-color: #424242;" value="로그아웃" class="btn btn-primary btn-lg" onclick="document.logout.submit()">
					                            </div>
					                        </div>
					                    </fieldset>
					                </form>
					            </div>
					        </div>
					    </div>
					</div>
					<%
                	}
					%>
                <hr>
                <form action="pcSelectForm.pc" name="forms2" method="post">
                <div class="post-preview">
                        <h2 class="post-title">
                            	PC 선택 &nbsp;&nbsp;&nbsp; <font color="red" size="3px">단대, 학과 및 강의실을 선택해서 검색해주세요.</font>
                        </h2>
                    <h3 class="post-subtitle">
                        	단대 : 
                            <select id = "unit" name="unit">
                            	<option value="공과 대학">공과 대학</option>
                            </select>
                            &nbsp;
                        	학과 : 
                            <select id = "department" name="department">
                            	<option value="IT 공학부">IT 공학부</option>
                            </select>
                            <br/>
                            <br/>
                        	강의실 : 
                            <select id = "lectureRoom" name="lectureRoom">
                            	<option value="504">504</option>
                            </select>호
                            <!-- <a href="pcSelectForm.pc" style="margin-left: 220px;"><input type="button" class="btn" value="검색" /></a> -->
                        	<input type="submit" style="margin-left: 140px; background-color: #424242; color: #ffffff;" class="btn" value="검색" onclick = "javaScript:document.forms2.submit(unit, department, lectureRoom)"/>
                        </h3>
                </div>
                </form>
                <hr>
                <div class="post-preview">
                    <a href="noticeBoardList.bo">
                        <h2 class="post-title">
                           	 공지사항
                        </h2></a>
                        <div>
                        
                        </div>
                </div>
                <hr>
                <div class="post-preview">
                    <a href="qnABoardList.bo">
                        <h2 class="post-title">
                            	문의사항
                        </h2></a>
                        <div>
                        
                        </div>
                </div>
                <hr>
                <!-- Pager -->
                <ul class="pager">
                    <li class="next">
                        <a href="#">Top &rarr;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>