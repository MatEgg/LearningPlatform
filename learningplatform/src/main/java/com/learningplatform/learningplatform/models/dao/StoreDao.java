package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.Store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StoreDao extends CrudRepository<Store, Integer> {

	public List<Store> findAll();

	public List<Store> findByOrderByAreaAsc();

	public List<Store> findByOrderByWidthAsc();

	public List<Store> findByOrderByLengthAsc();

	public Store findByUid(Integer uid);

}
