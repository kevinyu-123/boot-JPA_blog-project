package com.cos.blog.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {	
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	@ResponseBody
	public String kakaoCallback(String code) {
		//post 방식으로 key = value 데이터를 요청
		RestTemplate rt = new RestTemplate();
		
		//httpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//httpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id","3cc67d1cd705443fbd2c5ea3f22420fe");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//httpHeader 와 httpBody 를 하나의 오브젝트에 담는다.
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		
		//http 요청하기 - post 방식 , response 변수의 응답을 받는다.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		//Gson,Json simple, objectMapper 라이브러리들이다.
		ObjectMapper obMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("kakao access token: "+oauthToken.getAccess_token());
		
		//////////////////////////////////////////////
		
		RestTemplate rt2 = new RestTemplate();
		
		//httpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("content-type","application/x-www-form-urlencoded;charset=utf-8");
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		
		//httpHeader 와 httpBody 를 하나의 오브젝트에 담는다.
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);
		
		//http 요청하기 - post 방식 , response 변수의 응답을 받는다.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class);
		
		System.out.println(response2.getBody());
		
		// objectMapper를 통하여 정보를 저장한다.
		
		ObjectMapper obMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 아이디(번호)"+kakaoProfile.getId());
		System.out.println("email: "+kakaoProfile.getKakao_account().getEmail());
		
		return response2.getBody();
	}
	
	
	
	
	
	
}
