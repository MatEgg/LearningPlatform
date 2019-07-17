package com.learningplatform.learningplatform.models.dao.difficulty.operational;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.operational.SubtractionDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SubtractionDifficultyDao extends CrudRepository<SubtractionDifficultyModel, Integer> {
	public List<SubtractionDifficultyModel> findAll();

	public SubtractionDifficultyModel findByUid(Integer uid);

}
