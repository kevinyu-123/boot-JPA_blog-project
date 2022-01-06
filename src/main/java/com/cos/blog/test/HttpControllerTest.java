package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//사용자가 요청 -> 응답(html 파일)
//@Controller

//사용자가 요청 -> 응답(data)
@RestController
public class HttpControllerTest {
	
	@GetMapping("/lombok")
	public String lombokTest() {
		Member m = Member.builder().id(2).email("tntj123@naver.com").password("12345").build();
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get") //?id=1&username=ssar&password=1234&email=ssar@naver.com
	public  String getTest(Member m) { 
		return "get 요청:"+m.getId()+" "+m.getUsername()+" "+m.getPassword()+" "+m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public  String postTest(@RequestBody Member m) { //파싱을 messageconverter가 해주게 됌.
		return "post 요청:"+m.getId()+" "+m.getUsername()+" "+m.getPassword()+" "+m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public  String putTest() {
		return "put  요청";
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public  String deleteTest() {
		return "delete 요청";
	}

	
}
