package com.coeding.springmvc.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coeding.springmvc.entity.Role;
import com.coeding.springmvc.repository.RoleRepository;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public Role findById(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		Role Role = session.selectOne("mapper.role.findById", id);
		logger.info("Role ::"+Role);
		return Role;
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		List<Role> Role = session.selectList("mapper.role.findAll");
		for(Role p : Role){
			logger.info("Role List::"+p);
		}
		return Role;
	}

	@Override
	public String create(Role pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.insert("mapper.role.create", pojo);
		session.commit();
		logger.info("Create role::");
		return "Create role";
	}

	@Override
	public String update(Role pojo) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.update("mapper.role.update", pojo);
		session.commit();
		logger.info("update role::");
		return "update role";
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		session.delete("mapper.role.delete", id);
		session.commit();
		logger.info("delete role::");
		return "delete role";
	}

	@Override
	public Role findByRole(String name) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		Role Role = session.selectOne("mapper.role.findByRole", name);
		logger.info("Role ::"+Role);
		return Role;
	}

}
