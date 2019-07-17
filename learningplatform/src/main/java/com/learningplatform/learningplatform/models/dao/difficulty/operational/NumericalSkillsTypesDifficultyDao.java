package com.learningplatform.learningplatform.models.dao.difficulty.operational;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.difficulty.operational.NumericalSkillTypesDifficultyModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface NumericalSkillsTypesDifficultyDao extends CrudRepository<NumericalSkillTypesDifficultyModel, Integer> {

	public List<NumericalSkillTypesDifficultyModel> findAll();

	public NumericalSkillTypesDifficultyModel findByUid(Integer uid);

}
