let index = {
	init:function(){
	$("#btn-save").on("click",()=>{ 
		this.save();
		});
	$("#btn-delete").on("click",()=>{ 
		this.deleteById();
		});
	$("#btn-update").on("click",()=>{ 
		this.update();
		});
	$("#btn-reply-save").on("click",()=>{ 
		this.replySave();
		});

	},	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
	
		}	
		$.ajax({
			type: "post",
			url: "/api/board",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터 타입
			dataType: "json",
		}).done(function(response){
			alert("글이 성공적으로 저장되었습니다");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	deleteById: function(){
		let id =$("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json",
		}).done(function(response){
			alert("글이 삭제되었습니다");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
		update: function(){
		let id = $("#id").val();
			
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		
		}	
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터 타입
			dataType: "json",
		}).done(function(response){
			alert("글이 성공적으로 수정되었습니다");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	replySave: function(){
		let data = {
			content: $("#reply-content").val()	
		}	
		let boardId = $("#boardId").val();
		$.ajax({
			type: "post",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터 타입
			dataType: "json",
		}).done(function(response){
			alert("댓글이 성공적으로 저장되었습니다");
			location.href=`/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
}

index.init();