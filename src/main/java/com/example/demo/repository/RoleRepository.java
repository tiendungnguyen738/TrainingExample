package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	
	Role findByName(String name);

}
