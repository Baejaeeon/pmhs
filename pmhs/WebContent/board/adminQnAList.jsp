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
		width: 80%;
		height: 500px;
		border: 1px;
		border-bottom: 1px dotted; 
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
	
	}
	.td_checkbox{
	    width: 55px;
	    text-align: center;
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
<jsp:include page="../header.jsp"/>
<form action="adminQnABoardDeletePro.boa" name="form3">
	<%!
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 포맷 지정
	%>
	<section id = "listArea">
		<c:if test="${empty articleList }"> <!-- 예약이 없으면... -->
		</c:if>
		<c:if test="${not empty articleList }"> <!-- 예약이 있으면... -->
		<h2> <img src="img/list.PNG">  문의 사항</h2>
			<table>
				<tr id = "tr_title">
					<td><input type="checkbox" class="td_checkbox" name = "allCheck" onclick="checkAll()" /></td>
					<td class = "td_num">
						번호
					</td>
					<td class = "td_subject" style="text-align: center;">
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
				<tr class="list">
					<td class="td_checkbox"><input type="checkbox" name = "delete1" id = "delete1" value = "${article.num }" /></td>
					<td class = "td_num">
						${number }
					</td>
					<c:set var = "number" value = "${number - 1 }"></c:set>
					<td class = "td_subject">
					<c:forEach begin="1" end = "${article.re_level }"> <!-- 답글 레벨을 하나씩 가져와서  -->
						&nbsp;&nbsp;&nbsp;
						</c:forEach>
						<c:if test="${article.re_level > 0 }"> <!-- 답글 레벨이 0보다 크면 re를 출력해준다. -->
						<img src="img/re.gif" style="height: 18px; width: 30px; float: left; margin-left: 10px;">
						<!-- 게시물 상세보기 요청 링크를 걸어준다. 해당 글을 구분할 수 있는 값인 num값을 파라미터로 던져준다. --> 
						</c:if>
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
				</c:forEach>
			</table>
			<tr>
		</tr>
		</c:if>
	</section>
	<section id = "command">

	 <a href="adminQnABoardList.boa"><input type="image" src="img/boardDelete.jpg" onclick="document.form3.submit()" style="margin-left:80%; margin-top: 5px; "/></a>

	</section>
	<c:if test="${pageInfo.count > 0 }">
	<section id = "pageArea">
	<c:if test="${pageInfo.startPage > 20  }"> <!-- [이전] 출력 -->
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
<jsp:include page="../footer.jsp"/>
</body>
</html>