package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Message;

import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.message.MessageService;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class MessageController {

	@Autowired
	MessageService messageService;
	@PostMapping("/sendMessage")
	public String sentMessage(@RequestBody MessageModel message) {
		
		return messageService.sentMessage(message);
	}
	@GetMapping("/listMessage")
	public List<Message> listMessage(@RequestBody UserModel usermodel) {
		
		return messageService.listMessage(usermodel);
	}
}
