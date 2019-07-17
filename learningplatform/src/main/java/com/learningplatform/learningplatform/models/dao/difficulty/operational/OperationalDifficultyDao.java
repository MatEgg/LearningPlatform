package com.learningplatform.learningplatform.models.dao.difficulty.operational;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.operational.OperationalDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OperationalDifficultyDao extends CrudRepository<OperationalDifficultyModel, Integer> {

	public List<OperationalDifficultyModel> findAll();

	public OperationalDifficultyModel findByUid(Integer uid);

}
