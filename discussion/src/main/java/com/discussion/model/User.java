package com.discussion.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "d_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String  firstName;
	private String lastName;
	private String email;
	private Boolean isActive=true;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
       joinColumns =   @JoinColumn(name = "user_id",referencedColumnName = "id"),
       inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
    )
	private Set<Role> roles;
	
}
