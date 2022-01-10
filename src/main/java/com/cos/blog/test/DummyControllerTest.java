package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입 (DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다.";
		}
		
		return "삭제되었습니다.id :"+id;
	}
	
	
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해줌
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	@Transactional //함수 종료시 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id :"+id);
		System.out.println("pwd :"+requestUser.getPassword());
		System.out.println("email :"+requestUser.getEmail());
		
		//DB에서 얻어온 값
		//영속화 
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		//더티 체킹 
		return user;
	}
	
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴 받아본다.
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users= pagingUsers.getContent();
		return pagingUsers;
	}
	
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 없습니다. id:"+id);
			}
		});
		//user 객체 = java object
		/*
		 * 요청 : 웹브라우저
		 * restcontroller 어노테이션은 html 파일이 아닌 데이터를 return 하기 때문에 변환이 필요하다.
		 * 그러기 위해선 json 을 통해야 하는데
		 * 스프링 부트는 messageConverter 를 통하여 응답시 자동으로 작동이된다.
		 *  자바 오브젝트를 리턴 하게 하려면 messageConverter 가 Jackson 라이브러리를 호출,
		 *  user 객체(자바 오브젝트)를 json 으로 변환,웹 브라우저로 리턴시킨다.
		 */
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join 으로 요청을 보내게된다.
	// http 의 body에 username, password,email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
//		System.out.println("username "+user.getUsername());
//		System.out.println("password "+user.getPassword());
//		System.out.println("email "+user.getEmail());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return"회원가입이 완료되었습니다";
	}
}
