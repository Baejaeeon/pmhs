<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
<style>
.row {
	padding: 20px 0px;
}


.contcustom {
	text-align: center;
	width: 500px;
	border-radius: 0.5rem;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: 10px auto;
	background-color: white;
	padding: 20px;
}

input {
	width: 80%;
	margin-bottom: 17px;
	padding: 15px;
	background-color: #ECF4F4;
	border-radius: 2px;
	border: none;
}


h2 {
	margin-bottom: 20px;
	font-size: 39px;
	font-weight: bold;
	color: #ABABAB;
}

.btn {
    background-color: #48D1CC;
	border-radius: 2px;
	font-weight: bold;
	padding: 10px;
	color: white;
}

.med {
	font-size: 27px;
	color: white;
}

.wide {
	background-color: #8EB7E4;
	width: 100%;
	-webkit-border-top-right-radius: 0;
	-webkit-border-bottom-right-radius: 0;
	-moz-border-radius-topright: 0;
	-moz-border-radius-bottomright: 0;
	border-top-right-radius: 0;
	border-bottom-right-radius: 0;
}
table{
    border-bottom-style: inset;
    border-bottom-color: white;
}
.td_left{
    width: 550px;
    font-family: "맑은고딕";
    font-size: 13px;
    font-weight: bold;
   
}
.td_right{
     width:300px;
    float: right;
    font-family: "맑은고딕";
    font-size: 13px; 
}

#container1 {
    background-color: #e2dada;
}

.centered-forms {
    margin-top: 120px;
    margin-bottom: 120px;
}

.centered-forms .panel {
    background: rgba(255, 255, 255, 0.8);
    box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
}

</style>
<body>

<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
                <%
                if(loginUser == null){
                %>
           <section>
			<form action="loginPro.mem" name="forms" method="post">
       <div class="panel-body">
				<div class="container">
					<div class="row colored">
						<div class="contcustom">
						<span><img src="img/logo1.jpg" style="height: 70px; width: 70px;" /></span>
							<h2>Log-In</h2>
							<div>
								<input type="text" name="id" id="id" placeholder="Enter your ID">
								<input type="password" name="passwd" id="passwd"
									placeholder="Enter your Password"> <input type="submit"
									class="btn" value="LOG-IN" style =  "margin-bottom: 5px; background-color: #424242;" onclick="javaScript:document.forms.submit()">
									<input type="button"
									class="btn" style =  "margin-bottom: 5px; background-color: #424242;" value="회원가입" onclick = "regist()">
							</div>
						</div>
					</div>
			</form>
	
	</section>
               
                <%
                	}
                	else{
                %>
                	<fieldset>
					        <div class="col-md-4" style="width: 42%; margin-left: 130px;">
					            <div class="well well-sm">
					                <form action ="logout.mem" name="logout" class="form-horizontal" method="post">
					                    <fieldset>
					                        <div class="form-group">
					                            <span class="col-md-1 col-md-offset-2 text-center" ><i class="fa fa-user bigicon"></i></span>
					                            <div class="col-md-8">
					                                 <span STYLE ="font-size: 20px; font-weight:bold; font-color: #4169E1; margin-top: 210px;"> ${loginUser.m_name }</span> 님 로그인하셨습니다.<br>
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
					                                <a href="memberDetail.mem"><input type="button"  class="btn" value = "회원 정보상세보기" style =  "margin-bottom: 5px; background-color: #424242;"></a>
					                                <input type="submit"  value="로그아웃" class="btn" onclick="document.logout.submit()" style="background-color: #424242;">
					                            </div>
					                        </div>
					                    </fieldset>
					                </form>
					            </div>
					    </div>
			<div class="col-md-4" style="width: 42%; height: 50%;">
			<div class="well well-sm" >
                <form action="pcSelectForm.pc" name="forms2" method="post">
                <div class="post-preview">
                        <h2 class="post-title">
                            	<img src="img/pclist.PNG">PC 선택 &nbsp;&nbsp;&nbsp; <font color="red" size="3px">단대, 학과 및 강의실을 선택해서 검색해주세요.</font>
                        </h2>
                    <h3 class="post-subtitle" style="margin-left: 55px; margin-top: 50px;font-size: 20px;" >
                        	단대 : 
                            <select id = "unit" name="unit" style="font-size: 18px;">
                            	<option value="공과 대학">공과 대학</option>
                            </select>
                            &nbsp;
                            &nbsp;
                        	학과 : 
                            <select id = "department" name="department" style="font-size: 18px;">
                            	<option value="IT 공학부">IT 공학부</option>
                            </select>
                            <br>
                            <br>
                            
                        	강의실 : 
                            <select id = "lectureRoom" name="lectureRoom" style="font-size: 18px;">
                            	<option value="504">504</option>
                            </select>호
                            <!-- <a href="pcSelectForm.pc" style="margin-left: 220px;"><input type="button" class="btn" value="검색" /></a> -->
                        	<input type="submit"  class="btn" value="검색" onclick = "javaScript:document.forms2.submit(unit, department, lectureRoom)" style="margin-top: 10px; background-color: #424242; width:150px; margin-left:50px; margin-bottom: 30px;"/>
                        </h3>
                </div>
               </form>
               
                </div>
                </div>
               </div>
               
                
			<div class="col-md-4" style="width: 42%; margin-left: 130px;">
			<!-- <div class="well well-sm" > -->
                <hr>
                <div class="post-preview">
                    <a href="noticeBoardList.bo">
                        <h2 class="post-title">
                        <img src="img/list.PNG">   	 공지사항
                        </h2></a>
                        
                        <c:forEach var = "noticeArticle" items = "${noticeArticleList }">
                        <table >
                        <tr>
                        <td class = "td_left">	
                        <img src="img/nth_theme_smart_smart02_new.gif" />  
							    	&nbsp; <a href="noticeBoardContent.bo?num=${noticeArticle.num }&pageNum=${pageNum }">${noticeArticle.subject }</a></td>
						<td class = "td_right">	    	
							    	 &nbsp; ${noticeArticle.reg_date }</td></tr><br>
						
                        </table>
                        </c:forEach>
                </div>
                <!-- </div> -->
                </div>
 
			<div class="col-md-4" style="width: 42%;">
			<!-- <div class="well well-sm" > -->
                 <hr>
                <div class="post-preview">
                    <a href="qnABoardList.bo">
                        <h2 class="post-title">
                        <img src="img/list.PNG">   	 문의사항
                        </h2></a>
                     		<c:forEach var = "qnaArticle" items = "${qnaArticleList }">
                        <table>
                        <tr>
                       <td class = "td_left">	
							     <img src="img/nth_theme_smart_smart02_new.gif" />  
							    	&nbsp; <a href="qnABoardContent.bo?num=${qnaArticle.num }&pageNum=${pageNum }">${qnaArticle.subject }</a></td>
						<td class = "td_right">
							    	 &nbsp; ${qnaArticle.reg_date }</td></tr><br>
							     </table>
						</c:forEach>
                       
                </div>
                <hr>
                         <ul class="pager">
                    <li class="next">
                        <a href="#">Top &rarr;</a>
                    </li>
                </ul>
                <!-- </div> -->
                </div>
                </div> 
                </div>
                
                </fieldset>
            	<%
                	}
					%>
            <!-- /.col-md-4 -->
          
        </div>
