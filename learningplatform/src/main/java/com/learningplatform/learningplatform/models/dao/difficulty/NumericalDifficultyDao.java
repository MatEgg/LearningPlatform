package com.learningplatform.learningplatform.models.dao.difficulty;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.NumericalDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface NumericalDifficultyDao extends CrudRepository<NumericalDifficultyModel, Integer>  {

	public List<NumericalDifficultyModel> findAll();

	public NumericalDifficultyModel findByUid(Integer uid);

}
