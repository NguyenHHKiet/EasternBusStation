package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
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
	private SqlSessionFactory sessionFactory;
	
	@Override
	public Bookings findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create(Bookings pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Bookings pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookings> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
