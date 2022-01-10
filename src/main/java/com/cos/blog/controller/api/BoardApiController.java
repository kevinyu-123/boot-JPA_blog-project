package com.cos.blog.controller.api;

import java.security.KeyStore.PrivateKeyEntry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.Board;

import com.cos.blog.service.BoardService;


@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	//회원가입/////
	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		boardService.register(board, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> deleteById(@PathVariable int id){
		boardService.deleteCont(id);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);

	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDTO<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.updateCont(id,board);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
