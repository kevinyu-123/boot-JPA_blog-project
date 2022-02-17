package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService  {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	
	// 회원가입
	@Transactional
	public void register(Board board,User user) { //title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
		
	}
	
	@Transactional(readOnly = true)
	public Page<Board> viewBoard(Pageable pageable){
		return boardRepository.findAll(pageable);
		
	}
	@Transactional(readOnly = true)
	public Board viewNote(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("아이디 찾기 실패");
				});
	}
	
	@Transactional
	public void deleteCont(int id) {
		boardRepository.deleteById(id);
				
	}
	
	@Transactional
	public void updateCont(int id,Board requestBoard) {
		Board board = boardRepository.findById(id)
			.orElseThrow(()->{
				return new IllegalArgumentException("아이디 찾기 실패");
			}); // 영속화
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//함수 종료시 트렌젝션이 종료된다. 더티체킹 - 자동 업데이트 db로 flush 됌
	}
	
	@Transactional
	public void writeReply(User user, int boardId, Reply requestReply) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(()->{
					return new IllegalArgumentException("게시글 아이디를 찾을 수 없습니다.");
				}); 
		
		requestReply.setUser(user);
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
	}
	
	
	
	
	

}
