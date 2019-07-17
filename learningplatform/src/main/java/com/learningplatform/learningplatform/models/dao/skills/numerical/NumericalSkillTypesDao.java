package com.learningplatform.learningplatform.models.dao.skills.numerical;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkillTypes;

@Repository
@Transactional
public interface NumericalSkillTypesDao extends CrudRepository<NumericalSkillTypes, Integer> {

	public List<NumericalSkillTypes> findAll();

	public NumericalSkillTypes findByUid(Integer uid);

}
