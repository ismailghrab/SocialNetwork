package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	@Query(value="SELECT * FROM Comment m Where m.ps_cmt = ?1 ",
			nativeQuery = true)
	List<Comment> findallbyPost(Long id);

}
