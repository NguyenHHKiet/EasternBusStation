package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.User;

public interface UserRepository {

	User findById(int id);
	List<User> findAll();
	String create(User pojo);
	String update(User pojo);
	String delete(int id);
	User findByEmail(String emailId);
}
