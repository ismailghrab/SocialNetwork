package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Lob
	private byte[] image;
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="userId",referencedColumnName="id")
	private User user;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ps_cmt", referencedColumnName="id")
	private List<Comment>  comment=new ArrayList<>();

}
