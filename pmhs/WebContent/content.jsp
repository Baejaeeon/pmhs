<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</script>
<body>
<div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
              <form action="loginPro.mem" method="post">
                <div class="post-preview">
            		<div class="container">
              			 <div class="row colored">
                  		<div class="contcustom">
                     	<h2>Log-In</h2>
                     	<div>
	                        <input type="text" name="id" id="id" placeholder="Enter your ID">
	                        <input type="password" name="passwd" id="passwd" placeholder="Enter your Password">
	                        <input type="submit" class="btn" value="LOG-IN">
	                        <input type="button" class="btn" value="회원가입" onclick = "regist()">
                     </div>
                  </div>
               </div>
               </div>
                </div>
                </form>
                <hr>
                <div class="post-preview">
                        <h2 class="post-title">
                            	PC 선택 &nbsp;&nbsp;&nbsp; <font color="red" size="3px">단대, 학과 및 강의실을 선택해서 검색해주세요.</font>
                        </h2>
                    <h3 class="post-subtitle">
                        	단대 : 
                            <select id = "unit">
                            	<option>공과 대학</option>
                            	<option>교양관</option>
                            </select>
                            &nbsp;
                        	학과 : 
                            <select id = "department">
                            	<option>IT 공학부</option>
                            	<option>기초교양교육원</option>
                            </select>
                            <br/>
                            <br/>
                        	강의실 : 
                            <select id = "lectureRoom">
                            	<option>504</option>
                            	<option>517</option>
                            </select>호
                            <a href="pcSelectForm.pc" style="margin-left: 220px;"><input type="button" class="btn" value="검색" /></a>
                        </h3>
                </div>
                <hr>
                <div class="post-preview">
                    <a href="post.html">
                        <h2 class="post-title">
                           	 공지사항
                        </h2>
                        <h3 class="post-subtitle">
                            We predict too much for the next year and yet far too little for the next ten.
                        </h3>
                    </a>
                    <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on August 24, 2014</p>
                </div>
                <hr>
                <div class="post-preview">
                    <a href="post.html">
                        <h2 class="post-title">
                            	문의사항
                        </h2>
                        <h3 class="post-subtitle">
                            Many say exploration is part of our destiny, but it’s actually our duty to future generations.
                        </h3>
                    </a>
                    <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on July 8, 2014</p>
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