<%@page import="pmhs.web.board.vo.PageInfo"%>
<%@page import="pmhs.web.boardAdmin.dao.BoardAdminDAO"%>
<%@page import="pmhs.web.boardAdmin.vo.QnABoardVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#listArea {
		margin: auto;
		width: 600px;
		height: 500px;
		border: 1px double orange;
	}
	
	#list2Area{
		margin: auto;
		text-align: right;
		width: 600px;
	}
	
	#pageArea {
		margin: auto;
		width: 600px;
		text-align: center;
	}
	
	h2, #td_command {
		text-align: center;
		border-bottom: 1px dotted red;
	}
	
	table {
		margin: auto;
		width: 580px;
	}
	
	#tr_title {
		background: orange;
	}
	.td_num {
		width: 80px;
		text-align: center;
	}
	.td_subject {
		width: 150px;
	}
	.td_name {
		width: 80px;
		text-align: center;
	}
	.td_time {
		width: 80px;
		text-align: center;
	}
	.td_declaredate {
		width: 100px;
		text-align: center;
	}
	.td_error{
		width: 80px;
		text-align: center;
	}
	.td_left {
		width: 200px;
	}
	
	.td_right {
		width: 280px;
	}
	#tr_command {
		text-align: right;
	}
</style>
<script>
	function checkAll() {
		// 단일객체 일때와 배열 객체일 경우를 구분해서 처리
		if(document.forms[0].delete1.length == undefined) { // 단일 객체일 경우..
			document.getElementById("delete1").checked = document.forms[0].allCheck.checked;
			// checked : 체크가 됬는지 안됬는지 여부를 나타냄
		}
		else { // 배열일 경우
			for(i = 0; i < document.forms[0].delete1.length; i++) {
				document.forms[0].delete1[i].checked = document.forms[0].allCheck.checked;
			}
		}
	}
</script>
</head>
<body>
<form action="adminQnABoardDeletePro.boa">
	<%!
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 포맷 지정
	%>
	<section id = "listArea">
		<c:if test="${empty articleList }"> <!-- 예약이 없으면... -->
		</c:if>
		<c:if test="${not empty articleList }"> <!-- 예약이 있으면... -->
		<h2>문의게시물 목록</h2>
			<table>
				<tr id = "tr_title">
					<td><input type="checkbox" name = "allCheck" onclick="checkAll()" /></td>
					<td class = "td_num">
						번호
					</td>
					<td class = "td_subject">
						제목
					</td>
					<td class = "td_writer">
						작성자
					</td>
					<td class = "td_reg_date">
						작성일
					</td>
					<td class = "td_readCount">
						조회수
					</td>
				</tr>
				<!-- pageInfo에 공유되어 있는 값을 가져와서 number변수에 저장 -->
				<c:set var = "number" value = "${pageInfo.number }"></c:set>
				<c:forEach var = "article" items = "${articleList }"> <!-- for문 수행 -->
				<tr>
					<td><input type="checkbox" name = "delete1" id = "delete1" value = "${article.num }" /></td>
					<tr>
					<td class = "td_num">
						${number }
					</td>
					<c:set var = "number" value = "${number - 1 }"></c:set>
					<td class = "td_subject">
						<a href = "adminQnABoardContent.boa?num=${article.num }&pageNum=${pageInfo.currentPage}">${article.subject }</a>
					</td>
					<td class = "td_writer">
						${article.writer }
					</td>
						<fmt:formatDate value="${article.reg_date }" var = "regDate" pattern="yyyy.MM.dd" />
					<td class = "td_regdate">
						${regDate }
					</td>
					<td class = "td_readcount">
						${article.readCount }
					</td>
				</tr>
					</td>
				</tr>
				</c:forEach>
			</table>
			<tr>
		</tr>
		</c:if>
	</section>
	<section id = "list2Area">
		<td colspan="7" id = "tr_command" >
				<input type = "submit" value = "삭제" />
		</td>
	</section>
	<c:if test="${pageInfo.count > 0 }">
	<section id = "pageArea">
	<c:if test="${pageInfo.startPage > 10  }"> <!-- [이전] 출력 -->
		<a href = "adminQnABoardList.boa?pageNum=${pageInfo.startPage - 20 }">[이전]</a>
		<!-- 이전 그룹의 첫 페이지를 가져온다. -->
	</c:if>
	<c:forEach var = "i" begin="${pageInfo.startPage }" end = "${pageInfo.endPage }">
		<!-- forEach문을 통해 startPage부터 endPage까지 출력하기 -->
		<a href = "adminQnABoardList.boa?pageNum=${i }">[${i }]</a>
	</c:forEach>
	<c:if test="${pageInfo.endPage < pageInfo.pageCount }"> <!-- 마지막 페이지가 아닐때, -->
		<!-- 마지막 그룹이 아니면 다음 그룹으로 넘어갈 수 있다. -->
		<a href = "adminQnABoardList.boa?pageNum=${pageInfo.startPage + 20 }">[다음]</a>
	</c:if>
	</section>
	</c:if>
	</form>
</body>
</html>