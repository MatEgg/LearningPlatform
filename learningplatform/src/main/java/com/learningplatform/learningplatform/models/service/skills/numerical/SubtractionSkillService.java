package com.learningplatform.learningplatform.models.service.skills.numerical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.numerical.SubtractionSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.numerical.SubtractionSkill;

@Service
public class SubtractionSkillService implements ModelServiceSaveOnly<SubtractionSkill> {

	@Autowired
	private SubtractionSkillDao subtractionSkillDao;

	@Override
	public Boolean save(SubtractionSkill subtractionSkill) {
		return subtractionSkillDao.save(subtractionSkill) != null;
	}

}
