package com.learningplatform.learningplatform.models.dao.skills.conceptual;

import com.learningplatform.learningplatform.models.skills.conceptual.ConceptualSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ConceptualSkillDao extends CrudRepository<ConceptualSkill, Integer> {
	public List<ConceptualSkill> findAll();

	public ConceptualSkill findByUid(Integer uid);
}
