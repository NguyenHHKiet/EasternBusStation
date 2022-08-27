package com.coeding.springmvc.repository;

import java.util.List;

import com.coeding.springmvc.entity.BusData;

public interface BusDataRepository {

	BusData findById(int id);
	List<BusData> findAll();
	String create(BusData pojo);
	String update(BusData pojo);
	String delete(int id);
	List<BusData> findByToFromAndDate(String to , String from, String date);
}
