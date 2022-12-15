package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.security.DatabaseUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	
	public void save(User user) {		
		userRepo.save(user);
	}
	
	public void delete(User user) {		
		userRepo.delete(user);
	}
	
	public Optional<User> findById(int id) {		
		return userRepo.findById(id);
	}
	
	public List<User> findAll() {		
		return userRepo.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if (userOpt.isEmpty()) throw new UsernameNotFoundException("User not found");
		
		return new DatabaseUserDetails(userOpt.get());
	}
}