package com.learningplatform.learningplatform.models.dao.skills.conceptual;

import com.learningplatform.learningplatform.models.skills.conceptual.NumberSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface NumberSkillDao extends CrudRepository<NumberSkill, Integer> {
	public List<NumberSkill> findAll();

	public NumberSkill findByUid(Integer uid);
}
