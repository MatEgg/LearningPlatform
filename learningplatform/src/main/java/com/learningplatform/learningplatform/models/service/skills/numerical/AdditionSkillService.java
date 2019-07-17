package com.learningplatform.learningplatform.models.service.skills.numerical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.numerical.AdditionSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.numerical.AdditionSkill;

@Service
public class AdditionSkillService implements ModelServiceSaveOnly<AdditionSkill> {

	@Autowired
	private AdditionSkillDao additionSkillDao;

	@Override
	public Boolean save(AdditionSkill additionSkill) {
		return additionSkillDao.save(additionSkill) != null;
	}

}
