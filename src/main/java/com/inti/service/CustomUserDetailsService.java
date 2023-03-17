package com.inti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.inti.model.Utilisateur;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UtilisateurServiceImpl usi;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur u = usi.findByUsernameorEmail(username);
		
		if(u == null)
		{
			throw new UsernameNotFoundException("L'utilisateur n'existe pas " + username + " n'existe pas");
		}
		
		return User
				.withUsername(u.getUsername())
				.password(u.getMdp())
				.roles(u.getRole().getNom())
				.build();
	}

}
