package com.coeding.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.repository.BookingsRepository;
import com.coeding.springmvc.service.BookingsService;

@Service
public class BookingsServiceImpl implements BookingsService {

	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Override
	public void create(Bookings pojo) {
		// TODO Auto-generated method stub
		bookingsRepository.create(pojo);
	}

	@Override
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		return bookingsRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		bookingsRepository.delete(id);
	}

	@Override
	public Bookings update(Bookings pojo) {
		// TODO Auto-generated method stub
		return bookingsRepository.update(pojo);
	}

	@Override
	public Bookings findById(int id) {
		// TODO Auto-generated method stub
		return bookingsRepository.findById(id);
	}

	@Override
	public List<Bookings> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return bookingsRepository.findByUserId(userId);
	}

}
