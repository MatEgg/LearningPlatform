package com.learningplatform.learningplatform.models.dao.skills.numerical;

import com.learningplatform.learningplatform.models.skills.numerical.DivisionSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DivisionSkillDao extends CrudRepository<DivisionSkill, Integer> {

	public List<DivisionSkill> findAll();

	public DivisionSkill findByUid(Integer uid);
}
