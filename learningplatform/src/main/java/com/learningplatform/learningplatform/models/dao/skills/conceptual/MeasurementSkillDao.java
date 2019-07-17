package com.learningplatform.learningplatform.models.dao.skills.conceptual;

import com.learningplatform.learningplatform.models.skills.conceptual.MeasurementSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MeasurementSkillDao extends CrudRepository<MeasurementSkill, Integer> {
	public List<MeasurementSkill> findAll();

	public MeasurementSkill findByUid(Integer uid);
}
