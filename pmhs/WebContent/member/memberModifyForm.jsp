<%@page import="pmhs.web.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>

    <title>Registration Form Template | PrepBootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" type="text/css" href="css/bootstrap.regist.min.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.regist.css" />
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css" />

    <script type="text/javascript" src="js/jquery-1.10.2.regist.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.regist.min.js"></script>
</head>
<body>
<%
Member loginUser = (Member)request.getAttribute("loginUser");
%>
<div class="container">

<div class="page-header">
    <h1>회원정보수정 <small> Modify </small></h1>
</div>





<script>
  function winOpen(){
	//자바스크립트는 화면을 제어 할수 있는 수많은 내장 객체들을 제공하고, 각 내장 객체들은 
	//속성과 메소드가 제공된다.
	window.open("idCheckPro.mem?id=" + document.forms[0].id.value,"window1","width=300,height=300");
  }
  function zipSearch() {
	  window.open("zipcodeSearch.mem","window2","width=300,height=400");
  }
  function winClose(){
	  window.close(); 
  }
</script>

		 <div class="container" id="container1">
        <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center">회원정보수정</h3>

                    </div>
                    <div class="panel-body">

                        <form action = "memberModifyPro.mem" method="POST" role="form">
                            <div class="form-group">
                               <input type = "hidden" name = "id" value = ${loginUser.m_id } />
                                <input type="text" name="id" id="id" class="form-control input-sm" placeholder="아이디" value = ${loginUser.m_id } readonly="readonly" />
                            </div>
                           <div class="form=group">
                           <input type = "button" value ="아이디 중복체크" onclick="winOpen()"/>
                            </div>
                            <br>
                                <div class="form-group">
                                        <input type="text" name="passwd" id="passwd" class="form-control input-sm" placeholder="비밀번호" value = ${loginUser.m_passwd } />
                                </div>
                                <div class="form-group">
                                        <input type="text" name="passwd" id="passwd" class="form-control input-sm" placeholder="비밀번호 확인" value = ${loginUser.m_passwd } />
                                </div>
                                    <div class="form-group">
                                    <input type = "hidden" name = "name" value = ${loginUser.m_name } />
                                         <input type="text" name="name" id="name" class="form-control input-sm" placeholder="이름" value = ${loginUser.m_name } readonly="readonly">
                                </div>
                                    <div class="form-group">
                                    <input type = "hidden" name = "studentNum" value = ${loginUser.m_studentNum } />
                                       <input type="text" name="studentNum" id="studentNum" class="form-control input-sm" placeholder="학번" value = ${loginUser.m_studentNum } readonly="readonly" />
                                </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                         <input type="text" name="zipcode1" id="zipcode1" readonly="readonly" class="form-control input-sm" value = ${loginUser.m_zipcode1 } >
                                    </div>
                                </div>
                                -
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                 <input type="text" name="zipcode2" id="zipcode2" readonly="readonly" class="form-control input-sm" value = ${loginUser.m_zipcode2 } >
                                </div>
                                </div>
                                <br>
                                
                                <div class="form-group">
                                <input type = "button" value = "우편번호 찾기" onclick = "zipSearch()"/>
                                </div>
                            <div class="form-group">
                                <input type="text" name="address1" id="address1" readonly="readonly" class="form-control input-sm" value = ${loginUser.m_address1 } >
                                <input type="text" name="address2" id="address2" class="form-control input-sm" value = ${loginUser.m_address2 }  >
                               
                            </div>

                             <div class="form-group">
                                <input type="date" name="birthDay" id="birthDay" class="form-control input-sm" placeholder="생년월일" value = ${loginUser.m_birthDay }>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" name="phone" id="phone" class="form-control input-sm" placeholder="전화번호" value = ${loginUser.m_phone }>
                            </div>
                             <div class="form-group">
                                <input type="email" name="email" id="email" class="form-control input-sm" placeholder="이메일" value = ${loginUser.m_email }>
                            </div>
                            <div class="form-group">
                             <SPAN STYLE="font-weight:bold; text-align:center;">  MAN </SPAN><input type = "radio" name = "gender" id = "gender" class="form-control input-sm" value="${loginUser.m_gender eq M }" checked />
				          <SPAN STYLE="font-weight:bold; text-align:center;">    WOMEN </SPAN> <input type = "radio" name = "gender" id = "gender" class="form-control input-sm" value="${loginUser.m_gender eq F }" checked />
                            </div>

                            

                            <input type="submit" value="회원정보수정" class="btn btn-info btn-block">
                            <input type="button" value="취소" class="btn btn-info btn-block" onClick="javaScript:winClose()">
                      </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<style>
#container1 {
    background-color: #e2dada;
}

.centered-form {
    margin-top: 120px;
    margin-bottom: 120px;
}

.centered-form .panel {
    background: rgba(255, 255, 255, 0.8);
    box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
}

</style>

        </div>
</body>
</html>