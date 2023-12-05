package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String pannumber) throws UsernameNotFoundException {
		User user = userRepository.getUserByUserName(pannumber);
//		System.out.println("62222222222222--->"+user);
		if(user == null) {
			throw new UsernameNotFoundException("Colud Not Found Exception");
		}
//		return new org.springframework.security.core.userdetails.User(
//	            user.getUsername(),
//	            user.getPassword(),
//	            AuthorityUtils.createAuthorityList("ROLE_" + user.getRole())
//	        );
		
		CustomeUserDetails customeUserDetails = new CustomeUserDetails(user);
		return customeUserDetails;
	}

}
