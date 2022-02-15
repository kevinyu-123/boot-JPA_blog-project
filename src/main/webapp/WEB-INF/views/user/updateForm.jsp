<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../layout/header.jsp"%>
	<div class="container">
		<form>
			<div class="form-group">
				<label for="username">User name:</label> 
					<input type="hidden" id="id" value="${principal.user.id}">
					<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
			</div>
			
			
			
			<c:choose>
				<c:when test="${empty principal.user.oauth }">
					<div class="form-group">
						<label for="email">Email address:</label> <input type="email"
						class="form-control" value="${principal.user.email}" placeholder="Enter email" id="email">
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
						class="form-control"  placeholder="Enter password" id="password">
					</div>	
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label for="email">Email address:</label> <input type="email"
						class="form-control" value="${principal.user.email}" placeholder="Enter email" readonly id="email">
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
						class="form-control"  placeholder="Enter password" readonly id="password">
					</div>	
				</c:otherwise>
			</c:choose>
			
			
		</form>
		<button id="btn-update" class="btn btn-primary">수정하기</button>
	</div>
	<br/>
	
	<script src="/js/user.js"></script>
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>