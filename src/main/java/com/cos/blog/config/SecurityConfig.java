package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // bean 등록
@EnableWebSecurity //스프링시큐리티를 통한 필터 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소 접근시 권한 및 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean //IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		 .csrf().disable()
		 .authorizeRequests()
		  .antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //auth 하위는 다 허용
		  .permitAll()
		  .anyRequest() //아니면 필터링 필요
		  .authenticated()
		 .and()
		  .formLogin()
		  .loginPage("/auth/loginForm");
	}
	
}
