package com.learningplatform.learningplatform.models.dao.skills.conceptual;

import com.learningplatform.learningplatform.models.skills.conceptual.SolvingTaskSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SolvingTaskSkillDao extends CrudRepository<SolvingTaskSkill, Integer> {
	public List<SolvingTaskSkill> findAll();

	public SolvingTaskSkill findByUid(Integer uid);
}
