package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails{
	
	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//비밀번호 만료 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//계정 활성화
	@Override
	public boolean isEnabled() {
		return true;
	}
	//계정 권한의 목록을 리턴한다
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors =new ArrayList<>();
		collectors.add(()->{
			return "ROLE_"+user.getRole();
		});
		return collectors;
	}
}
