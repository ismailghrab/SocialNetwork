package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Invitation;
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long>{
	@Query(value="SELECT * FROM Invitation i Where i.sender_id = ?1 AND i.confirmed=0 ",
			nativeQuery = true)
		List<Invitation> listSentInvitation(Long id);
	@Query(value="SELECT * FROM Invitation i Where i.sender_id = ?1 AND i.receiver_id = ?2 ",
			nativeQuery = true)
<Optional>	Invitation findInv(Long sender,Long receiver );
	@Query(value="SELECT * FROM Invitation i Where i.receiver_id = ?1 AND i.confirmed=0 ",
			nativeQuery = true)
	List<Invitation> listReceivedInvitation(Long id);
}
