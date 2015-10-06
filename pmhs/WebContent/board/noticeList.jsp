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
		width: 80%;
		height: 500px;
		border: 1px;
		border-bottom: 1px dotted; 
	}

	img{
		margin: auto;
		float: right;
		width: 40px;
		height: 30px;
	}
	#command{
		margin: auto;
		width: 60%;
		
	}
	#pageArea {
		margin: auto;
		width: 80%;
		height: 100%;
		text-align: center;
		font-size: 15px;
		
	}
	
	 #td_command {
		text-align: center;
		border-bottom: 1px dotted ;
	}
	h2{
	text-align: left;
	border-bottom: 1px dotted ;
    
	}
	
	table {
		margin: auto;
		width: 80%;
	    font-family: "맑은고딕";
	    
	}
   .list{
    font-family: "맑은고딕";
    font-size: 15px;
    border-bottom-style: inset;
    border-bottom-color: white;
    
   }
	#tr_title {
		background: #424242;
		color: white;
		text-align: center;
		height: 20px;
	}
	.td_num {
		width: 55px;
		text-align: center;
	}
	.td_subject {
		width: 280px;
	}
	.td_writer {
		width: 70px;
		text-align: center;
	}
	.td_readcount {
		width: 55px;
		text-align: center;
	}
	.td_regdate {
		width: 40px;
		text-align: center;
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
<jsp:include page="../header.jsp"/>
<form action="noticeBoardWriteForm.bo">	
	<section id = "listArea">
	<c:if test="${empty articleList }">
			<h2>등록된 글이 없습니다.</h2>
		</c:if>
	<c:if test="${not empty articleList }">	
		<h2> 공지 사항</h2>
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
				<tr class="list">
								<td class = "td_num">
						${number }
					</td>
					<c:set var = "number" value = "${number - 1 }"></c:set>
					<td class = "td_subject">
						<a href = "noticeBoardContent.bo?num=${article.num }&pageNum=${pageInfo.currentPage}">${article.subject }</a>
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
	<c:if test="${pageInfo.count > 0 }">
	<section id = "pageArea">
	<c:if test="${pageInfo.startPage > 20  }"> <!-- [이전] 출력 -->
		<a href = "qnABoardList.bo?pageNum=${pageInfo.startPage - 20 }">[이전]</a>
		<!-- 이전 그룹의 첫 페이지를 가져온다. -->
	</c:if>
	<c:forEach var = "i" begin="${pageInfo.startPage }" end = "${pageInfo.endPage }">
		<!-- forEach문을 통해 startPage부터 endPage까지 출력하기 -->
		<a href = "qnABoardList.bo?pageNum=${i }">[${i }]</a>
	</c:forEach>
	<c:if test="${pageInfo.endPage < pageInfo.pageCount }"> <!-- 마지막 페이지가 아닐때, -->
		<!-- 마지막 그룹이 아니면 다음 그룹으로 넘어갈 수 있다. -->
		<a href = "qnABoardList.bo?pageNum=${pageInfo.startPage + 20 }">[다음]</a>
	</c:if>
	</section>
	</c:if>
	</form>
	<jsp:include page="../footer.jsp"/>	
</body>
</html>