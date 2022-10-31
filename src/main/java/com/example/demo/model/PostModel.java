package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostModel {

	private String Description;
	private MultipartFile image;
}
