package com.example.demo.security;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;

public class DatabaseUserDetails implements UserDetails{

	private static final long serialVersionUID = 3731354320731070576L;	
	private final User user;
	
	public DatabaseUserDetails(User user) {

		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		Set<GrantedAuthority> grantRole = new HashSet<>();
		
		for (Role role : roles) 
			grantRole.add(new SimpleGrantedAuthority(role.getName()));
		
		return grantRole;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
