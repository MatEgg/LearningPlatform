package com.learningplatform.learningplatform.models.service.skills.conceptual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.conceptual.RandomnessSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.conceptual.RandomnessSkill;

@Service
public class RandomnessSkillService implements ModelServiceSaveOnly<RandomnessSkill> {

	@Autowired
	private RandomnessSkillDao randomnessSkillDao;

	@Override
	public Boolean save(RandomnessSkill randomnessSkill) {
		return randomnessSkillDao.save(randomnessSkill) != null;
	}

}
