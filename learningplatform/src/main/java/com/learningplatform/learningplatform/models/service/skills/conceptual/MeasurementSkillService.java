package com.learningplatform.learningplatform.models.service.skills.conceptual;

import com.learningplatform.learningplatform.models.dao.skills.conceptual.MeasurementSkillDao;
import com.learningplatform.learningplatform.models.service.ModelServiceSaveOnly;
import com.learningplatform.learningplatform.models.skills.conceptual.MeasurementSkill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementSkillService implements ModelServiceSaveOnly<MeasurementSkill> {

	@Autowired
	private MeasurementSkillDao measurementSkillDao;

	@Override
	public Boolean save(MeasurementSkill measurementSkill) {
		return measurementSkillDao.save(measurementSkill) != null;
	}

}
