package com.coeding.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.coeding.springmvc.entity.BusData;

public interface BusDataRepository {

	void create(BusData pojo);

	List<BusData> findAll();

	void delete(Integer id);

	BusData update(BusData pojo);

	BusData findById(int id);

//	@Query(value = "select * from BusData  where busdata.toDestination =:to and busdata.fromDestination =:from and busdata.filterDate =:date  order By busdata.filterDate desc ", nativeQuery = true)
	List<BusData> findByToFromAndDate(String to, String from, String date);
}
