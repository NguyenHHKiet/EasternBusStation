package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.Role;

public interface RoleRepository {

	void create(Role pojo);

	List<Role> findAll();

	void delete(Integer id);

	Role update(Role pojo);

	Role findById(int id);
	
	Role findByRole(String name);
}
