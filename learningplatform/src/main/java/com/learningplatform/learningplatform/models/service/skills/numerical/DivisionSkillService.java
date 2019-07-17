package com.learningplatform.learningplatform.models.service.skills.numerical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.models.dao.skills.numerical.DivisionSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.numerical.DivisionSkill;

@Service
public class DivisionSkillService implements ModelServiceSaveOnly<DivisionSkill> {

	@Autowired
	private DivisionSkillDao divisionSkillDao;

	@Override
	public Boolean save(DivisionSkill divisionSkill) {
		return divisionSkillDao.save(divisionSkill) != null;
	}

}
