package com.learningplatform.learningplatform.models.dao.skills.numerical;

import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface NumericalSkillDao extends CrudRepository<NumericalSkill, Integer> {

	public List<NumericalSkill> findAll();

	public NumericalSkill findByUid(Integer uid);

}
