package com.example.demo.service.post;

import java.io.IOException;
import java.util.List;

import com.example.demo.entity.Post;
import com.example.demo.model.PostModel;

public interface PostService {

	String addPost(PostModel post)throws IOException;
	String DeletePost(Post post);
	List<Post> getMyPost();
	List<Post> getAllPost();

	
}
