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
		<form action="/auth/loginProc" method="post">
			<div class="form-group">
				<label for="username">User name:</label>
				<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
			</div>
			
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			
	
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=3cc67d1cd705443fbd2c5ea3f22420fe&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="39px" src="/image/kakao.png"></a>
		</form>
	</div>
	<br/>
	
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>