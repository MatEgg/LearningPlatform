package com.learningplatform.learningplatform.bkt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.bkt.BktSingleCalculation;

import learningplatform.learningplatform.calculation.SingleCalculation;

public class BktSingleCalculationTest {

	@Before
	public void setUp() throws Exception {
	}

	public void checkDecimalLevelTest() {
		float firstValue = 17.3f;
		float secondValue = 17.423f;
		Float[] firstAndSecondVal = new Float[] { firstValue, secondValue };

		SingleCalculation singleCalc = new SingleCalculation(firstValue, secondValue, "+");
		BktSingleCalculation bktSingleCalculation = new BktSingleCalculation(singleCalc);
		assertEquals(4, bktSingleCalculation.checkDecimalLevel(firstAndSecondVal), 0);

	}
	
	@Test
	public void checkDecimalLevelTest2() {
		float firstValue = 0.1f;
		float secondValue = 10;
		Float[] firstAndSecondVal = new Float[] { firstValue, secondValue };

		SingleCalculation singleCalc = new SingleCalculation(firstValue, secondValue, "+");
		BktSingleCalculation bktSingleCalculation = new BktSingleCalculation(singleCalc);
		assertEquals(2, bktSingleCalculation.checkDecimalLevel(firstAndSecondVal), 0);

	}


	@Test
	public void checkBigDigitLevelTest() {
		float firstValue = 17f;
		float secondValue = 123f;
		Float[] firstAndSecondVal = new Float[] { firstValue, secondValue };

		SingleCalculation singleCalc = new SingleCalculation(firstValue, secondValue, "+");
		BktSingleCalculation bktSingleCalculation = new BktSingleCalculation(singleCalc);
		assertEquals(10, bktSingleCalculation.checkBigDigitLevel(firstAndSecondVal), 0);

	}

	@Test
	public void checkNumericalLevelTest() {
		float firstValue = 2f;
		float secondValue = 47f;
		Float[] firstAndSecondVal = new Float[] { firstValue, secondValue };

		SingleCalculation singleCalc = new SingleCalculation(firstValue, secondValue, "+");
		BktSingleCalculation bktSingleCalculation = new BktSingleCalculation(singleCalc);
		assertEquals(4, bktSingleCalculation.checkNumericalLevel(firstAndSecondVal), 0);

	}

}
