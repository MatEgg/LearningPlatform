package com.learningplatform.learningplatform.Calculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.attributes.Area;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.question.AnswerHandler;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.AttributeCalculationType;
import com.learningplatform.learningplatform.types.CalculationStep;
import com.learningplatform.learningplatform.types.QuestionCalculation;
import com.learningplatform.learningplatform.types.ResultStep;
import com.learningplatform.learningplatform.types.TargetAttribute;
import com.learningplatform.learningplatform.types.UserResult;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.Calculation.AttributeCalculation;
import learningplatform.learningplatform.Calculation.SingleCalculation;
import learningplatform.learningplatform.Calculation.ValidCalculation;

public class AnswerHandlerTest {

	AnswerHandler ah;
	AttributeCalculationType attributeCalc;
	QuestionCalculation questionCalc;
	TargetAttribute attribute;
	Attribute oldAttribute;
	List<CalculationStep> calcSteps;
	List<TargetAttribute> containedAttributes;
	ResultStep rs;
	UserResult userResult;

	Element element;
	Store store;

	@Before
	public void setUp() throws Exception {
		ah = AnswerHandler.getInstance();
		store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		element = new StoreElement(store, 1);
		calcSteps = new ArrayList<>();

	}

	@Test
	public void checkCorrectAttributeCalculationTest() {
		oldAttribute = new Area(25, element, 5, 5);
		oldAttribute.setId(1);
		attribute = new TargetAttribute(1, 1, false);
		rs = new ResultStep(250);
		calcSteps.add(new CalculationStep("firstVal", 25, null));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("secondVal", 10, null));

		attributeCalc = new AttributeCalculationType(calcSteps, rs, attribute, containedAttributes);
		userResult = ah.checkCorrectAttributeCalculation(attributeCalc);
		assertEquals(250, userResult.getUserCalculationResult(), 0);
		assertTrue(userResult.isCorrect());
	}

	@Test
	public void checkCorrectQuestionCalculationTest() {

		rs = new ResultStep(250);
		calcSteps.add(new CalculationStep("firstVal", 25, null));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("secondVal", 10, null));
		questionCalc = new QuestionCalculation(calcSteps, rs, 1, containedAttributes, false);
		
		userResult = ah.checkCorrectQuestionCalculation(questionCalc);

		assertEquals(250, userResult.getUserCalculationResult(), 0);
		assertTrue(userResult.isCorrect());

	}

}
