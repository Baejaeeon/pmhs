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
		width: 500px;
		height: 500px;
		border: 1px double orange;
	}
		#list2Area{
		margin: auto;
		width: 500px;
	}
	
	#pageArea {
		margin: auto;
		width: 500px;
		text-align: center;
	}
	
	h2, #td_command {
		text-align: center;
		border-bottom: 1px dotted red;
	}
	
	table {
		margin: auto;
		width: 480px;
	}
	
	#tr_title {
		background: orange;
	}
	.td_num {
		width: 40px;
	}
	.td_subject {
		width: 130px;
	}
	.td_writer {
		width: 100px;
	}
	.td_readcount {
		width: 100px;
	}
	.td_regdate {
		width: 100px;
	}
	
	.td_left {
		width: 200px;
	}
	
	.td_right {
		width: 280px;
	}
</style>
</head>
<body>
<form action="noticeBoardWriteForm.boa">	
	<section id = "listArea">
	<c:if test="${empty articleList }">
			<h2>등록된 글이 없습니다.</h2>
		</c:if>
	<c:if test="${not empty articleList }">	
		<h2> 공지 게시글 목록</h2>
			<table>
				<tr id = "tr_title">
					<td class = "td_num">
						번호
					</td>
					<td class = "td_subject">
						제목
					</td>
					<td class = "td_writer">
						작성자
					</td>
					<td class = "td_regdate">
						작성일
					</td>
					<td class = "td_readcount">
						조회수
					</td>
				</tr>
			<c:set var = "number" value="${pageInfo.number }"></c:set>
			<c:forEach var="article" items="${articleList }">
				<tr>
					<td class = "td_num">
						${number }
					</td>
					<c:set var = "number" value = "${number - 1 }"></c:set>
					<td class = "td_subject">
						<a href = "adminNoticeBoardContent.boa?num=${article.num }&pageNum=${pageInfo.currentPage}">${article.subject }</a>
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
				</c:forEach>
			</table>
		</c:if>
	</section>
	<section id = "list2Area">
		<input type = "submit" value = "글등록" />
	</section>
	<c:if test="${pageInfo.count > 0 }">
	<section id = "pageArea">
	<c:if test="${pageInfo.startPage > 20 }">
		<a href = "adminNoticeBoardList.boa?pageNum=${pageInfo.startPage-20 }">[이전]</a>
	</c:if>
	<c:forEach var = "i" begin="${pageInfo.startPage }" end="${pageInfp.endPage }">
		<a href = "adminNoticeBoardList.boa?pageNum=${i }">[${i }]</a>
		<!-- startPage부터 endPage까지 출력하기 -->
	</c:forEach>
	<c:if test="${pageInfoendPage < pageInfo.pageCount }">
		<a href = "adminNoticeBoardList.boa?pageNum=${pageInfo.startPage+20 }">[다음]</a>
	</c:if>
	</section>
	</c:if>
	</form>
</body>
</html>