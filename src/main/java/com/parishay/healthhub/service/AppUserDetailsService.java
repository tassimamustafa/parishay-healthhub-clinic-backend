package com.parishay.healthhub.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.parishay.healthhub.entity.AppUser;
import com.parishay.healthhub.repository.AppUserRepository;
import java.util.Collections;
@Service

public class AppUserDetailsService implements UserDetailsService {
	private final AppUserRepository appUserRepository;
	
	public AppUserDetailsService(AppUserRepository appUserRepository) {
		this.appUserRepository=appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		AppUser appUser=appUserRepository.findByUsername(username).orElseThrow(()  -> new UsernameNotFoundException("User not found:  "));
	
	GrantedAuthority authority=new SimpleGrantedAuthority(appUser.getRole());
	
	return new User(
			appUser.getUsername(),
			appUser.getPassword(),
			Collections.singleton(authority)
			
			);
	}
	
}
