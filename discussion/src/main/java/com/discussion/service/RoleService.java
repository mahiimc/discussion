package com.discussion.service;

import org.springframework.stereotype.Service;

import com.discussion.model.Role;
import com.discussion.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public Role addRole(Role role) {
		Role dbRole = findRoleByName(role.getName());
		if(dbRole == null ) {
			roleRepository.save(role);
			return role;
		}
		return dbRole;
	}
	
	public Role findRoleByName(String name) {
		return roleRepository.findRoleByName(name);
	}

}
