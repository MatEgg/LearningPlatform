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
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.calculation.MostDifficultyCalculation;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BktHandlerOperationalTest {

	BktUpdateOperational bktUpdateOperational;
	BktTypeOperational bktTypeOperational;
	BktPotentialOperationalUpdate bktPotentialOperationalUpdate;
	BktHandler bktHandler;
	MostDifficultyCalculation mostDiffCalc;
	User user;

	@Autowired
	DaoFactory daoFactory;

	@Before
	public void setUp() throws Exception {
		user = daoFactory.getUserService().getUserByID(1251);
		bktHandler = BktHandler.getInstanceNewDao(daoFactory, 1251);

	}

	@Test
	public void additionTest() {
		mostDiffCalc = new MostDifficultyCalculation(1, 5, 5, Operators.ADDITION);
		bktPotentialOperationalUpdate = new BktPotentialOperationalUpdate(true, mostDiffCalc);
		assertEquals(209, bktHandler.updateSkillMasteryOperational(bktPotentialOperationalUpdate).getNumericalSkill()
				.getAdditionSkill().getNumericalSkillTypes().getBigNumberHandling());

	}
	
	@Test
	public void subtractionTest() {
		mostDiffCalc = new MostDifficultyCalculation(1, 5, 5, Operators.SUBSTRACTION);
		bktPotentialOperationalUpdate = new BktPotentialOperationalUpdate(true, mostDiffCalc);
		assertEquals(209, bktHandler.updateSkillMasteryOperational(bktPotentialOperationalUpdate).getNumericalSkill()
				.getSubtractionSkill().getNumericalSkillTypes().getBigNumberHandling());

	}
	
	@Test
	public void multiplicationTest() {
		mostDiffCalc = new MostDifficultyCalculation(1, 5, 5, Operators.MULTIPLICATION);
		bktPotentialOperationalUpdate = new BktPotentialOperationalUpdate(true, mostDiffCalc);
		assertEquals(209, bktHandler.updateSkillMasteryOperational(bktPotentialOperationalUpdate).getNumericalSkill()
				.getMultiplicationSkill().getNumericalSkillTypes().getBigNumberHandling());

	}
	
	@Test
	public void divisionTest() {
		mostDiffCalc = new MostDifficultyCalculation(1, 5, 5, Operators.DIVISION);
		bktPotentialOperationalUpdate = new BktPotentialOperationalUpdate(true, mostDiffCalc);
		assertEquals(330, bktHandler.updateSkillMasteryOperational(bktPotentialOperationalUpdate).getNumericalSkill()
				.getDivisionSkill().getNumericalSkillTypes().getBigNumberHandling());

	}

}
