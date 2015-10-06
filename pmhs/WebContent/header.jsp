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
	function messageOpen_click(){
		window.open("messageReceiveList.msg", "messageReceiveList", "width=480, height=440");		
	}
</script>
<body>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="main.do">PC Manage Helper</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="background-color:#3B3B3B;">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="main.do">Home</a>
                    </li>
                    <li>
                        <a href="introduceForm.jsp">시스템 소개</a>
                    </li>
                    <c:if test="${loginUser.m_id eq 'admin' }">
                    	<li>
                       		<a href = "adminNoticeBoardList.boa">공지관리</a>
                    	</li>
                    	<li>
                        	<a href = "adminQnABoardList.boa">문의관리</a>
                    	</li>
                    </c:if>
                    <c:if test="${loginUser.m_id ne 'admin' }">
                    <li>
                        <a href="noticeBoardList.bo">공지사항</a>
                    </li>
                    <li>
                        <a href="qnABoardList.bo">문의사항</a>
                    </li>
                    </c:if>
                    <li>
                        <a href="memberMain.mem">회원정보</a>
                    </li>
                    <li>
                    <c:if test="${loginUser.m_id eq 'admin' }">
                        <a href="errorPcList.pca">PC 관리</a>
                    </c:if>
                    </li>
                    <li>
                    <c:if test="${loginUser.m_id eq 'admin' }">
                    	<a href = "reservationList.pca">예약정보</a>
                    </c:if>
                    </li>
                    <li>
                    <c:if test="${loginUser.m_id eq 'admin' }">
                    	<a href = "memberAdminMain.mema">회원관리</a>
                    </c:if>
                    </li>
                    <li>
                        <a href = "javaScript:messageOpen_click()">메시지</a>
                    </li>
                    <li>
                    	<%
                        if(loginUser != null){
						%>
						   <a href = "logout.mem">로그아웃</a>
						<%
						   }
						   else{
						%>	   
						
						   <a href = "loginForm.mem">로그인</a>
						<%
						   }
						%>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('img/dg1.jpg'); " >
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        &nbsp;<br>
                        &nbsp;<br>
                       <div class="site-heading">
                        <h1>PC Manage Helper</h1>
                        <hr class="small">
                        <span class="subheading">CATHOLIC UNIVERSITY OF DAEGU </span>
                    </div>
                        &nbsp;<br>
                        &nbsp;<br>
                        <span class="subheading"></span>
                    </div>
                </div>
            </div>
        </div>
    </header>
</body>
</html>