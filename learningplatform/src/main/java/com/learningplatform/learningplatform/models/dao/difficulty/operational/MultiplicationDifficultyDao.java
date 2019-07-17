package com.learningplatform.learningplatform.models.dao.difficulty.operational;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.operational.MultiplicationDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MultiplicationDifficultyDao extends CrudRepository<MultiplicationDifficultyModel, Integer> {

	public List<MultiplicationDifficultyModel> findAll();

	public MultiplicationDifficultyModel findByUid(Integer uid);

}
