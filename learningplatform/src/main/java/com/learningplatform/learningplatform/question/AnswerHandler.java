package com.learningplatform.learningplatform.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.SystemPropertyUtils;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.FormularUtils;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.AttributeCalculationType;
import com.learningplatform.learningplatform.types.CalculationStep;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.AttributeUtils;

import learningplatform.learningplatform.calculation.AttributeCalculation;
import learningplatform.learningplatform.calculation.OptimalSolution;
import learningplatform.learningplatform.calculation.SingleCalculation;

import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionCalculation;
import com.learningplatform.learningplatform.types.UserResult;

/**
 * @author Mathias
 *
 */
public class AnswerHandler {

	static AnswerHandler answerHandlerInstance;

	/**
	 * Lazy instantiation
	 * 
	 * @return Answer Handler instance
	 */
	public static AnswerHandler getInstance() {
		if (answerHandlerInstance == null) {
			answerHandlerInstance = new AnswerHandler();
			return answerHandlerInstance;
		} else {
			return answerHandlerInstance;
		}
	}

	/**
	 * Checks if the value of the result of the student and the attribute value is
	 * the same
	 * 
	 * @param targetAttribute   The attribute to be compared
	 * @param attributeCalcType holds information about the calculation
	 * @return boolean if answer is correct
	 */
	public boolean checkCorrectAttributeAnswer(Attribute targetAttribute, AttributeCalculationType attributeCalcType) {
		return targetAttribute.getNumericalValue() == (attributeCalcType.getResultStep().getResult());
	}

	/**
	 * Checks if the value of the result of the student and the attribute value is
	 * about the same (rounded).
	 * 
	 * @param attributeCalc     holds information about the attribute calculation
	 * @param attributeCalcType holds information about the student's calculation
	 * @return
	 */
	public boolean checkCorrectAttributeRoundedAnswer(AttributeCalculation attributeCalc,
			AttributeCalculationType attributeCalcType) {

		return attributeCalc.getCorrectRoundedAnswer() == (attributeCalcType.getResultStep().getResult());
	}

	/**
	 * Compares the result of the student with the value of the attribute
	 * 
	 * @param attributeCalc holds information about the calculation
	 * @return UserResult that holds information about the correctness of the
	 *         calculation
	 */
	public UserResult checkCorrectAttributeCalculation(AttributeCalculationType attributeCalc) {
		float correctValue = attributeCalc.getResultStep().getResult();
		float calculatedValue = 0;

		try {
			calculatedValue = AnswerHandlerUtils.calculationWrapper(attributeCalc.getCalculationSteps(),
					attributeCalc.getSingleCalculations());
		} catch (IndexOutOfBoundsException e) {
			calculatedValue = Integer.MAX_VALUE;
		}

		UserResult userResult = new UserResult(correctValue == calculatedValue, calculatedValue);
		return userResult;

	}

	/**
	 * Checks if the value of the result of the student and the question answer
	 * value is the same
	 * 
	 * @param question            Question that was answered
	 * @param questionCalculation holds information about the question calculation
	 * @return boolean if the answer is correct
	 */
	public boolean checkCorrectQuestionAnswer(Question question, QuestionCalculation questionCalculation) {
		return question.getResult() == questionCalculation.getResultStep().getResult();
	}

	/**
	 * Compares the result of the student with the value of the question answer
	 * 
	 * @param questionCalculation holds information about the question calculation
	 * @return UserResult that holds information about the correctness of the
	 *         question calculation
	 */
	public UserResult checkCorrectQuestionCalculation(QuestionCalculation questionCalculation) {
		float correctValue = questionCalculation.getResultStep().getResult();
		int questionID = questionCalculation.getQuestionId();
		Question question = QuestionHandler.getInstance().getQuestionCatalogue().getQuestionMap().get(questionID);
		float calculatedValue;

		if (questionCalculation.getCalculationSteps().size() == 1) {
			calculatedValue = questionCalculation.getCalculationSteps().get(0).getValue();
			question.setAttributeOnly(true);
		} else {
			calculatedValue = AnswerHandlerUtils.calculationWrapper(questionCalculation.getCalculationSteps(),
					questionCalculation.getSingleCalculations());
		}

		UserResult userResult = new UserResult(correctValue == calculatedValue, calculatedValue);
		return userResult;
	}

	public float checkCorrectQuestionComponents() {
		return 0;
	}

	/**
	 * Checks all the possible skill representations of a student that submits an
	 * attribute result, such as correctness of the result and if the correct
	 * formula was used.
	 * 
	 * @param attributeCalculation holds information about the attribute calculation
	 * @return AttributeAnswerCheck containing information about the answer check
	 */
	public AttributeAnswerCheck checkAttributeAnswer(AttributeCalculationType attributeCalculation,
			Map<Integer, Element> elementMap) {

		int elementID = attributeCalculation.getTargetAttribute().getElementId();
		int attributeID = attributeCalculation.getTargetAttribute().getId();

		Element element = elementMap.get(elementID);
		Map<Integer, Attribute> attributeMapp = element.getAttributeIdMap();
		Attribute targetAttribute = attributeMapp.get(attributeID);

		AnswerHandler answerHandler = AnswerHandler.getInstance();

		Boolean correctAnswer = answerHandler.checkCorrectAttributeAnswer(targetAttribute, attributeCalculation);
		Boolean correctRoundedAnswer = answerHandler
				.checkCorrectAttributeRoundedAnswer(targetAttribute.getAttributeCalculation(), attributeCalculation);
		UserResult userResult = answerHandler.checkCorrectAttributeCalculation(attributeCalculation);
		OptimalSolution os = new OptimalSolution(targetAttribute.getAttributeCalculation());
		AttributeAnswerCheck attributeAnswerCheck = new AttributeAnswerCheck(correctAnswer, correctRoundedAnswer,
				userResult, 0f, os, (userResult.getUserCalculationResult() == targetAttribute.getNumericalValue()));

		List<Attribute> convertedAttributes = AttributeUtils
				.convertTargetAttributes(attributeCalculation.getContainedAttributes(), elementMap);
		attributeAnswerCheck
				.setCorrectFormular(FormularUtils.checkIfFormularCorrect(targetAttribute.getValidCalculations(),
						convertedAttributes, attributeCalculation.getCalculationSteps()));

		attributeAnswerCheck.setCorrectValues(FormularUtils.checkIfCorrectValuesPresent(
				targetAttribute.getValidCalculations(), attributeCalculation.getCalculationSteps()));

		return attributeAnswerCheck;
	}

}
