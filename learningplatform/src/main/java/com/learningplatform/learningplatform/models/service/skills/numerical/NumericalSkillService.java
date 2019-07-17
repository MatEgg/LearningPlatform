package com.learningplatform.learningplatform.models.service.skills.numerical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.numerical.NumericalSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkill;

@Service
public class NumericalSkillService implements ModelServiceSaveOnly<NumericalSkill> {

	@Autowired
	NumericalSkillDao numericalSkillDao;

	@Override
	public Boolean save(NumericalSkill numericalSkill) {
		return numericalSkillDao.save(numericalSkill) != null;
	}

}
