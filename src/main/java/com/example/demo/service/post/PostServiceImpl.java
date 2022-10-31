package com.example.demo.service.post;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Util.UserUtils;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.PostModel;
import com.example.demo.repository.PostRepository;



@Service

public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserUtils userUtils;
	@Override
	public String addPost(PostModel postmodel) throws IOException {
		User user=userUtils.getConnectedUser();
		Post post=new Post();		
		post.setName(postmodel.getImage().getName());
		post.setDescription(postmodel.getDescription());
		post.setImage(postmodel.getImage().getBytes());
		post.setUser(user);
		postRepository.save(post);
		return "post published correctly";
	}
	@Override
	public String DeletePost(Post post) {
		if(post.getUser()==userUtils.getConnectedUser()) {
			postRepository.delete(post);
			return "post deleted succefully";			
		}
		return "Can't delete a post of not your";
	}
	@Override
	public List<Post> getMyPost() {
		
		return postRepository.findAllByUser(userUtils.getConnectedUser());
	}
	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return postRepository.findByUserIn(userUtils.getConnectedUser().getAmis());
		}
}
