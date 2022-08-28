package com.coeding.springmvc.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.BusData;
import com.coeding.springmvc.repository.BusDataRepository;

@Repository
@Transactional
public class BusDataRepositoryImpl implements BusDataRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(BusDataRepositoryImpl.class);

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public BusData findById(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		BusData BusData = session.selectOne("mapper.busdata.findById", id);
		logger.info("BusData ::"+BusData);
		return BusData;
	}

	@Override
	public List<BusData> findAll() {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		List<BusData> BusData = session.selectList("mapper.busdata.findAll");
		for(BusData p : BusData){
			logger.info("BusData List::"+p);
		}
		return BusData;
	}

	@Override
	public String create(BusData pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.insert("mapper.busdata.create", pojo);
		session.commit();
		logger.info("Create BusData::");
		return "Create BusData";
	}

	@Override
	public String update(BusData pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.update("mapper.busdata.update", pojo);
		session.commit();
		logger.info("update busdata::");
		return "update busdata";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.delete("mapper.busdata.delete", id);
		session.commit();
		logger.info("delete busdata::");
		return "delete busdata";
	}

	@Override
	public List<BusData> findByToFromAndDate(String to, String from, String date) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("to", to);
		map.put("from", from);
		map.put("date", date);
		List<BusData> BusData = session.selectList("mapper.busdata.findByToFromAndDate", map);
		for(BusData p : BusData){
			logger.info("BusData List::"+p);
		}
		return BusData;
	}

}
