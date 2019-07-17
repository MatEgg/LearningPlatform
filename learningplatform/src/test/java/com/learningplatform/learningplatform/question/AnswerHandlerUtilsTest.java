package com.learningplatform.learningplatform.question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.CalculationStep;

import learningplatform.learningplatform.Calculation.SingleCalculation;

public class AnswerHandlerUtilsTest {

	List<SingleCalculation> singleCalcs;
	List<CalculationStep> calcSteps;

	@Before
	public void setUp() throws Exception {
		singleCalcs = new ArrayList<>();
		calcSteps = new ArrayList<>();
	}

	@Test
	public void calculationWrapperAdditionTest() {

		calcSteps.add(new CalculationStep("(", 0.0f, "("));
		calcSteps.add(new CalculationStep("1", 1.0f, ""));
		calcSteps.add(new CalculationStep("+", 0.0f, "+"));
		calcSteps.add(new CalculationStep("1", 1.0f, ""));
		calcSteps.add(new CalculationStep(")", 0.0f, ")"));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("2", 2.0f, ""));

		assertEquals(4, AnswerHandlerUtils.calculationWrapper(calcSteps, singleCalcs), 0);
		assertEquals(2, singleCalcs.size());

	}

	@Test
	public void calculationWrapperMultiplicationTest() {

		calcSteps.add(new CalculationStep("(", 0.0f, "("));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		calcSteps.add(new CalculationStep(")", 0.0f, ")"));
		calcSteps.add(new CalculationStep("+", 0.0f, "+"));
		calcSteps.add(new CalculationStep("(", 0.0f, "("));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		calcSteps.add(new CalculationStep(")", 0.0f, ")"));

		assertEquals(50, AnswerHandlerUtils.calculationWrapper(calcSteps, singleCalcs), 0);
		assertEquals(3, singleCalcs.size());

	}

	@Test
	public void calculationWrapperNegativeTest() {

		calcSteps.add(new CalculationStep("(", 0.0f, "("));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		calcSteps.add(new CalculationStep(")", 0.0f, ")"));
		calcSteps.add(new CalculationStep("-", 0.0f, "-"));
		calcSteps.add(new CalculationStep("100", 100.0f, ""));

		assertEquals(-75, AnswerHandlerUtils.calculationWrapper(calcSteps, singleCalcs), 0);
		assertEquals(2, singleCalcs.size());

	}

	@Test
	public void calculationWrapperWrongTest() {

		calcSteps.add(new CalculationStep("(", 0.0f, "("));
		calcSteps.add(new CalculationStep("5", 5.0f, ""));
		try {
			AnswerHandlerUtils.calculationWrapper(calcSteps, singleCalcs);
		} catch (Exception e) {
			return;
		}

	}

}
