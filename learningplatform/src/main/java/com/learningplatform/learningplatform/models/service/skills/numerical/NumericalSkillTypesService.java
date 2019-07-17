package com.learningplatform.learningplatform.models.service.skills.numerical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.numerical.NumericalSkillTypesDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkillTypes;

@Service
public class NumericalSkillTypesService implements ModelServiceSaveOnly<NumericalSkillTypes> {

	@Autowired
	NumericalSkillTypesDao numericalSkillTypesDao;

	@Override
	public Boolean save(NumericalSkillTypes numericalSkillTypes) {
		return numericalSkillTypesDao.save(numericalSkillTypes) != null;
	}

}
