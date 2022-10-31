package com.example.demo.service.message;

import java.util.List;

import com.example.demo.entity.Message;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;

public interface MessageService {

	String sentMessage(MessageModel message);

	List<Message> listMessage(UserModel usermodel);



}
