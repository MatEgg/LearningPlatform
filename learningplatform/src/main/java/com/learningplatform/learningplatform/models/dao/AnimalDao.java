package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.Animal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface AnimalDao extends CrudRepository<Animal, Integer> {

	public List<Animal> findAll();

	public Animal findByUid(Integer uid);

}
