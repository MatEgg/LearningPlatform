package com.learningplatform.learningplatform.models.service.skills.conceptual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.conceptual.SolvingTaskSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.conceptual.SolvingTaskSkill;

@Service
public class SolvingTaskSkillService implements ModelServiceSaveOnly<SolvingTaskSkill> {

	@Autowired
	private SolvingTaskSkillDao solvingTaskSkillDao;

	@Override
	public Boolean save(SolvingTaskSkill solvingTaskSkill) {
		return solvingTaskSkillDao.save(solvingTaskSkill) != null;
	}

}
