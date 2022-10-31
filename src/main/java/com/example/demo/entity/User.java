package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.JoinColumn;


@Entity
@Data
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	@JsonIgnore 
	private String password;
	private Date dob;
	private Boolean state;
	private String role;
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "amis",
							joinColumns =@JoinColumn(name="id1" ),
							inverseJoinColumns = @JoinColumn(name="id2"))
	@JoinColumn(name="amis_id", referencedColumnName="id")
	
	private List<User> amis=new ArrayList<>();

	@OneToMany
	@JoinColumn(name="cmt_usr", referencedColumnName="id")
	private List<Comment> comments= new ArrayList<>();
	

}
