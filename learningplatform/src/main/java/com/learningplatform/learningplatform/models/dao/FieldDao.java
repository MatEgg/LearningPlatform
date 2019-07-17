package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.Field;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FieldDao extends CrudRepository<Field, Integer> {

	public List<Field> findAll();
	
	@Query("SELECT a FROM Field a where a.name Not Like ?1")
	public List<Field> findAllNotLike(String name);

	public Field findByUid(Integer uid);
}
