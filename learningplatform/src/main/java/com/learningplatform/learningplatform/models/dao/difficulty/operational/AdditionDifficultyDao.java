package com.learningplatform.learningplatform.models.dao.difficulty.operational;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.operational.AdditionDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AdditionDifficultyDao extends CrudRepository<AdditionDifficultyModel, Integer> {

	public List<AdditionDifficultyModel> findAll();

	public AdditionDifficultyModel findByUid(Integer uid);


}
