package com.example.demo.service.comment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Util.UserUtils;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserUtils userUtils;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	@Override
	public String addcomment(Comment comment,Post post) {
		
		Comment savedComment=commentRepository.save(comment);
		User user= userUtils.getConnectedUser();
		user.getComments().add(savedComment);
		userRepository.save(user);
		post.getComment().add(savedComment);
		postRepository.save(post);		
		return "comment added successfully";
	}
	@Override
	public String updatecomment(Comment comment) {
		
		commentRepository.save(comment);		
		return "comment updated successfully";
	}
	@Override
	public String deletecomment(Comment comment) {
		commentRepository.delete(comment);
		return "comment deleted successfully";
	}
	@Override
	public List<Comment> getcomments(Post post) {
		
		return commentRepository.findallbyPost(post.getId());

	}

}
