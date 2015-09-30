<%@page import="pmhs.web.member.vo.Zipcode"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
	ArrayList<Zipcode> zipSearchList = (ArrayList<Zipcode>)request.getAttribute("zipSearchDataList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#dongInputArea {
   wirdth: 300px;
   margin: auto;
   border: 1px solid black;
}
</style>
<script>
function winClose(zipcode1,zipcode2,address) {
   opener.document.forms[0].zipcode1.value = zipcode1;
   opener.document.forms[0].zipcode2.value = zipcode2;
   opener.document.forms[0].address1.value = address;
    window.close();
}
</script>
</head>
<body>
   <%
  if(zipSearchList == null){
%>
   <section id="dongInputArea">
      <h4>동이름을 입력하세요</h4>
      <form action="zipcodeSearch.mem" method="post">
         <label for="dong">동이름 : </label> <input type="text" name="dong"
            id="dong" /><br> <input type="submit" value="검색" />
      </form>
   </section>
   <%
  }
  else{
%>
   <table>
      <tr>
         <td>시,도</td>
         <td>구,군</td>
         <td>동</td>
         <td>번지</td>
         <td>우편번호</td>
      </tr>
      <%
          for(int i =0; i < zipSearchList.size();i++){
             String zipcode[] = zipSearchList.get(i).getZipcode().split("-");
             String zipcode1 = zipcode[0];
             String zipcode2 = zipcode[1];
             
             String address = zipSearchList.get(i).getSido() + " "
                   + zipSearchList.get(i).getGugun() + " "
                   + zipSearchList.get(i).getDong() + " "
                   + zipSearchList.get(i).getBungi();
      %>
         <tr>
         <td><%=zipSearchList.get(i).getSido()%></td>
         <td><%=zipSearchList.get(i).getGugun()%></td>
         <td><%=zipSearchList.get(i).getDong()%></td>
         <td><%=zipSearchList.get(i).getBungi()%></td>
         <td><a href = "javaScript:winClose('<%=zipcode1%>','<%=zipcode2%>','<%=address%>')"><%=zipSearchList.get(i).getZipcode()%></a></td>
      </tr>
      <%
          }
      %>
   </table>
   <%
  }
%>
</body>
</html>