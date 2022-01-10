package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable) { //세션에 접근하기 위함
		model.addAttribute("boards",service.viewBoard(pageable));
		return "index";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model) {
		model.addAttribute("board",service.viewNote(id));
		return "board/detail";

	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String udateForm(@PathVariable int id,Model model) {
		model.addAttribute("board",service.viewNote(id));
		return "board/updateForm";
	}
	
	
	
	
	
	
	
}
