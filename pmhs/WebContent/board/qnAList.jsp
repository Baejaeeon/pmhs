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
	<%!
		
		// 한 페이지 당 출력될 글의 개수 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 지정
	%>
	
<form action="qnABoardWriteForm.bo">	
	<section id = "listArea">
	<c:if test="${empty articleList }">
			<h2>등록된 글이 없습니다.</h2>
		</c:if>
	<c:if test="${not empty articleList }">	
		<h2> 문의 게시글 목록</h2>
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
					<c:set var="number" value="${number -1 }"></c:set>
					</td>
					<td class = "td_subject">
						<c:forEach begin="1" items="${article.re_level }">
							&nbsp;&nbsp;&nbsp;
							</c:forEach>
						<c:if test="${article.re_level > 0 }">
						re : 
						</c:if>
						<a href = "boardContent.bo?num=${article.num }&pageNum=${pageInfo.currentPage }">${article.subject }</a>
						<!-- 게시물 상세보기 요청 링크를 걸어준다. 해당 글을 구분할 수 있는 값인 num값을 파라미터로 던져준다. -->
					</td>
					<td class = "td_writer">
						${article.writer }
					</td>
					<td class = "td_regdate">
						${article.regDate }
					</td>
					<td class = "td_readcount">
						${article.readcount}
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
		<a href = "boardList.bo?pageNum=${pageInfo.startPage-20 }">[이전]</a>
	</c:if>
	<c:forEach var = "i" begin="${pageInfo.startPage.startPage }" end="${pageInfp.endPage }">
		<a href = "boardList.bo?pageNum=${i }">[${i }]</a>
		<!-- startPage부터 endPage까지 출력하기 -->
	</c:forEach>
	<c:if test="${pageInfoendPage < pageInfo.pageCount }">
		<a href = "boardList.bo?pageNum=${pageInfo.startPage+20 }">[다음]</a>
	</c:if>
	</section>
	</c:if>
	</form>
</body>
</html>