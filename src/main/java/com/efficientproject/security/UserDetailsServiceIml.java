package com.efficientproject.security;

import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.efficientproject.persistance.dao.UserRepository;
import com.efficientproject.persistance.model.User;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceIml implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		try {
			final User user = userRepository.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException("No user found with username: " + email);
			}
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
					getAuthorities(singletonList(getUsersRole(user.isAdmin()))));
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	private String getUsersRole(boolean isAdmin) {
		if(isAdmin) {
			return "ADMIN";
		}else {
			return "USER";
		}
	}
	
	private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
