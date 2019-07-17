package com.learningplatform.learningplatform.models.dao.difficulty;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.Difficulty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DifficultyDao extends CrudRepository<Difficulty, Integer> {

	public List<Difficulty> findAll();

	public Difficulty findByUid(Integer uid);
	
}
