package com.learningplatform.learningplatform.models.dao.skills.conceptual;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learningplatform.learningplatform.models.skills.conceptual.GeneralPerformanceSkill;

@Repository
@Transactional
public interface GeneralPerformanceSkillDao extends CrudRepository<GeneralPerformanceSkill, Integer> {

	public List<GeneralPerformanceSkill> findAll();

	public GeneralPerformanceSkill findByUid(Integer uid);
}
