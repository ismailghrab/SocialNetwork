package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String message;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private User sender;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private User receiver;
	private Date CreatedAt;
}
