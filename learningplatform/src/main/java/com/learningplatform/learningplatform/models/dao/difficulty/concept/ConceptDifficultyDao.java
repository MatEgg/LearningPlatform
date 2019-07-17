package com.learningplatform.learningplatform.models.dao.difficulty.concept;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ConceptDifficultyDao extends CrudRepository<ConceptDifficultyModel, Integer> {
	
	public List<ConceptDifficultyModel> findAll();

	public ConceptDifficultyModel findByUid(Integer uid);
}
