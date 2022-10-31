package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	@Query(value="SELECT * FROM Message m Where m.sender_id = ?1 AND m.receiver_id = ?2 OR m.sender_id = ?2 AND m.receiver_id = ?1 ",
			nativeQuery = true)
		List<Message> findallmessages(Long idsender,Long idreceiver);

}
