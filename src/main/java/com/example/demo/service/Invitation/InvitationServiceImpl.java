package com.example.demo.service.Invitation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Invitation;
import com.example.demo.entity.User;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.UserRepository;

@Service
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private InvitationRepository invitationRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public String SentInv(String email) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		User sender= userRepository.findByEmail(username);
		User receiver=userRepository.findByEmail(email);
		Invitation invitation=invitationRepository.findInv(sender.getId(), receiver.getId());
		if(invitation!=null) {
			return "invitation already sent previously";
		}
		Invitation inv= new Invitation();
		 inv.setSender(sender);
		 inv.setReceiver(receiver);
		inv.setDate(new Date());
		inv.setConfirmed(false);
		invitationRepository.save(inv);
			
		
		return "invitation sent successfully";
	}
	@Override
	public List<User> listSentInv() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		User user=userRepository.findByEmail(username);
		List<Invitation> SentInv=invitationRepository.listSentInvitation(user.getId());
		
		List<User> UserInvited = new ArrayList<>();
		for (Invitation inv : SentInv) {
			UserInvited.add(inv.getReceiver());
			
			
		}
		return UserInvited;
	}
	@Override
	public String confirmInv(Long id) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		User user=userRepository.findByEmail(username);
		Invitation inv = invitationRepository.findById(id).get();
		if (user.getId()!=inv.getReceiver().getId()) {
			return "cant confirm invitation with auther user";
		}
		inv.setConfirmed(true);
		invitationRepository.save(inv);
		User sender=inv.getSender();
		User receiver=inv.getReceiver();
		sender.getAmis().add(receiver);
		receiver.getAmis().add(sender);
		userRepository.save(sender);
		userRepository.save(receiver);
		return "invitation confirmed successfully";
	}
	@Override
	public List<User> listReceivedInv() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		User user=userRepository.findByEmail(username);
		List<Invitation> ReceivedInv=invitationRepository.listReceivedInvitation(user.getId());
		
		List<User> UserInvited = new ArrayList<>();
		for (Invitation inv : ReceivedInv) {
			UserInvited.add(inv.getReceiver());
			
			
		}
		return UserInvited;
	}
}
