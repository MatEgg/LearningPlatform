package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

	public List<User> findAll();

	public User findByUid(Integer uid);
	
	public User findByName(String name);

}
