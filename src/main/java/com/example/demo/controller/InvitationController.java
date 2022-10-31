package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.Invitation.InvitationService;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class InvitationController {

	@Autowired
	private InvitationService invitationService;
	@PostMapping("/SentInv")
	private String SentInv(@RequestBody User user){
		return invitationService.SentInv( user.getEmail());
	}
	@PostMapping("/confirminv")
	private String confirmInv(@RequestBody User user){
		return invitationService.confirmInv( user.getId());
	}
	@GetMapping("/listsentinv")
	private List<User> listSentInv(){
		return invitationService.listSentInv();
	}
	@GetMapping("/listreceivedinv")
	private List<User> listReceivedInv(){
		return invitationService.listReceivedInv();	
	}
}
