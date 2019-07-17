package com.learningplatform.learningplatform.models.dao.skills.conceptual;

import com.learningplatform.learningplatform.models.skills.conceptual.RandomnessSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RandomnessSkillDao extends CrudRepository<RandomnessSkill, Integer> {
	public List<RandomnessSkill> findAll();

	public RandomnessSkill findByUid(Integer uid);
}
