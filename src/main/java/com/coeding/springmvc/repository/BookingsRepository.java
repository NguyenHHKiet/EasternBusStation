package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.Bookings;

public interface BookingsRepository {

	void create(Bookings pojo);

	List<Bookings> findAll();

	void delete(Integer id);

	Bookings update(Bookings pojo);

	Bookings findById(int id);

	List<Bookings> findByUserId(int userId);
}
