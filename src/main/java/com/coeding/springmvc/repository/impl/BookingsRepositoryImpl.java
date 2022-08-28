package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.BusData;
import com.coeding.springmvc.repository.BookingsRepository;

@Repository
@Transactional
public class BookingsRepositoryImpl implements BookingsRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(BookingsRepositoryImpl.class);

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public Bookings findById(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		Bookings Bookings = session.selectOne("mapper.booking.findById", id);
		logger.info("Bookings ::"+Bookings);
		return Bookings;
	}

	@Override
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		List<Bookings> Bookings = session.selectList("mapper.booking.findAll");
		for(Bookings p : Bookings){
			logger.info("Bookings List::"+p);
		}
		return Bookings;
	}

	@Override
	public String create(Bookings pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.insert("mapper.booking.create", pojo);
		session.commit();
		logger.info("Create booking::");
		return "Create booking";
	}

	@Override
	public String update(Bookings pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.update("mapper.booking.update", pojo);
		session.commit();
		logger.info("Create booking::");
		return "Create booking";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.delete("mapper.booking.delete", id);
		session.commit();
		logger.info("delete booking::");
		return "delete booking";
	}

	@Override
	public List<Bookings> findByUserId(int userId) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		List<Bookings> Bookings = session.selectList("mapper.booking.findByUserId", userId);
		for(Bookings p : Bookings){
			logger.info("Bookings List::"+p);
		}
		return Bookings;
	}

}
