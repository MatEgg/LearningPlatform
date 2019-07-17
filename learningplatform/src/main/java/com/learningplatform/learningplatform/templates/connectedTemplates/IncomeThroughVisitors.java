package com.learningplatform.learningplatform.templates.connectedTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.CityElement;
import com.learningplatform.learningplatform.templates.Elements.CustomerElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;

import learningplatform.learningplatform.calculation.OptimalSolution;
import learningplatform.learningplatform.calculation.ValidCalculation;

public class IncomeThroughVisitors extends PercentConnectedTemplate {

	ListMultimap<QuestionAttribute, Element> elementsMap;
	List<Attribute> attributes;
	StoreElement storeElement;
	CityElement cityElement;
	CustomerElement customerElement;
	int level = 1;

	private float spentPerVisitor = 0;
	private float percentageOfVisitorsSpending = 0;
	private float taxes = 0;
	private float payMorePercentage = 0;
	private float payingHowMuchMore = 0;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);
	}

	public IncomeThroughVisitors(WordProblem wordProblem) {
		super(wordProblem);
		elementsMap = wordProblem.getElementsMap();
		cityElement = new CityElement(this);
		customerElement = new CustomerElement(this);
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		setRequiredAttr();
	}
	
	public IncomeThroughVisitors() {
		super();
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		setRequiredAttr();
	}

	@Override
	public void callFinishTemplate() {

		EnumMap<QuestionAttribute, Element> incomeAttributes = new EnumMap<>(QuestionAttribute.class);
		incomeAttributes.put(QuestionAttribute.CITY, cityElement);
		incomeAttributes.put(QuestionAttribute.CUSTOMER, customerElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.INCOMEVISITORS,
				incomeAttributes);

		cityElement.getAttributeByType(AttributeType.POPULATION).setIsDistractor(false);
		storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);

		spentPerVisitor = DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(
				DifficultyOverviewHelper.MONEY_SPENT_PER_VISITOR_UPPER, Operators.MULTIPLICATION);

		payingHowMuchMore = 2;

