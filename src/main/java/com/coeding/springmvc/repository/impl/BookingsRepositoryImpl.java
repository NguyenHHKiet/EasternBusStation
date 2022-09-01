package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.repository.BookingsRepository;

@Repository
@Transactional
public class BookingsRepositoryImpl implements BookingsRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(BookingsRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Bookings pojo) {
		// TODO Auto-generated method stub
		logger.info("create(Bookings pojo) ::");
		sessionFactory.getCurrentSession().saveOrUpdate(pojo);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Bookings").list();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Bookings Bookings = (Bookings) sessionFactory.getCurrentSession().load(Bookings.class, id);
		if (null != Bookings) {
			this.sessionFactory.getCurrentSession().delete(Bookings);
		}
	}

	@Override
	public Bookings update(Bookings pojo) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pojo);
		return pojo;
	}

	@Override
	public Bookings findById(int id) {
		// TODO Auto-generated method stub
		return (Bookings) sessionFactory.getCurrentSession().get(Bookings.class, id);
	}

	@Override
	public List<Bookings> findByUserId(int userId) {
		// TODO Auto-generated method stub
		String query = "select * from Bookings where userId= :userId";
		return sessionFactory.getCurrentSession().createQuery("FROM Bookings u WHERE  u.userId = :userId")
				.setParameter("userId", userId).list();
	}

}
