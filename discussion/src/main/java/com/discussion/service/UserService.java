package com.discussion.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.discussion.model.Role;
import com.discussion.model.SecuredUser;
import com.discussion.model.User;
import com.discussion.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;

	public SecuredUser findUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email);
		return new SecuredUser(user);
	}

	public User saveUser(User user) {
		Role userRole = roleService.findRoleByName("USER");
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRoles(Set.of(userRole));
		userRepository.save(user);
		return user;
	}
}
