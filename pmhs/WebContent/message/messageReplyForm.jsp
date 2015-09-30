<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답 장</title>
</head>
<body>
      <h2>답 장</h2>
      <form action="messageReplyPro.msg" method = "POST">
         <table>
            <tr>
               <td class = "td_left">
                  <label for = "title">제목</label>
               </td>
               <td class = "td_right">
                  <input type = "text" name = "title" id = "title" />
               </td>
            </tr>
            <tr>
               <td class = "td_left">
                  <label for = "receiver">받는사람</label>
               </td>
               <td class = "td_right">
                  <input type = "text" name = "receiver" id = "receiver" value="${writer }" readonly="readonly" />
               </td>
            </tr>
            <tr>
               <td class = "td_left">
                  <label for = "content">내용</label>
               </td>
               <td class = "td_right">
                  <textarea name = "content" id = "content" 
                  rows = "10" cols = "25"></textarea>
               </td>
            </tr>
            
            <tr>
               <td colspan = "2" id = "td_command"> <!-- colspan : 셀 합치기 -->
                  <input type = "submit" value = "전송" />
                  <a href="messageReceiveList.msg"><input type = "button" value = "취소" /></a>
                  <!-- location : 현재 url을 가지고 있다. 취소를 누르면 수신목록보기로 페이지를 이동한다. -->
               </td>
            </tr>
         </table>
      </form>
</body>
</html>