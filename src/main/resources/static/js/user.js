let index = {
	init:function(){
	$("#btn-save").on("click",()=>{ //function 을 사용하지 않은 이유는 this를 바인딩 하기 위함
		this.save();
		});

	},	
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}	
		$.ajax({
			type: "post",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터 타입
			dataType: "json",
		}).done(function(response){
			alert("회원가입 완료");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	
}

index.init();