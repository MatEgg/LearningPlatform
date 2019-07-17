package com.learningplatform.learningplatform.models.service.skills.numerical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.numerical.MultiplicationSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.numerical.MultiplicationSkill;

@Service
public class MultiplicationSkillService implements ModelServiceSaveOnly<MultiplicationSkill> {

	@Autowired
	private MultiplicationSkillDao multiplicationSkillDao;

	@Override
	public Boolean save(MultiplicationSkill model) {
		return multiplicationSkillDao.save(model) != null;
	}

}
