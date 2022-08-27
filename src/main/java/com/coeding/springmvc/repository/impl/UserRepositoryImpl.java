package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.User;
import com.coeding.springmvc.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		User user = session.selectOne("mapper.user.findById", id);
		logger.info("User ::"+user);
		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		List<User> userList = session.selectList("mapper.user.findAll");
		for(User p : userList){
			logger.info("User List::"+p);
		}
		return userList;
	}

	@Override
	public String create(User pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.insert("mapper.user.create", pojo);
		session.commit();
		logger.info("Create user::");
		return "Create user";
	}

	@Override
	public String update(User pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.update("mapper.user.update", pojo);
		session.commit();
		logger.info("Create user::");
		return "Create user";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.delete("mapper.user.delete", id);
		session.commit();
		logger.info("delete user::");
		return "delete user";
	}

	@Override
	public User findByEmail(String emailId) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		User user = session.selectOne("mapper.user.findByEmail", emailId);
		logger.info("findUserByEmail ::"+user);
		return (user);
	}

}
