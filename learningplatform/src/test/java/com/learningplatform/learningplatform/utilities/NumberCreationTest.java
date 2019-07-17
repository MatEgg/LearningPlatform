package com.learningplatform.learningplatform.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.difficulty.DifficultyCalculated;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.AdditionDifficulty;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DecimalCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DigitCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.NumberTypeDifficultyCost;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class NumberCreationTest {

	DifficultyCalculated difficultyCalculated;
	NumberTypeDifficultyCost numberType;
	DigitCountDifficultyCost digitCount;
	DecimalCountDifficultyCost decimalCount;

	@Before
	public void setUp() throws Exception {

		difficultyCalculated = new DifficultyCalculated();
	}

	@Test
	public void createNumberWithParametersLowTest() {

		numberType = new NumberTypeDifficultyCost(50);
		digitCount = new DigitCountDifficultyCost(50);
		decimalCount = new DecimalCountDifficultyCost(50);
		numberType.setLevel(1);
		digitCount.setLevel(1);
		decimalCount.setLevel(1);

		float result = NumberCreation.determineEasyToCalculateNumbers(100, numberType, digitCount, decimalCount, false,
				10, 100);
		assertEquals(1, result, 10);

	}
	
	@Test
	public void createNumberWithParametersMiddleTest() {

		numberType = new NumberTypeDifficultyCost(50);
		digitCount = new DigitCountDifficultyCost(50);
		decimalCount = new DecimalCountDifficultyCost(50);
		numberType.setLevel(3);
		digitCount.setLevel(3);
		decimalCount.setLevel(1);

		float result = NumberCreation.determineEasyToCalculateNumbers(100, numberType, digitCount, decimalCount, false,
				20, 100);
		boolean remainder = result % 2 == 0; 
		assertTrue(result < 10 && remainder);

	}
	
	@Test
	public void createNumberWithParametersHighTest() {

		numberType = new NumberTypeDifficultyCost(50);
		digitCount = new DigitCountDifficultyCost(50);
		decimalCount = new DecimalCountDifficultyCost(50);
		numberType.setLevel(4);
		digitCount.setLevel(8);
		decimalCount.setLevel(1);

		float result = NumberCreation.determineEasyToCalculateNumbers(100, numberType, digitCount, decimalCount, false,
				10, 100);
		boolean remainder = (result % 2 != 0) && (result % 5 != 0);
		assertTrue(result > 40 && result < 50 && remainder);

	}
	
	
	@Test
	public void createNumberWithParametersMaxTest() {

		numberType = new NumberTypeDifficultyCost(50);
		digitCount = new DigitCountDifficultyCost(50);
		decimalCount = new DecimalCountDifficultyCost(50);
		numberType.setLevel(4);
		digitCount.setLevel(10);
		decimalCount.setLevel(1);

		float result = NumberCreation.determineEasyToCalculateNumbers(999, numberType, digitCount, decimalCount, false,
				10, 1000);
		boolean remainder = (result % 2 != 0) && (result % 5 != 0);
		assertTrue(result > 100 && remainder);
		
	}
	
	@Test
	public void createNumberWithParametersDecimalTest() {

		numberType = new NumberTypeDifficultyCost(50);
		digitCount = new DigitCountDifficultyCost(50);
		decimalCount = new DecimalCountDifficultyCost(50);
		numberType.setLevel(1);
		digitCount.setLevel(10);
		decimalCount.setLevel(4);

		float result = NumberCreation.determineEasyToCalculateNumbers(999, numberType, digitCount, decimalCount, true,
				1, 100);
		boolean remainder = (result % 2 != 0) && (result % 5 != 0);
		assertTrue(result > 0 && result < 100 && remainder);
		
	}

}
