package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.Bookings;

public interface BookingsRepository {
	Bookings findById(int id);
	List<Bookings> findAll();
	String create(Bookings pojo);
	String update(Bookings pojo);
	String delete(int id);
	List<Bookings> findByUserId(int userId);
}
