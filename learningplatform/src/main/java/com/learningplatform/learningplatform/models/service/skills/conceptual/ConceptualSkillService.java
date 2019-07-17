package com.learningplatform.learningplatform.models.service.skills.conceptual;

import com.learningplatform.learningplatform.models.dao.skills.conceptual.ConceptualSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.conceptual.ConceptualSkill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptualSkillService implements ModelServiceSaveOnly<ConceptualSkill> {

	@Autowired
	private ConceptualSkillDao conceptualSkillDao;

	@Override
	public Boolean save(ConceptualSkill conceptualSkill) {
		return conceptualSkillDao.save(conceptualSkill) != null;
	}

}
