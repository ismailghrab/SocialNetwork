package com.example.demo.service.message;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public String sentMessage(MessageModel messagemodel) {
		Message msg= new Message();
		msg.setMessage(messagemodel.getMessage());
		msg.setCreatedAt(new Date());
		User to=userRepository.findByEmail(messagemodel.getEmail());
		msg.setReceiver(to);
		String username=SecurityContextHolder.getContext().getAuthentication().getName().toString();
		User from=userRepository.findByEmail(username);
		msg.setSender(from);
		messageRepository.save(msg);
		return "message sent successfully";
	}
	@Override
	public List<Message> listMessage(UserModel usermodel) {

		User me=userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		Long usermodelid=null;
		if (usermodel.getId()==null) {
			if(usermodel.getEmail()!=null) {
			usermodelid=userRepository.findByEmail(usermodel.getEmail()).getId();
			}
		}else {
			usermodelid=usermodel.getId();
		}
		List<Message> listmessage=messageRepository.findallmessages(me.getId(), usermodelid);
		return listmessage;
	}

}
