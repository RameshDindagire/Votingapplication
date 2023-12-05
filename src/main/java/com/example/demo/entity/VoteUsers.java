package com.example.demo.entity;


import java.util.Optional;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "c_id" ,referencedColumnName = "c_id")
	private String candidates;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "u_id" ,referencedColumnName = "id")
	private String user;
}