<%-- <div class="container">
        <div class="row" >
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
                <%
                if(loginUser == null){
                %>
                <div class="post-preview" >
              <form action="loginPro.mem" name="forms" method="post">
            		<div class="container" >
              			 <div class="row colored" >
                  		<div class="contcustom" >
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
                     
                        <c:forEach var = "noticeArticle" items = "${noticeArticleList }">
                        <table>
                        <td class = "td_left">	
                        <img src="img/nth_theme_smart_smart02_new.gif" />  
							    	&nbsp; <a href="noticeBoardContent.bo?num=${noticeArticle.num }&pageNum=${pageNum }">${noticeArticle.subject }</a></td>
						<td class = "td_right">	    	
							    	 &nbsp; ${noticeArticle.reg_date }</td><br>
						
                        </table>
                        </c:forEach>
                </div>
                <hr>
                <div class="post-preview">
                    <a href="qnABoardList.bo">
                        <h2 class="post-title">
                            	문의사항
                        </h2></a>
                        <div>
							        <%             
							              try {
							                int q_num;
							                String q_subject;
							                Timestamp q_reg_date;
							                
							                Class.forName("oracle.jdbc.driver.OracleDriver");
							                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","java", "java");
							                Statement stmt = con.createStatement();
							                
							                ResultSet rs = stmt.executeQuery("select q_num, q_subject, q_reg_date from (select * from QNABOARD order by q_reg_date DESC) WHERE rownum <=4");
							                while(rs.next()){
								                q_num=rs.getInt(1);
								                q_subject=rs.getString(2);
								                q_reg_date=rs.getTimestamp(3);
							    	%>   
										<tr class="list">		
							             <p><a href=""><%=q_subject %></a> | <%=q_reg_date %></p>
										</tr>
							     <%     
							                }
							          }catch(Exception e){
							          out.println(e);
							          }
							     %>
							    
					<c:forEach var = "qnaArticle" items = "${qnaArticleList }">
                        <table>
                       <td class = "td_left">	
							     <img src="img/nth_theme_smart_smart02_new.gif" />  
							    	&nbsp; <a href="qnABoardContent.bo?num=${qnaArticle.num }&pageNum=${pageNum }">${qnaArticle.subject }</a></td>
						<td class = "td_right">
							    	 &nbsp; ${qnaArticle.reg_date }</td><br>
							     </table>
						</c:forEach>
                       
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
    </div> --%>
  </fieldset>
</body>
</html>