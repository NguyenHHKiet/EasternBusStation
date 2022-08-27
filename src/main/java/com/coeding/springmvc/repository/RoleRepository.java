package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.Role;

public interface RoleRepository {

	Role findById(int id);
	List<Role> findAll();
	String create(Role pojo);
	String update(Role pojo);
	String delete(int id);
	Role findByRole(String name);
}
