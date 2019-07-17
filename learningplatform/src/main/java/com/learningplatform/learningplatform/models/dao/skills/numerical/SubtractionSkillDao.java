package com.learningplatform.learningplatform.models.dao.skills.numerical;

import com.learningplatform.learningplatform.models.skills.numerical.SubtractionSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SubtractionSkillDao extends CrudRepository<SubtractionSkill, Integer> {
	public List<SubtractionSkill> findAll();

	public SubtractionSkill findByUid(Integer uid);
}
