package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.User;

public interface UserRepository {

	void create(User pojo);

	List<User> findAll();

	void delete(Integer id);

	User update(User pojo);

	User findById(int id);
	
	User findByEmail(String emailId);
}
