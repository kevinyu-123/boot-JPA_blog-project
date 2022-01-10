package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration // bean 등록
@EnableWebSecurity //스프링시큐리티를 통한 필터 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소 접근시 권한 및 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService PrincipalDetailService;
	
	@Bean //IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인을 진행해 주는데 password를 가로챌때
	//해당 password가 뭘로 해쉬가 되어 회원가입 되어있는지 알아야
	//같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(PrincipalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		 .csrf().disable() //테스트시 걸어두는게 좋다.
		 .authorizeRequests()
		  .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**") //auth 하위는 다 허용
		  .permitAll()
		  .anyRequest() //아니면 필터링 필요
		  .authenticated()
		 .and()
		  .formLogin()
		  .loginPage("/auth/loginForm")
		  .loginProcessingUrl("/auth/loginProc")
		  .defaultSuccessUrl("/"); // 시큐리티가 대신 로그인을 해준다.
		
	}
	
}
