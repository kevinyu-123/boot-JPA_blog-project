package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	
	// 회원가입
	@Transactional
	public void register(User user) {
		String rawPwd = user.getPassword();
		String encodedPwd = encoder.encode(rawPwd);
		user.setPassword(encodedPwd);
		user.setRole(RoleType.USER);

		userRepository.save(user);
		
	}
	
	//회원수정
	@Transactional
	public void userUpdate(User user) {
		//수정시 영속성 컨텍스트 user 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
		//영속화를 위해 select문 실행
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		//validate 체크 oauth 값이 없을때만 수정이 가능하다.
		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
		String rawPwd = user.getPassword();
		String encPwd = encoder.encode(rawPwd);
		persistance.setPassword(encPwd);
		persistance.setEmail(user.getEmail());		
		}
		// 함수 종료 후 영속화된 객체에 변화가 감지되면 더티체킹을 통하여 update문이 실행
		
		
	}
	
	@Transactional(readOnly = true)
	public User findMember(String username) {		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	
	
	
	
	
	

}