// Find values for how many people spend money, how much the taxes are and how much percent of the people pay more than the others.

		percentageOfVisitorsSpending = DifficultyAdjustmentUtils.getPercentageMultiplierBasedOnDifficulty(
				Operators.MULTIPLICATION,
				DifficultyOverviewHelper.PERCENTAGE_OF_PAYING_VISITORS_LOWER,
				DifficultyOverviewHelper.PERCENTAGE_OF_PAYING_VISITORS_UPPER) / 100;

		taxes = DifficultyAdjustmentUtils.getPercentageMultiplierBasedOnDifficulty(Operators.MULTIPLICATION, DifficultyOverviewHelper.TAXES_LOWER, DifficultyOverviewHelper.TAXES_UPPER)
				/ 100;

		payMorePercentage = DifficultyAdjustmentUtils.getPercentageMultiplierBasedOnDifficulty(
				Operators.MULTIPLICATION,
				DifficultyOverviewHelper.PERCENTAGE_OF_PAYING_MORE_LOWER,
				DifficultyOverviewHelper.PERCENTAGE_OF_PAYING_MORE_UPPER) / 100;

		Attribute customerSpending = null;

		customerElement.setSpentPerVisitor(spentPerVisitor);
		customerElement.setPercentageOfVisitorsSpending(percentageOfVisitorsSpending);
		// If template is higher than level 1, there are taxes to pay
		if (level > 1) {
			customerElement.setTaxes(taxes);
		}
		customerElement.getPayMorePercentage();
		customerElement.setPayingHowMuchMore(payingHowMuchMore);

		float averageCustomerSpending = spentPerVisitor;

		// If template is on level 3, the most complicated template is used

		if (level == 3) {
			averageCustomerSpending = (payMorePercentage * (spentPerVisitor * payingHowMuchMore))
					+ ((1 - payMorePercentage) * spentPerVisitor);
			customerElement.setSpentPerVisitor(averageCustomerSpending);
			customerSpending = customerElement.getAttributeByType(AttributeType.CUSTOMERSPENDING);

			customerSpending.setNumericalValue(averageCustomerSpending);
			customerSpending.setIsVisible(false);
			Float[] values = new Float[] { payMorePercentage, spentPerVisitor, payingHowMuchMore };

			customerSpending.getValidCalculations()
					.add(new ValidCalculation(values, Formula.CUSTOMERSPENDING, Operators.MULTIPLICATION));
			customerSpending.getAttributeCalculation().getCalculationInformation()
					.setAttributeConcept(AttributeConcept.PERCENTAGE_CALCULATION_SKILL);
			customerSpending.getAttributeCalculation()
					.setCorrectRoundedAnswer(QuestionTextHelper.roundNumber(averageCustomerSpending, 2));

			StringBuilder sb = new StringBuilder();
			sb.append(payMorePercentage);
			sb.append(" * ");
			sb.append(spentPerVisitor * payingHowMuchMore);
			sb.append(" + (1 - ");
			sb.append(payMorePercentage);
			sb.append(") ");
			sb.append(" * ");
			sb.append(spentPerVisitor);

			OptimalSolution optimalSolution = new OptimalSolution(customerSpending.getAttributeCalculation());

			optimalSolution.setOptimalSolutionText(sb.toString());
			customerSpending.setManualHelp(true);
			customerSpending.setOptimalSolution(optimalSolution);

		} else {

			customerElement.getAttributeByType(AttributeType.CUSTOMERSPENDING)
					.setNumericalValue(averageCustomerSpending);

			customerElement.getAttributeByType(AttributeType.CUSTOMERSPENDING).setIsDistractor(false);
		}

		finishTemplate();

	}

	@Override
	public void setQuestionElements() {
		List<Element> questionElements = new ArrayList<>();
		questionElements.add(cityElement);
		questionElements.add(customerElement);
		addQuestionElements(questionElements);
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}



	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.INCOME_VISITORS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.learningplatform.learningplatform.templates.Template#constructQuestion()
	 * 
	 * Based on the level of the template, more difficulty and more complex
	 * templates are used.
	 */
	@Override
	public QuestionText constructQuestion() {

		questionText = new QuestionText();

		questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation((int) (percentageOfVisitorsSpending * 100)));
		questionText.addTextLine("% der Einwohner in ");
		questionText.addTextLine(cityElement.getElementText());
		questionText.addTextLine(" besuchen ");
		questionText.addTextLine(storeElement.getTextMiddle());
		questionText.addTextLine(" jedes Jahr. ");

		if (level == 1) {
			questionText.addTextLine(" Ein Besucher gibt im Durchschnitt ");
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(spentPerVisitor));
			questionText.addTextLine(" Euro aus. ");

		}
		if (level == 2) {
			questionText.addTextLine(" Ein Besucher gibt im Durchschnitt ");
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(spentPerVisitor));
			questionText.addTextLine(" Euro aus. ");
			questionText.addTextLine(" Auf die Einnahmen müssen ");
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation((int) (taxes * 100)));
			questionText.addTextLine(" % Steuern gezahlt werden. ");
		}

		if (level == 3) {
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation((int) (payMorePercentage * 100)));
			questionText.addTextLine("% der Besucher geben im Durchschnitt ");
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(spentPerVisitor * payingHowMuchMore));
			questionText.addTextLine(" Euro aus. Die restlichen Besucher zahlen im Durchschnitt ");
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(spentPerVisitor));
			questionText.addTextLine(" Euro. Auf die Einnahmen müssen ");
			questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation((int) (taxes * 100)));
			questionText.addTextLine("% Steuern gezahlt werden. ");

		}
		return questionText;
	}

	public float getSpentPerVisitor() {
		return spentPerVisitor;
	}

	public void setSpentPerVisitor(float spentPerVisitor) {
		this.spentPerVisitor = spentPerVisitor;
	}

	public float getPercentageOfVisitorsSpending() {
		return percentageOfVisitorsSpending;
	}

	public void setPercentageOfVisitorsSpending(float percentageOfVisitorsSpending) {
		this.percentageOfVisitorsSpending = percentageOfVisitorsSpending;
	}

	public float getTaxes() {
		return taxes;
	}

	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static EnumMap<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}

}
