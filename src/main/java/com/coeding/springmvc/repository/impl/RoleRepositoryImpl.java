package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.Role;
import com.coeding.springmvc.repository.RoleRepository;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void create(Role pojo) {
		// TODO Auto-generated method stub
		logger.info("create(Role pojo) ::");
		sessionFactory.getCurrentSession().saveOrUpdate(pojo);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Role").list();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Role Bookings = (Role) sessionFactory.getCurrentSession().load(Role.class, id);
		if (null != Bookings) {
			this.sessionFactory.getCurrentSession().delete(Bookings);
		}
	}

	@Override
	public Role update(Role pojo) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pojo);
		return pojo;
	}

	@Override
	public Role findById(int id) {
		// TODO Auto-generated method stub
		return (Role) sessionFactory.getCurrentSession().get(Role.class, id);
	}

	@Override
	public Role findByRole(String role) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("from Role where role= :role");
		query.setString("role", role);
		Role r = (Role) query.uniqueResult();
		return r;
	}

}
