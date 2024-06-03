package com.discussion.service;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.discussion.model.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataIntializationService implements CommandLineRunner {

	private final RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
		Set<String> roles = Set.of("USER","ADMIN","MODERATOR");
		roles.stream().map(role -> new Role(role)).forEach(roleObj -> roleService.addRole(roleObj));
	}
	


}
