package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private SessionFactory sessionFactory;
	
	@Override
	public void create(User pojo) {
		// TODO Auto-generated method stub
		logger.info("create(User pojo) ::");
		sessionFactory.getCurrentSession().saveOrUpdate(pojo);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		User Bookings = (User) sessionFactory.getCurrentSession().load(User.class, id);
		if (null != Bookings) {
			this.sessionFactory.getCurrentSession().delete(Bookings);
		}
	}

	@Override
	public User update(User pojo) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pojo);
		return pojo;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("from User where email= :email");
		query.setString("email", email);
		User e = (User) query.uniqueResult();
		return e;
	}

}
