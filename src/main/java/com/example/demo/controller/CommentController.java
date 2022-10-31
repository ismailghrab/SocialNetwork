package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.service.comment.CommentService;

@RestController("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/addComment")
	public String addcomment(@RequestBody Comment comment,@RequestBody Post post) {
	
		return commentService.addcomment(comment,post);
	}
	@PostMapping("/updateComment")
	public String updatecomment(@RequestBody Comment comment) {
	
		return commentService.updatecomment(comment);
	}
	@PostMapping("/deleteComment")
	public String deletecomment(@RequestBody Comment comment) {
	
		return commentService.deletecomment(comment);
	}
	@GetMapping("/getComment")
	public List<Comment> getcomments(@RequestBody Post post) {
	
		return commentService.getcomments(post);
	}
		
	
	
}
