package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table
public class Candidates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer c_id;
	
	private Integer candidate_id;
	
	private String name;
	
//	@OneToOne(mappedBy = "candidates")
//	@JsonIgnore
//	private VoteUsers users;
	
	
	
}
