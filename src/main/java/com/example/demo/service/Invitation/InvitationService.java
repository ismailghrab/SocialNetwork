package com.example.demo.service.Invitation;

import java.util.List;

import com.example.demo.entity.User;

public interface InvitationService {

	String SentInv(String email);

	List<User> listSentInv();
	List<User> listReceivedInv();

	String confirmInv(Long id);
}
