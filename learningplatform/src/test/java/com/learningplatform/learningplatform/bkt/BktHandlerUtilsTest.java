package com.learningplatform.learningplatform.bkt;

import static org.junit.Assert.*;

import org.junit.Test;

import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.Calculation.MostDifficultyCalculation;

public class BktHandlerUtilsTest {

	BktUpdateConceptual bktUpdateConceptual;
	BktUpdateOperational bktUpdateOperational;
	BktTypeConceptual bktTypeConceptual;
	BktHandler bktHandler;
	BktPotentialOperationalUpdate bktPotentialOperationalUpdate;
	MostDifficultyCalculation mostDifficultyCalculation;

	static final float TRANSIT = 0.09f;
	static final float SLIP = 0.09f;
	static final float GUESS = 0.14f;

	@Test
	public void checkIfLevelHigherTest() {

		mostDifficultyCalculation = new MostDifficultyCalculation(4, 4, 4, Operators.ADDITION);
		bktPotentialOperationalUpdate = new BktPotentialOperationalUpdate(true, mostDifficultyCalculation);

		bktUpdateOperational = BktHandlerUtils.checkIfLevelHigher(1, 10, 4, bktPotentialOperationalUpdate);
		assertFalse(bktUpdateOperational.isBigNumber());
		assertTrue(bktUpdateOperational.isDecimal());
		assertTrue(bktUpdateOperational.isNumericalType());

	}

	@Test
	public void extractLevelTest() {
		assertEquals(BktHandlerUtils.extractLevel(0), 1);
		assertEquals(BktHandlerUtils.extractLevel(452), 4);
		assertEquals(BktHandlerUtils.extractLevel(1000), 10);
		assertEquals(BktHandlerUtils.extractLevel(4000), 10);

	}

	@Test
	public void calculateNewValueTest() {

		assertEquals(530, BktHandlerUtils.calculateNewValue(4, 75, 10, 4, true));
		assertEquals(475, BktHandlerUtils.calculateNewValue(4, 75, 10, 1, true));
		assertEquals(490, BktHandlerUtils.calculateNewValue(4, 90, 10, 1, true));
		assertEquals(530, BktHandlerUtils.calculateNewValue(4, 99, 10, 1, true));

	}

	@Test
	public void getLevelUpLimitTest() {

		assertEquals(98, BktHandlerUtils.getLevelUpLimit(-1));
		assertEquals(98, BktHandlerUtils.getLevelUpLimit(0));
		assertEquals(98, BktHandlerUtils.getLevelUpLimit(1));
		assertEquals(90, BktHandlerUtils.getLevelUpLimit(2));
		assertEquals(80, BktHandlerUtils.getLevelUpLimit(3));
		assertEquals(70, BktHandlerUtils.getLevelUpLimit(4));
		assertEquals(70, BktHandlerUtils.getLevelUpLimit(5));
		assertEquals(70, BktHandlerUtils.getLevelUpLimit(100));

	}

	@Test
	public void getLevelDownLimitTest() {

		assertEquals(11, BktHandlerUtils.getLevelDownLimit(-1));
		assertEquals(11, BktHandlerUtils.getLevelDownLimit(0));
		assertEquals(11, BktHandlerUtils.getLevelDownLimit(1));
		assertEquals(15, BktHandlerUtils.getLevelDownLimit(2));
		assertEquals(20, BktHandlerUtils.getLevelDownLimit(3));
		assertEquals(25, BktHandlerUtils.getLevelDownLimit(4));
		assertEquals(25, BktHandlerUtils.getLevelDownLimit(5));
		assertEquals(25, BktHandlerUtils.getLevelDownLimit(100));

	}

	@Test
	public void updateMasteryTest() {

		assertEquals(87, BktHandlerUtils.updateMastery(50, true, SLIP, GUESS, TRANSIT));
		assertEquals(97, BktHandlerUtils.updateMastery(87, true, SLIP, GUESS, TRANSIT));
		assertEquals(99, BktHandlerUtils.updateMastery(97, true, SLIP, GUESS, TRANSIT));
		assertEquals(91, BktHandlerUtils.updateMastery(99, false, SLIP, GUESS, TRANSIT));
		assertEquals(55, BktHandlerUtils.updateMastery(91, false, SLIP, GUESS, TRANSIT));
		assertEquals(89, BktHandlerUtils.updateMastery(55, true, SLIP, GUESS, TRANSIT));
		assertEquals(50, BktHandlerUtils.updateMastery(89, false, SLIP, GUESS, TRANSIT));

	}

}
