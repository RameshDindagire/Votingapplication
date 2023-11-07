package com.example.demo.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.AdminUsers;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminUserRepository;
import com.example.demo.service.AdminUserService;

public class AdminDetailsImpl implements UserDetailsService{
	
	private AdminUserRepository adminUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String pannumber) throws UsernameNotFoundException {
		AdminUsers user = adminUserRepository.findByUsername(pannumber);
//		System.out.println("62222222222222--->"+user);
		if(user == null) {
			throw new UsernameNotFoundException("Colud Not Found Exception");
		}
//		return new org.springframework.security.core.userdetails.User(
//	            user.getUsername(),
//	            user.getPassword(),
//	            AuthorityUtils.createAuthorityList("ROLE_" + user.getRole())
//	        );
		
		CustomeAdminDetails customeUserDetails = new CustomeAdminDetails(user);
		return customeUserDetails;
	}
}