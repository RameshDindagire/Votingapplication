package com.example.demo.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="USER")
public class User {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;

	 private String username;
	 
	 private String password;
	 
	 @Size(max = 10 ,message = "Plese Check your Pan Number !")
	 @Column(length = 10)
	 private String pannumber;
	 
	 @Size(max = 10,message = "Check your Number !")
	 @Column(length = 10)
	 private String mobilNumber;
	 
	 private String email;
	 
	 private String role;
	 
//	 @OneToOne(mappedBy = "user")
//	 @JsonIgnore
//	 private VoteUsers users;

}
