<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="layout/header.jsp" %>

	<c:forEach var="board" items="${boards.content}">
		<div class="container">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</div>
	</c:forEach>
	<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${boards.first}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>		
		</c:when>
		<c:otherwise>
		  	<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>	
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${boards.last}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:when>
		<c:otherwise>
		<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:otherwise>
	</c:choose>

	</ul>

	<br>
	
	<%@ include file="layout/footer.jsp" %>
	


