package com.learningplatform.learningplatform.question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.attributes.Area;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionCatalogue;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.question.QuestionHandler.QuestionType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.CalculationStep;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionCalculation;
import com.learningplatform.learningplatform.types.ResultStep;
import com.learningplatform.learningplatform.types.TargetAttribute;
import com.learningplatform.learningplatform.types.UserResult;

import learningplatform.learningplatform.calculation.CalculationStats;
import learningplatform.learningplatform.calculation.OptimalSolution;
import learningplatform.learningplatform.calculation.ValidCalculation;

public class QuestionHandlerTest {

	List<TargetAttribute> containedAttributes;
	QuestionHandler qh;
	List<Element> elements;
	Map<QuestionType, Map<QuestionAttribute, Element>> questionTypeFlags;
	QuestionCalculation questionCalculation;

	List<Attribute> attributes;

	Element element;
	EnumMap<AttributeType, Attribute> map;
	Map<Integer, Element> elementIdMap;
	Store store;

	TargetAttribute attribute;
	Attribute oldAttribute;

	Attribute areaAttribute;
	Attribute lengthAttribute;
	Attribute widthAttribute;
	Attribute volumeAttribute;
	Attribute heightAttribute;
	Attribute foodReqAttribute;
	Attribute animalAttribute;
	Attribute priceAttribute;
	Attribute expensesFoodAttribute;
	Attribute expensesConstructionAttribute;
	Attribute incomeVisitors;
	Attribute populationAttribute;
	Attribute customerSpendingAttribute;

	List<Question> questions;
	List<Question> only2Questions;
	OptimalSolution optimalSolution1;
	OptimalSolution optimalSolution2;
	OptimalSolution optimalSolution3;
	OptimalSolution optimalSolution4;
	OptimalSolution optimalSolution5;

	CalculationStats calcStats1;
	CalculationStats calcStats2;
	CalculationStats calcStats3;
	CalculationStats calcStats4;
	CalculationStats calcStats5;

	Question question1;
	Question question2;
	Question question3;
	Question question4;
	Question question5;

	Map<Integer, Question> questionMap;

	List<CalculationStep> calcSteps;
	ResultStep rs;
	UserResult userResult;

	@Before
	public void setUp() throws Exception {

		store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		element = new StoreElement(store, 1);
		map = new EnumMap<>(AttributeType.class);

		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		lengthAttribute = new Attribute("test", "", 9, element, AttributeType.LENGTH);

		Map<QuestionAttribute, Element> tempMap = new HashMap<>();
		tempMap.put(QuestionAttribute.STORE, element);

		questionTypeFlags = new HashMap<>();
		questionTypeFlags.put(QuestionType.FEEDING, tempMap);

		elements = new ArrayList<>();
		elements.add(element);

		attributes = new ArrayList<>();

		element.setAttributeMap(map);

		qh = QuestionHandler.getInstance();

		// ------ Questions -------- //

		questions = new ArrayList<>();
		only2Questions = new ArrayList<>();

		question1 = new Question("test1", 10, 1, 1);
		question2 = new Question("test2", 10, 2, 2);
		question3 = new Question("test3", 10, 3, 3);
		question4 = new Question("test4", 10, 4, 4);
		question5 = new Question("test5", 10, 1, 5);

		optimalSolution1 = new OptimalSolution();
		optimalSolution2 = new OptimalSolution();
		optimalSolution3 = new OptimalSolution();
		optimalSolution4 = new OptimalSolution();
		optimalSolution5 = new OptimalSolution();

		calcStats1 = new CalculationStats();
		calcStats1.setStepsNeeded(3);
		calcStats2 = new CalculationStats();
		calcStats2.setStepsNeeded(4);
		calcStats3 = new CalculationStats();
		calcStats3.setStepsNeeded(3);
		calcStats4 = new CalculationStats();
		calcStats4.setStepsNeeded(4);
		calcStats5 = new CalculationStats();
		calcStats5.setStepsNeeded(5);

		optimalSolution1.setCalculationStats(calcStats1);
		optimalSolution1.setOptimalSolutionDetailed("test1");
		optimalSolution2.setCalculationStats(calcStats2);
		optimalSolution2.setOptimalSolutionDetailed("test2");
		optimalSolution3.setCalculationStats(calcStats3);
		optimalSolution3.setOptimalSolutionDetailed("test3");
		optimalSolution4.setCalculationStats(calcStats4);
		optimalSolution4.setOptimalSolutionDetailed("test4");
		optimalSolution5.setCalculationStats(calcStats5);
		optimalSolution5.setOptimalSolutionDetailed("test5");

		question1.setOptimalSolution(optimalSolution1);
		question2.setOptimalSolution(optimalSolution2);
		question3.setOptimalSolution(optimalSolution3);
		question4.setOptimalSolution(optimalSolution4);
		question5.setOptimalSolution(optimalSolution5);

		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);
		questions.add(question5);

		// ----------------- CalculationSteps ------------------ //

		oldAttribute = new Area(25, element, 5, 5);
		oldAttribute.setId(1);
		attribute = new TargetAttribute(1, 1, false);
		
		containedAttributes = new ArrayList<>();
		containedAttributes.add(attribute);
		
		
		rs = new ResultStep(250);
		calcSteps = new ArrayList<>();
		calcSteps.add(new CalculationStep("firstVal", 25, null));
		calcSteps.add(new CalculationStep("*", 0.0f, "*"));
		calcSteps.add(new CalculationStep("secondVal", 10, null));

		questionMap = new HashMap<>();
		questionMap.put(question1.getId(), question1);

		elementIdMap = new HashMap<>();
		elementIdMap.put(1, element);

		QuestionCatalogue qc = new QuestionCatalogue();
		qc.setQuestions(questions);
		qc.setQuestionMap(questionMap);
		qh.setQuestionCatalogue(qc);

	}

	@Test
	public void generateQuestionsTest() {
		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		lengthAttribute = new Attribute("test", "", 9, element, AttributeType.LENGTH);
		qh.setQuestionTypeFlags(questionTypeFlags);
		qh.generateQuestions(elements);
	}

	@Test
	public void calculateStepsNeededTest() {
		assertEquals(14, qh.calculateStepsNeeded());

	}

	@Test
	public void checkQuestionAnswerTest() {
		questionCalculation = new QuestionCalculation(calcSteps, rs, 1, containedAttributes, false);
		assertNotNull(qh.checkQuestionAnswer(questionCalculation, elementIdMap));
	}

}
