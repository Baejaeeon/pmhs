<%@page import="pmhs.web.board.vo.PageInfo"%>
<%@page import="pmhs.web.pcAdmin.dao.PCAdminDAO"%>
<%@page import="pmhs.web.pcAdmin.vo.ReservationInfo"%>
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
</style>
</head>
<body>
	<%!
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 포맷 지정
	%>
	<section id = "listArea">
		<c:if test="${empty reservationList }"> <!-- 예약이 없으면... -->
			<h2>등록된 예약이 없습니다.</h2>
		</c:if>
		<c:if test="${not empty reservationList }"> <!-- 예약이 있으면... -->
		<h2>예약 목록</h2>
			<table>
				<tr id = "tr_title">
					<td class = "td_num">
						신고번호
					</td>
					<td class = "td_subject">
						제목
					</td>
					<td class = "td_name">
						신고자
					</td>
					<td class = "td_declaredate">
						신고날짜
					</td>
					<td class = "td_time">
						방문시간
					</td>
					<td class = "td_error">
						에러증상
					</td>
				</tr>
				<!-- pageInfo에 공유되어 있는 값을 가져와서 number변수에 저장 -->
				<c:set var = "number" value = "${pageInfo.number }"></c:set>
				<c:forEach var = "reservation" items = "${reservationList }"> <!-- for문 수행 -->
				<tr>
					<td class = "td_num">
						${number }
					</td>
					<c:set var = "number" value = "${number - 1 }"></c:set>
					<td class = "td_subject">
						${reservation.subject }
					</td>
					<td class = "td_name">
						${reservation.name }
					</td>
						<fmt:formatDate value="${reservation.declareDate }" var = "declareDate" pattern="yyyy.MM.dd" />
					<td class = "td_declaredate">
						${declareDate }
					</td>
					<td class = "td_time">
						${reservation.time }
					</td>
					<td class = "td_error">
						${reservation.errorSymptom }
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</section>
	<c:if test="${pageInfo.count > 0 }">
	<section id = "pageArea">
	<c:if test="${pageInfo.startPage > 10  }"> <!-- [이전] 출력 -->
		<a href = "reservationList.pca?pageNum=${pageInfo.startPage - 10 }">[이전]</a>
		<!-- 이전 그룹의 첫 페이지를 가져온다. -->
	</c:if>
	<c:forEach var = "i" begin="${pageInfo.startPage }" end = "${pageInfo.endPage }">
		<!-- forEach문을 통해 startPage부터 endPage까지 출력하기 -->
		<a href = "reservationList.pca?pageNum=${i }">[${i }]</a>
	</c:forEach>
	<c:if test="${pageInfo.endPage < pageInfo.pageCount }"> <!-- 마지막 페이지가 아닐때, -->
		<!-- 마지막 그룹이 아니면 다음 그룹으로 넘어갈 수 있다. -->
		<a href = "reservationList.pca?pageNum=${pageInfo.startPage + 10 }">[다음]</a>
	</c:if>
	</section>
	</c:if>
</body>
</html>