package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.BusData;
import com.coeding.springmvc.repository.BusDataRepository;

@Repository
@Transactional
public class BusDataRepositoryImpl implements BusDataRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(BusDataRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void create(BusData pojo) {
		// TODO Auto-generated method stub
		logger.info("create(BusData pojo) ::");
		sessionFactory.getCurrentSession().saveOrUpdate(pojo);
	}

	@Override
	public List<BusData> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from BusData").list();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		BusData Bookings = (BusData) sessionFactory.getCurrentSession().load(BusData.class, id);
		if (null != Bookings) {
			this.sessionFactory.getCurrentSession().delete(Bookings);
		}
	}

	@Override
	public BusData update(BusData pojo) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(pojo);
		return pojo;
	}

	@Override
	public BusData findById(int id) {
		// TODO Auto-generated method stub
		return (BusData) sessionFactory.getCurrentSession().get(BusData.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BusData> findByToFromAndDate(String to, String from, String date) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("FROM BusData WHERE to = :to AND from = :from AND date = :date")
				.setParameter("to", to).setParameter("from", from).setParameter("date", date).list();
	}

}
