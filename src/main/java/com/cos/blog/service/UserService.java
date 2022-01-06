package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
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

	
	
	
	
	
	
	
	

}
