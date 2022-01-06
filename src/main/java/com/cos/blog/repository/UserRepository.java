package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동으로 bean 등록이 된다.
//@repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
	
	

}


//JPA 네이밍 쿼리
//SELECT * FROM user WHERE username = ? AND password = ?  쿼리가 생성된다.
//앞에 유저는 return 값
//User findByUsernameAndPassword(String username,String password);

////native query
//@Query(value="SELECT * FROM user WHERE username = ? AND password = ?",nativeQuery = true)
//User login(String username,String password);
