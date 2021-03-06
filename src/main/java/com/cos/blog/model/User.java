package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //user 클래스가 mysql에 테이블이 생성이 된다.
//@DynamicInsert //insert 시에 null 인 값을 제외시켜준다.
public class User {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // auto_increment
	
	@Column(nullable=false,length=200,unique = true)
	private String username; // 아이디
	
	@Column(nullable=false,length=100) //해쉬( 데이터베이스에 암호화 시켜 저장하기 때문에 넉넉하게 준다)
	private String password;
	
	@Column(nullable=false,length=50)
	private String email;
	
	//DB 는 roletype 이 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum 을 쓰는게 좋다. //권한을줄 수 있다. ex : admin , user, manager
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
	
	// 사이트별 로그인 경로
	private String oauth;

}
