package com.example.demo.service.comment;

import java.util.List;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;

public interface CommentService {

	String addcomment(Comment comment,Post post);

	String updatecomment(Comment comment);

	String deletecomment(Comment comment);

	List<Comment> getcomments(Post post);

}
