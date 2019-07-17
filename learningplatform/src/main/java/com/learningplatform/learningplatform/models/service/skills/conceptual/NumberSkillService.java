package com.learningplatform.learningplatform.models.service.skills.conceptual;

import com.learningplatform.learningplatform.models.dao.skills.conceptual.NumberSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.conceptual.NumberSkill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberSkillService implements ModelServiceSaveOnly<NumberSkill> {

	@Autowired
	private NumberSkillDao numberSkillDao;

	@Override
	public Boolean save(NumberSkill numberSkill) {
		return numberSkillDao.save(numberSkill) != null;
	}

}
