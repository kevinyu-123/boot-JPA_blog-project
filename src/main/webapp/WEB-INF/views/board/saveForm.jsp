<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../layout/header.jsp" %>

	<div class="container">
		<form >
			<div class="form-group">

				<input type="text" name="title" class="form-control" placeholder="Enter title" id="title">
			</div>			
			<div class="form-group">

				<textarea class="form-control summernote" rows="3" cols="" id="content"></textarea>
			</div>
		</form>
		<button id="btn-save" class="btn btn-primary">저장하기</button>
	</div>
	
	 <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
    <script src="/js/board.js"></script>
	
	<%@ include file="../layout/footer.jsp" %>
	


