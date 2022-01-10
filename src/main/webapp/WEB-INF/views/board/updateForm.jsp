<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../layout/header.jsp" %>

	<div class="container">
		<form >
			<input type="hidden" id="id" value="${board.id}">
			<div class="form-group">
				<input value="${board.title }" type="text" name="title" class="form-control" id="title">
			</div>			
			<div class="form-group">
			
				<textarea class="form-control summernote" rows="3" cols="" id="content">${board.content}</textarea>
			</div>
		</form>
		<button id="btn-update" class="btn btn-primary">수정하기</button>
	</div>
	
	 <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
    <script src="/js/board.js"></script>
	
	<%@ include file="../layout/footer.jsp" %>
	


