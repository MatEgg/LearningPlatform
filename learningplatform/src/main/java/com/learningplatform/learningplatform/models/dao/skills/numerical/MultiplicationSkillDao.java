package com.learningplatform.learningplatform.models.dao.skills.numerical;

import com.learningplatform.learningplatform.models.skills.numerical.MultiplicationSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MultiplicationSkillDao extends CrudRepository<MultiplicationSkill, Integer> {

	public List<MultiplicationSkill> findAll();

	public MultiplicationSkill findByUid(Integer uid);
}
