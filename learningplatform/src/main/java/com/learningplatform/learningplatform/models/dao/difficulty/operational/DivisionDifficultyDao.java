package com.learningplatform.learningplatform.models.dao.difficulty.operational;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.operational.DivisionDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DivisionDifficultyDao extends CrudRepository<DivisionDifficultyModel, Integer> {
	public List<DivisionDifficultyModel> findAll();

	public DivisionDifficultyModel findByUid(Integer uid);

}
