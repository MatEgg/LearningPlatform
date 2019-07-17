package com.learningplatform.learningplatform.models.dao.skills.numerical;

import com.learningplatform.learningplatform.models.skills.numerical.AdditionSkill;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AdditionSkillDao extends CrudRepository<AdditionSkill, Integer> {

	public List<AdditionSkill> findAll();

	public AdditionSkill findByUid(Integer uid);
}
