package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.dao.difficulty.DifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.concept.ConceptDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.conceptual.ConceptualDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.AdditionDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.DivisionDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.MultiplicationDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.NumericalSkillsTypesDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.OperationalDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.SubtractionDifficultyDao;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.AdditionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.DivisionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.MultiplicationDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.NumericalSkillTypesDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.OperationalDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.SubtractionDifficultyModel;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving difficulty models.
 *
 */
@Service
public class DifficultyService implements ModelServiceSaveOnly<Difficulty> {
	@Autowired
	DifficultyDao difficultyDao;

	@Autowired
	AdditionDifficultyDao additionDifficultyDao;

	@Autowired
	SubtractionDifficultyDao subtractionDifficultyDao;

	@Autowired
	MultiplicationDifficultyDao multiplicationDifficultyDao;

	@Autowired
	DivisionDifficultyDao divisionDifficultyDao;

	@Autowired
	NumericalSkillsTypesDifficultyDao numericalSkillsTypesDifficultyDao;

	@Autowired
	OperationalDifficultyDao operationalDifficultyDao;

	@Autowired
	ConceptDifficultyDao conceptDifficultyDao;

	@Autowired
	ConceptualDifficultyDao conceptualDifficultyDao;

	public List<Difficulty> getAllDifficulty() {
		return difficultyDao.findAll();
	}

	public Difficulty getUserByID(int id) {
		return difficultyDao.findByUid(id);
	}

	@Override
	public Boolean save(Difficulty difficulty) {

		OperationalDifficultyModel operationalDifficultyModel = difficulty.getOperationalDifficultyModel();

		AdditionDifficultyModel additionDifficultyModel = operationalDifficultyModel.getAdditionDifficultyModel();
		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelAddition = additionDifficultyModel
				.getNumericalSkillTypesDifficultyModel();

		SubtractionDifficultyModel subtractionDifficultyModel = operationalDifficultyModel
				.getSubtractionDifficultyModel();
		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelSubtraction = subtractionDifficultyModel
				.getNumericalSkillTypesDifficultyModel();

		MultiplicationDifficultyModel multiplicationDifficultyModel = operationalDifficultyModel
				.getMultiplicationDifficultyModel();
		NumericalSkillTypesDifficultyModel numericalSkillTypeDifficultyModelMultiplication = multiplicationDifficultyModel
				.getNumericalSkillTypesDifficultyModel();

		DivisionDifficultyModel divisionDifficultyModel = operationalDifficultyModel.getDivisionDifficultyModel();
		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelDivision = divisionDifficultyModel
				.getNumericalSkillTypesDifficultyModel();

		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelAddition);
		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelSubtraction);
		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelDivision);
		numericalSkillsTypesDifficultyDao.save(numericalSkillTypeDifficultyModelMultiplication);

		additionDifficultyDao.save(additionDifficultyModel);
		subtractionDifficultyDao.save(subtractionDifficultyModel);
		multiplicationDifficultyDao.save(multiplicationDifficultyModel);
		divisionDifficultyDao.save(divisionDifficultyModel);

		ConceptDifficultyModel conceptDifficultyModel = difficulty.getConceptDifficultyModel();
		ConceptualDifficultyModel conceptualDifficultyModel = difficulty.getConceptualDifficultyModel();

		conceptDifficultyDao.save(conceptDifficultyModel);
		conceptualDifficultyDao.save(conceptualDifficultyModel);
		operationalDifficultyDao.save(operationalDifficultyModel);

		if (difficultyDao.findByUid(difficulty.getUid()) != null) {
			Difficulty difficultyTemp = difficultyDao.findByUid(difficulty.getUid());
			difficultyTemp.setConceptDifficultyModel(conceptDifficultyModel);
			difficultyTemp.setConceptualDifficultyModel(conceptualDifficultyModel);
			difficultyTemp.setOperationalDifficultyModel(operationalDifficultyModel);

			return difficultyDao.save(difficultyTemp) != null;
		}

		return difficultyDao.save(difficulty) != null;

	}
}
