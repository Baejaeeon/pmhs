<%@page import="pmhs.web.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 #contentArea{
    width : 600px;
    height : 600px;
    margin : auto;
    border : 1px solid brown;
    }
    h2,#commandArea{
       text-align: center;
    }
    #content_basic{
    height : 40px;
    border-bottom : 1px double orange;
    }
</style>
</head>
<body>
<%
Member loginUser = (Member)request.getAttribute("loginUser");
%>
<section id = "contentArea">
   <h2>회원상세정보</h2>
   <section id = "content_basic">
      아이디 : ${loginUser.m_id} |<br>
      이름 : ${loginUser.m_name} |<br>
      학번 : ${loginUser.m_studentNum} |<br>
      우편번호 : ${loginUser.m_zipcode1} - ${loginUser.m_zipcode2 } |<br>
      생년월일  : ${loginUser.m_birthDay} |<br>
      주소 : ${loginUser.m_address1 }  ${loginUser.m_address2 }<br>
      이메일 : ${loginUser.m_email }<br>
      전화번호 : ${loginUser.m_phone }<br>
      성별 : ${loginUser.m_gender }<br>
   </section>
   
    
  
   <section id = "commandArea">
       <a href = "memberModifyForm.mem?id=${loginUser.m_id}">회원정보수정</a>
       <a href = "index.jsp">메인화면으로</a>
   
   </section>
</section>
</body>
</html>