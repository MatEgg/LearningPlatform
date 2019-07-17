package com.learningplatform.learningplatform.models.dao.difficulty.conceptual;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ConceptualDifficultyDao extends CrudRepository<ConceptualDifficultyModel, Integer> {
	public List<ConceptualDifficultyModel> findAll();

	public ConceptualDifficultyModel findByUid(Integer uid);

}
