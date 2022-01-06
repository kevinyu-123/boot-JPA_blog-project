package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length=100)
	private String title;
	
	@Lob // 대용량 데이터를 쓸때 씀
	private String content; //섬머노트 라이브러리

	@ColumnDefault("0")
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)  // Many = board , user = one 
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappedby 는 연관관계의 주인이 아니다. db에 컬럼을 만들지 않음.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
