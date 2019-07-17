package com.learningplatform.learningplatform.bkt;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.models.User;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BktHandlerConceptualTest {

	BktUpdateConceptual bktUpdateConceptual;
	BktTypeConceptual bktTypeConceptual;
	BktHandler bktHandler;
	User user;

	@Autowired
	DaoFactory daoFactory;

	@Before
	public void setUp() throws Exception {
		user = daoFactory.getUserService().getUserByID(1251);
		bktHandler = BktHandler.getInstanceNewDao(daoFactory, 1251);

	}

	@Test
	public void compRulesTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.COMPUTATIONAL_RULES_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill()
				.getNumberSkill().getComputationalRulesSkill());

	}

	@Test
	public void areaCalcTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.AREA_CALCULATION_SKILL, true);
		assertEquals(309, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill()
				.getMeasurementSkill().getAreaCalculationSkill());

	}

	@Test
	public void probabilitiesCalcTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.CALCULATE_PROBABILITIES_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill().getRandomnessSkill().getCalculateProbabilitiesSkill());

	}
	
	@Test
	public void correctFormulasCalcTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.CHOOSE_CORRECT_FORMULAS_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill().getSolvingTaskSkill().getChooseCorrectFormulasSkill());

	}
	
	
	@Test
	public void correctValuesCalcTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.INSERT_CORRECT_VALUES_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill().getSolvingTaskSkill().getInsertCorrectValuesSkill());

	}
	
	@Test
	public void correctInformationTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.CHOOSE_CORRECT_INFORMATION_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill().getSolvingTaskSkill().getChooseCorrectInformationSkill());

	}
	
	
	@Test
	public void solvingCarryTasksTest() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.SOLVING_CARRY_TASKS_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill().getGeneralPerformanceSkill().getSolvingCarryTasksSkill());

	}
	

	@Test
	public void stepsNeededTask() {
		bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.STEPS_NEEDED_SKILL, true);
		assertEquals(209, bktHandler.updateSkillMasteryConceptual(bktUpdateConceptual).getConceptualSkill().getGeneralPerformanceSkill().getStepsNeededSkill());

	}

}
