package com.learningplatform.learningplatform.models.service.skills.conceptual;

import com.learningplatform.learningplatform.models.dao.skills.conceptual.GeneralPerformanceSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.conceptual.GeneralPerformanceSkill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralPerformanceSkillService implements ModelServiceSaveOnly<GeneralPerformanceSkill> {

	@Autowired
	private GeneralPerformanceSkillDao generalPerformanceSkillDao;


	@Override
	public Boolean save(GeneralPerformanceSkill generalPerformanceSkill) {
		return generalPerformanceSkillDao.save(generalPerformanceSkill) != null;
	}

}
