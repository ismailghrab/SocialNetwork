package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.model.PostModel;
import com.example.demo.service.post.PostService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@CrossOrigin
public class PostController {

	@Autowired
	PostService  postService;
	@PostMapping("/post/add")
	private String addPost(@ModelAttribute PostModel post) throws IOException {
		log.info(post.getDescription());
		return postService.addPost(post);
		
	}
	@GetMapping("/post/profile")
	private List<Post> getmyposts(){
		
		return postService.getMyPost();
	}
	@GetMapping("/post/home")
	private List<Post> getallposts(){
		
		return postService.getAllPost();
	}
}
