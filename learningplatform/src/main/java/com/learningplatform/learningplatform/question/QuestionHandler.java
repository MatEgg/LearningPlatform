package com.learningplatform.learningplatform.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.guiObjects.QuestionGui;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.MaterialElement;
import com.learningplatform.learningplatform.templates.Elements.NewBuildingElement;
import com.learningplatform.learningplatform.templates.Elements.ProductElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.AttributeCalculationType;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionCalculation;
import com.learningplatform.learningplatform.types.UserResult;
import com.learningplatform.learningplatform.utilities.AttributeUtils;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;

import learningplatform.learningplatform.Calculation.AttributeCalculation;
import learningplatform.learningplatform.Calculation.OptimalSolution;
import learningplatform.learningplatform.Calculation.ValidCalculation;

public class QuestionHandler {

	static WordProblem wordProblem;
	QuestionCatalogue questionCatalogue;
	static QuestionHandler questionHandlerInstance;
	private Map<QuestionType, Map<QuestionAttribute, Element>> questionTypeFlags;
	private Map<StepsNeeded, Integer> stepsNeededMap;

	public Map<QuestionType, Map<QuestionAttribute, Element>> getQuestionTypeFlags() {
		return questionTypeFlags;
	}

	public void setQuestionTypeFlags(Map<QuestionType, Map<QuestionAttribute, Element>> questionTypeFlags) {
		this.questionTypeFlags = questionTypeFlags;
	}

	public enum QuestionType {
		FEEDING, COLLECTION, BASESTORE, BUYINGFOOD, BUILDING, BUYINGMATERIAL, ANIMALPERAREA, INCOMEVISITORS
	}

	public enum StepsNeeded {
		INCOME, EXPENSESFOOD, EXPENSESMATERIAL, ANIMALCOUNT, FOODREQ
	}

	public QuestionHandler() {
		questionCatalogue = new QuestionCatalogue();
		questionTypeFlags = new HashMap<>();
		stepsNeededMap = new HashMap<>();
	}

	/**
	 * Lazy instantiation
	 * 
	 * @return Question Handler instance
	 */
	public static QuestionHandler getInstance() {
		if (questionHandlerInstance == null) {
			questionHandlerInstance = getNewInstance();
		}
		return questionHandlerInstance;
	}

	public static QuestionHandler getNewInstance() {
		questionHandlerInstance = new QuestionHandler();
		return questionHandlerInstance;
	}

	/**
	 * Generates questions
	 */
	public void generateQuestions(List<Element> elements) {
		missingAttributeQuestions(elements);
		createDependentQuestions();
		questionCatalogue.fillQuestionMap();
	}

	/**
	 * Creates questions based on set question flags.
	 */
	private void createDependentQuestions() {
		fillStepsNeededMap();

		if (questionTypeFlags.containsKey(QuestionType.BUYINGFOOD)) {
			constructPayingFoodQuestion();
			constructFeedingQuestion();

		}

		if (questionTypeFlags.containsKey(QuestionType.BUYINGMATERIAL)) {
			constructPayingMaterialQuestion();
			constructBuildingQuestion();

		}

		if (questionTypeFlags.containsKey(QuestionType.BUILDING)
				|| questionTypeFlags.containsKey(QuestionType.ANIMALPERAREA)) {
			constructAllAnimalsQuestion();

		}

		if (questionTypeFlags.containsKey(QuestionType.INCOMEVISITORS)) {
			constructAllIncomeQuestion();

		}

		if ((questionTypeFlags.containsKey(QuestionType.BUYINGFOOD)
				|| questionTypeFlags.containsKey(QuestionType.BUYINGMATERIAL))
				&& questionTypeFlags.containsKey(QuestionType.INCOMEVISITORS)) {
			constructProfitQuestion();

		}

		if (questionTypeFlags.containsKey(QuestionType.BUYINGFOOD)
				&& questionTypeFlags.containsKey(QuestionType.BUYINGMATERIAL)) {
			constructAllMoneySpentQuestion();

		}
	}

	/**
	 * Generates questions based on what attributes are missing in the word problem
	 */
	private void missingAttributeQuestions(List<Element> elements) {
		List<Attribute> relevantAttributes = new ArrayList<>();
		for (Element element : elements) {
			for (Attribute attribute : element.getAttributeList()) {
				if (attribute != null && !attribute.getIsVisible()) {
					relevantAttributes.add(attribute);
				}
			}
		}
		for (Attribute attribute : relevantAttributes) {
			if (attribute.getReplacementAttribute() != null
					|| attribute.getCanBeAsked() && !attribute.getAttributeType().equals(AttributeType.ANIMALCOUNT)) {
				constructAttributeQuestion(attribute);
			}
		}
	}

	/**
	 * Constructs question based on the feeding animal aspect.
	 */
	private void constructFeedingQuestion() {
		Map<QuestionAttribute, Element> feedingMap = questionTypeFlags.get(QuestionType.FEEDING);
		ProductElement productElement = (ProductElement) feedingMap.get(QuestionAttribute.ANIMALFOOD);
		StoreElement storeElement = (StoreElement) feedingMap.get(QuestionAttribute.STORE);
		AnimalElement animalElement = (AnimalElement) feedingMap.get(QuestionAttribute.ANIMAL);

		Map<QuestionAttribute, Element> newBuildingMap = questionTypeFlags.get(QuestionType.BUILDING);
		NewBuildingElement newBuildingElement;
		Attribute annimalAttrNewBuilding = null;

		if (newBuildingMap != null) {
			newBuildingElement = (NewBuildingElement) newBuildingMap.get(QuestionAttribute.BUILD);
			annimalAttrNewBuilding = newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT);
		}

		float days = storeElement.getDaysWorthOfFood();
		storeElement.setDaysWorthOfFood(days);
		StringBuilder sb = new StringBuilder();
		sb.append("Wie viel ");
		sb.append(productElement.getElementText());
		sb.append(" muss für ");
		sb.append(QuestionTextHelper.fixFloatRepresentation(days));
		sb.append(" Tage gekauft werden?");

		float animalCount = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue();

		if (annimalAttrNewBuilding != null) {
			animalCount += annimalAttrNewBuilding.getNumericalValue();
		}
		float result = days
				* animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT).getNumericalValueText().floatValue()
				* animalCount;
		Question question = new Question(sb.toString(), result, 3);

		question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT));
		calcAttributes.add(storeElement.getAttributeByType(AttributeType.ANIMALCOUNT));

		AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes, days, Operators.MULTIPLICATION);
		OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
		int stepsNeeded = stepsNeededMap.get(StepsNeeded.ANIMALCOUNT) + stepsNeededMap.get(StepsNeeded.FOODREQ) + 1;
		optimalSolution.getCalculationStats().setStepsNeeded(stepsNeeded);
		question.setOptimalSolution(optimalSolution);

		Float[] values = new Float[] { days,
				animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT).getNumericalValue(),
				storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue() };
		question.getValidCalculations().add(new ValidCalculation(values, Formula.FOODCALC, Operators.MULTIPLICATION));

		animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT).setIsDistractor(false);
		storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).setIsDistractor(false);

		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs question based on the building of a new house
	 */
	private void constructBuildingQuestion() {
		Map<QuestionAttribute, Element> buildingMap = questionTypeFlags.get(QuestionType.BUILDING);
		Map<QuestionAttribute, Element> storeMap = questionTypeFlags.get(QuestionType.FEEDING);
		Map<QuestionAttribute, Element> materialMap = questionTypeFlags.get(QuestionType.BUYINGMATERIAL);

		StoreElement storeElement = (StoreElement) storeMap.get(QuestionAttribute.STORE);
		NewBuildingElement newBuildingElement = (NewBuildingElement) buildingMap.get(QuestionAttribute.BUILD);
		MaterialElement materialElement = (MaterialElement) materialMap.get(QuestionAttribute.BUILDMATERIAL);

		Question question;
		StringBuilder sb = new StringBuilder();
		sb.append("Wie viele ");
		sb.append(materialElement.getElementText());
		sb.append(" müssen für die Reparatur angeschafft werden?");

		float result = newBuildingElement.getAttributeByType(AttributeType.AREA).getNumericalValue()
				* newBuildingElement.getMaterialPerArea();

		question = new Question(sb.toString(), result, 3);

		Float[] values = new Float[] { (float) newBuildingElement.getMaterialPerArea(),
				storeElement.getAttributeByType(AttributeType.AREA).getNumericalValue() };

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(storeElement.getAttributeByType(AttributeType.AREA));

		AttributeCalculation attributeCalc = new AttributeCalculation(
				storeElement.getAttributeByType(AttributeType.AREA), newBuildingElement.getMaterialPerArea(),
				Operators.MULTIPLICATION);
		OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
		optimalSolution.getCalculationStats().setStepsNeeded(2);
		question.setOptimalSolution(optimalSolution);

		storeElement.getAttributeByType(AttributeType.AREA).setIsDistractor(false);
		storeElement.getAttributeByType(AttributeType.LENGTH).setIsDistractor(false);
		storeElement.getAttributeByType(AttributeType.WIDTH).setIsDistractor(false);

		question.getValidCalculations()
				.add(new ValidCalculation(values, Formula.REPLACEMENT, Operators.MULTIPLICATION));

		question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs question based on buying new food
	 */
	private void constructPayingFoodQuestion() {
		Map<QuestionAttribute, Element> buyingMap = questionTypeFlags.get(QuestionType.BUYINGFOOD);
		Map<QuestionAttribute, Element> feedingMap = questionTypeFlags.get(QuestionType.FEEDING);

		ProductElement productElement;
		StoreElement storeElement;
		AnimalElement animalElement;

		Attribute animalAttr = null;
		Attribute annimalAttrNewBuilding = null;

		if (buyingMap != null) {
			productElement = (ProductElement) buyingMap.get(QuestionAttribute.ANIMALFOOD);
			storeElement = (StoreElement) buyingMap.get(QuestionAttribute.STORE);
			animalElement = (AnimalElement) buyingMap.get(QuestionAttribute.ANIMAL);
		} else {
			productElement = (ProductElement) feedingMap.get(QuestionAttribute.ANIMALFOOD);
			storeElement = (StoreElement) feedingMap.get(QuestionAttribute.STORE);
			animalElement = (AnimalElement) feedingMap.get(QuestionAttribute.ANIMAL);

		}

		Map<QuestionAttribute, Element> newBuildingMap = questionTypeFlags.get(QuestionType.BUILDING);

		NewBuildingElement newBuildingElement = null;

		if (newBuildingMap != null) {
			newBuildingElement = (NewBuildingElement) newBuildingMap.get(QuestionAttribute.BUILD);
		}

		animalAttr = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT);

		if (newBuildingElement != null) {
			annimalAttrNewBuilding = newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT);
		}

		Attribute foodReq = animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT);

		StringBuilder sb = new StringBuilder();
		sb.append("Wie viel Geld muss für die Versorgung aller Tiere ausgegeben werden?");

		float result = 0;
		int animalCount = (int) animalAttr.getNumericalValue();

		if (annimalAttrNewBuilding != null) {
			animalCount += annimalAttrNewBuilding.getNumericalValue();
		}

		result = storeElement.getDaysWorthOfFood() * productElement.getCost().getNumericalValue() * animalCount
				* foodReq.getNumericalValue();
		int stepsNeeded = stepsNeededMap.get(StepsNeeded.EXPENSESFOOD) + 1;

		Question question = new Question(sb.toString(), result, 4);

		Float[] values = new Float[] { storeElement.getDaysWorthOfFood(), productElement.getCost().getNumericalValue(),
				(float) animalCount, foodReq.getNumericalValue() };

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT));
		calcAttributes.add(storeElement.getAttributeByType(AttributeType.ANIMALCOUNT));
		calcAttributes.add(productElement.getAttributeByType(AttributeType.PRICE));

		productElement.getAttributeByType(AttributeType.PRICE).setIsDistractor(false);

		AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes, storeElement.getDaysWorthOfFood(),
				Operators.MULTIPLICATION);
		OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
		optimalSolution.getCalculationStats().setStepsNeeded(stepsNeeded);
		question.setOptimalSolution(optimalSolution);

		question.getValidCalculations().add(new ValidCalculation(values, Formula.FOODCALC2, Operators.MULTIPLICATION));

		question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs question based on how much money overall was spent
	 */
	private void constructAllMoneySpentQuestion() {
		Map<QuestionAttribute, Element> storeMap = questionTypeFlags.get(QuestionType.FEEDING);

		StoreElement storeElement = (StoreElement) storeMap.get(QuestionAttribute.STORE);

		Question question;
		StringBuilder sb = new StringBuilder();
		sb.append("Wie viel Geld muss insgesamt ausgegeben werden?");
		Attribute expensesFood = storeElement.getExpenseFood();
		Attribute expensesConstruction = storeElement.getExpenseConstruction();

		float result = expensesFood.getNumericalValue() + expensesConstruction.getNumericalValue();

		question = new Question(sb.toString(), result, 4);

		Float[] values = new Float[] { expensesFood.getNumericalValue(), expensesConstruction.getNumericalValue() };

		int stepsNeeded = stepsNeededMap.get(StepsNeeded.EXPENSESFOOD)
				+ stepsNeededMap.get(StepsNeeded.EXPENSESMATERIAL) + 1;

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(expensesFood);
		calcAttributes.add(expensesConstruction);

		AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes, Operators.ADDITION);
		OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
		optimalSolution.getCalculationStats().setStepsNeeded(stepsNeeded);
		question.setOptimalSolution(optimalSolution);

		question.getValidCalculations().add(new ValidCalculation(values, Formula.EXPENSES, Operators.ADDITION));

		question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs question based on the whole income of the store.
	 */
	private void constructAllIncomeQuestion() {
		Map<QuestionAttribute, Element> storeMap = questionTypeFlags.get(QuestionType.FEEDING);

		StoreElement storeElement = (StoreElement) storeMap.get(QuestionAttribute.STORE);
		storeElement.getAttributeByType(AttributeType.INCOMEVISITORS).setIsDistractor(false);
		storeElement.getAttributeByType(AttributeType.INCOMEVISITORS).setIsVisible(false);

		Question question;
		StringBuilder sb = new StringBuilder();
		sb.append("Wie hoch sind die Einnahmen der ");
		sb.append(storeElement.getElementText());
		Attribute incomeVisitors = storeElement.getIncomeVisitors();

		float result = incomeVisitors.getNumericalValue();
		float stepsNeeded = stepsNeededMap.get(StepsNeeded.INCOME) + 1;

		question = new Question(sb.toString(), result, 4);

		OptimalSolution optimalSolution = new OptimalSolution(
				storeElement.getAttributeByType(AttributeType.INCOMEVISITORS).getAttributeCalculation());
		optimalSolution.getCalculationStats().setStepsNeeded((int) stepsNeeded);
		question.setOptimalSolution(optimalSolution);
		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs the map that holds information about how many steps were needed to
	 * solve a certain question
	 */
	private void fillStepsNeededMap() {
		Map<QuestionAttribute, Element> storeMap = questionTypeFlags.get(QuestionType.FEEDING);
		Map<QuestionAttribute, Element> incomeMap = questionTypeFlags.get(QuestionType.INCOMEVISITORS);
		Map<QuestionAttribute, Element> newBuildingMap = questionTypeFlags.get(QuestionType.BUILDING);

		Attribute customerSpending = null;
		Attribute foodReq = null;
		Attribute animalCount = null;
		Attribute animalCountNewBuilding = null;
		Attribute chosenAttribute = null;

		int incomeSteps = 0;
		int expensesFoodSteps = 0;
		int expensesBuildingSteps = 0;
		int animalCountVal = 0;
		int foodReqVal = 0;

		if (storeMap.get(QuestionAttribute.STORE).getAttributeByType(AttributeType.INCOMEVISITORS) != null) {
			incomeSteps = 1;
		}

		if (storeMap.get(QuestionAttribute.STORE).getAttributeByType(AttributeType.EXPENSEFOOD) != null) {
			expensesFoodSteps = 1;
		}

		if (storeMap.get(QuestionAttribute.STORE).getAttributeByType(AttributeType.EXPENSECONSTRUCTION) != null) {
			expensesBuildingSteps = 2;
		}

		if (incomeMap != null) {
			customerSpending = incomeMap.get(QuestionAttribute.CUSTOMER)
					.getAttributeByType(AttributeType.CUSTOMERSPENDING);
		}
		animalCount = storeMap.get(QuestionAttribute.STORE).getAttributeByType(AttributeType.ANIMALCOUNT);
		foodReq = storeMap.get(QuestionAttribute.STORE).getAttributeByType(AttributeType.FOODREQUIREMENT);
		chosenAttribute = ((StoreElement) storeMap.get(QuestionAttribute.STORE)).getChosenAttribute();

		if (chosenAttribute != null && !chosenAttribute.getIsVisible()) {
			animalCountVal++;
		}

		if (newBuildingMap != null) {
			animalCountNewBuilding = newBuildingMap.get(QuestionAttribute.BUILD)
					.getAttributeByType(AttributeType.ANIMALCOUNT);
			animalCountVal++;
		}

		if (customerSpending != null && !customerSpending.getIsVisible()) {
			incomeSteps++;
		}

		if (foodReq != null && !foodReq.getIsVisible()) {
			foodReqVal++;
			expensesFoodSteps++;
		}

		if (animalCount != null && !animalCount.getIsVisible()) {
			expensesFoodSteps++;
			animalCountVal++;
		}

		if (animalCountNewBuilding != null && !animalCountNewBuilding.getIsVisible()) {
			expensesFoodSteps++;
			animalCountVal++;
		}

		stepsNeededMap.put(StepsNeeded.INCOME, incomeSteps);
		stepsNeededMap.put(StepsNeeded.EXPENSESFOOD, expensesFoodSteps);
		stepsNeededMap.put(StepsNeeded.EXPENSESMATERIAL, expensesBuildingSteps);
		stepsNeededMap.put(StepsNeeded.ANIMALCOUNT, animalCountVal);
		stepsNeededMap.put(StepsNeeded.FOODREQ, foodReqVal);

	}

	/**
	 * Constructs question based on how much profit was made.
	 */
	private void constructProfitQuestion() {
		Map<QuestionAttribute, Element> storeMap = questionTypeFlags.get(QuestionType.FEEDING);

		StoreElement storeElement = (StoreElement) storeMap.get(QuestionAttribute.STORE);

		Attribute expensesFood = storeElement.getExpenseFood();
		Attribute expensesConstruction = storeElement.getExpenseConstruction();
		Attribute incomeVisitors = storeElement.getIncomeVisitors();

		List<Attribute> expensesAttributes = new ArrayList<>();
		List<Attribute> incomeAttributes = new ArrayList<>();
		List<Float> allRelevantMoneyTransactions = new ArrayList<>();

		float allExpenses = 0;
		float allIncome = 0;

		if (expensesFood != null) {
			expensesAttributes.add(expensesFood);
			allRelevantMoneyTransactions.add(expensesFood.getNumericalValue());
		}

		if (expensesConstruction != null) {
			expensesAttributes.add(expensesConstruction);
			allRelevantMoneyTransactions.add(expensesConstruction.getNumericalValue());
		}

		if (incomeVisitors != null) {
			incomeAttributes.add(incomeVisitors);
			allRelevantMoneyTransactions.add(incomeVisitors.getNumericalValue());
		}

		for (Attribute attribute : expensesAttributes) {
			allExpenses += attribute.getNumericalValue();
		}

		for (Attribute attribute : incomeAttributes) {
			allIncome += attribute.getNumericalValue();
		}

		float resultProfit = allIncome - allExpenses;
		float resultProfitPercentage = (allIncome / Math.max(allExpenses, 1f)) * 100;

		int stepsNeeded = stepsNeededMap.get(StepsNeeded.EXPENSESFOOD)
				+ stepsNeededMap.get(StepsNeeded.EXPENSESMATERIAL) + stepsNeededMap.get(StepsNeeded.INCOME) + 1;

		Question questionProfitPercentage;

		StringBuilder sbProfit = new StringBuilder();
		sbProfit.append("Wie viel Profit macht die ");
		sbProfit.append(storeElement.getElementText());

		StringBuilder sbProfitPercentage = new StringBuilder();
		sbProfitPercentage.append("Wie ist das Verhältnis zwischen Einnahmen und Ausgaben? ");

		int decimalEnabledLevel = DifficultyHandler.getInstance().getDifficulty().getDivisionDifficulty()
				.getDecimalCountDifficultyCost().getLevel();

		Question questionProfit = new Question(sbProfit.toString(), resultProfit, 4);
		if (decimalEnabledLevel <= 2) {
			sbProfitPercentage.append("Runde auf eine ganze Zahl.");
			questionProfitPercentage = new Question(sbProfitPercentage.toString(),
					QuestionTextHelper.roundNumber(resultProfitPercentage, 0), 4);
		} else {
			sbProfitPercentage.append("Runde auf eine Nachkommastelle.");
			questionProfitPercentage = new Question(sbProfitPercentage.toString(),
					QuestionTextHelper.roundNumber(resultProfitPercentage, 2), 4);
		}
		questionProfitPercentage.setProcent(true);

		Float[] values = new Float[10];
		values = allRelevantMoneyTransactions.toArray(values);

		// ------ Constructing all the possible Questions ------

		AttributeCalculation attributeCalcProfit = new AttributeCalculation(expensesAttributes, Operators.SUBSTRACTION);
		OptimalSolution optimalSolutionProfit = new OptimalSolution(attributeCalcProfit);
		optimalSolutionProfit
				.setOptimalSolutionText("Subtrahiere die Summe aller Ausgaben von der Summe aller Einnahmen.");
		optimalSolutionProfit
				.setOptimalSolutionDetailed("Subtrahiere die Summe aller Ausgaben von der Summe aller Einnahmen.");
		optimalSolutionProfit.getCalculationStats().setStepsNeeded(stepsNeeded);

		questionProfit.setOptimalSolution(optimalSolutionProfit);

		AttributeCalculation attributeCalcProfitPercentage = new AttributeCalculation(incomeAttributes, allExpenses,
				Operators.DIVISION);
		OptimalSolution optimalSolutionProfitPercentage = new OptimalSolution(attributeCalcProfitPercentage);
		optimalSolutionProfitPercentage
				.setOptimalSolutionText("Dividiere die Summe aller Einnahmen mit der Summe aller Ausgaben.");
		optimalSolutionProfitPercentage
				.setOptimalSolutionDetailed("Dividiere die Summe aller Einnahmen mit der Summe aller Ausgaben.");
		optimalSolutionProfitPercentage.getCalculationStats().setStepsNeeded(stepsNeeded);
		questionProfitPercentage.setOptimalSolution(optimalSolutionProfitPercentage);

		questionProfitPercentage.getValidCalculations()
				.add(new ValidCalculation(values, Formula.PROFITPERCENTAGE, Operators.DIVISION));
		questionProfitPercentage.getCalculationInformation()
				.setAttributeConcept(AttributeConcept.PERCENTAGE_CALCULATION_SKILL);

		questionProfit.getValidCalculations().add(new ValidCalculation(values, Formula.PROFIT, Operators.SUBSTRACTION));
		questionProfit.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		questionCatalogue.getQuestions().add(questionProfit);
		questionCatalogue.getQuestions().add(questionProfitPercentage);
	}

	/**
	 * Constructs question based on how money was spent for materials
	 */
	private void constructPayingMaterialQuestion() {
		Map<QuestionAttribute, Element> buyingMaterialMap = questionTypeFlags.get(QuestionType.BUYINGMATERIAL);
		Map<QuestionAttribute, Element> buyingBuildingMap = questionTypeFlags.get(QuestionType.BUILDING);

		NewBuildingElement newBuildingElement = (NewBuildingElement) buyingBuildingMap.get(QuestionAttribute.BUILD);
		MaterialElement materialElement = (MaterialElement) buyingMaterialMap.get(QuestionAttribute.BUILDMATERIAL);

		Question question;
		StringBuilder sb = new StringBuilder();
		sb.append("Wie viel Geld muss für den Anbau ausgegeben werden?");

		float result = newBuildingElement.getAttributeByType(AttributeType.AREA).getNumericalValue()
				* materialElement.getAttributeByType(AttributeType.PRICE).getNumericalValue()
				* newBuildingElement.getMaterialPerArea();

		question = new Question(sb.toString(), result, 4);

		Float[] values = new Float[] {
				(float) materialElement.getAttributeByType(AttributeType.PRICE).getNumericalValue(),
				newBuildingElement.getAttributeByType(AttributeType.AREA).getNumericalValue(),
				(float) newBuildingElement.getMaterialPerArea() };

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(newBuildingElement.getAttributeByType(AttributeType.AREA));
		calcAttributes.add(materialElement.getAttributeByType(AttributeType.PRICE));

		AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes,
				newBuildingElement.getMaterialPerArea(), Operators.MULTIPLICATION);
		OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
		question.setOptimalSolution(optimalSolution);

		question.getValidCalculations()
				.add(new ValidCalculation(values, Formula.BUYINGMATERIALCALC, Operators.MULTIPLICATION));

		question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		if (!newBuildingElement.getAttributeByType(AttributeType.AREA).getIsVisible()) {
			question.getOptimalSolution().getCalculationStats()
					.setStepsNeeded(question.getOptimalSolution().getCalculationStats().getStepsNeeded() + 1);
		}

		question.getOptimalSolution().getCalculationStats().setStepsNeeded(2);

		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs question based on how many animals are in the store
	 */
	private void constructAllAnimalsQuestion() {
		Map<QuestionAttribute, Element> storeMap = questionTypeFlags.get(QuestionType.FEEDING);
		Map<QuestionAttribute, Element> newBuildingMap = questionTypeFlags.get(QuestionType.BUILDING);

		StoreElement storeElement;
		NewBuildingElement newBuildingElement = null;

		storeElement = (StoreElement) storeMap.get(QuestionAttribute.STORE);

		if (newBuildingMap != null) {
			newBuildingElement = (NewBuildingElement) newBuildingMap.get(QuestionAttribute.BUILD);
			storeElement = (StoreElement) storeMap.get(QuestionAttribute.STORE);
		}

		StringBuilder sb = new StringBuilder();
		if (newBuildingElement != null) {

			sb.append("Wie hoch ist die Anzahl der Tiere nach dem Umbau?");
		} else {
			sb.append("Wie hoch ist die Anzahl der Tiere?");
		}
		float result;
		Float[] values;
		Question question = null;

		int stepsNeeded = stepsNeededMap.get(StepsNeeded.ANIMALCOUNT);

		if (newBuildingElement != null) {
			result = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue()
					+ newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue();
			values = new Float[] { storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue(),
					newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue() };

			question = new Question(sb.toString(), result, 2);

			List<Attribute> calcAttributes = new ArrayList<>();
			calcAttributes.add(newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT));
			calcAttributes.add(storeElement.getAttributeByType(AttributeType.ANIMALCOUNT));

			newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT).setIsDistractor(false);

			AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes, Operators.ADDITION);
			OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
			optimalSolution.getCalculationStats().setStepsNeeded(stepsNeeded);
			question.setOptimalSolution(optimalSolution);

			question.getValidCalculations().add(new ValidCalculation(values, Formula.ALLANIMALS, Operators.ADDITION));

		} else {
			result = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue();

			question = new Question(sb.toString(), result, 3);

			AttributeCalculation attributeCalc = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT)
					.getAttributeCalculation();
			OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
			optimalSolution.getCalculationStats().setStepsNeeded(stepsNeeded);
			question.setOptimalSolution(optimalSolution);

			question.setValidCalculations(
					storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getValidCalculations());

		}
		storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).setIsDistractor(false);
		question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		questionCatalogue.getQuestions().add(question);
	}

	/**
	 * Constructs question based on missing attributes
	 */
	private void constructAttributeQuestion(Attribute attribute) {
		StringBuilder sb = new StringBuilder();
		sb.append("Was ist die ");
		sb.append(attribute.getName());
		sb.append(" von ");
		sb.append(attribute.getElement().getElementText());
		sb.append("?");

		Question question = new Question(sb.toString().replace("die Nahrungsbedarf", "der Nahrungsbedarf"),
				attribute.getNumericalValue(), 1);
		OptimalSolution optimalSolution = new OptimalSolution(attribute.getAttributeCalculation());
		question.setOptimalSolution(optimalSolution);

		AttributeType attributeType = attribute.getAttributeType();
		if (!question.getAttributeOnly()) {
			if (attributeType == AttributeType.AREA || attributeType == AttributeType.LENGTH
					|| attributeType == AttributeType.WIDTH) {
				question.getCalculationInformation().setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);
			}

			if (attributeType == AttributeType.ANIMALCOUNT) {
				question.getCalculationInformation().setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
			}
		}

		question.getValidCalculations().addAll(attribute.getValidCalculations());
		questionCatalogue.getQuestions().add(question);
	}

	public QuestionCatalogue getQuestionCatalogue() {
		return questionCatalogue;
	}

	public void setQuestionCatalogue(QuestionCatalogue questionCatalogue) {
		this.questionCatalogue = questionCatalogue;
	}

	/**
	 * converts questions into Gui questions
	 * 
	 * @return list of questions in Gui format
	 */
	public List<QuestionGui> getListOfQuestionGui() {
		List<QuestionGui> questionGuis = new ArrayList<>();

		for (Question question : questionCatalogue.questions) {
			questionGuis.add(convertToGuiQuestion(question));
		}
		return questionGuis;
	}

	/**
	 * converts a question intoa Gui question
	 * 
	 * @return QuestionGui question in Gui format
	 */
	public QuestionGui convertToGuiQuestion(Question question) {

		return new QuestionGui(question.getQuestionText(), question.getId(), question.getResult(),
				question.getOptimalSolution(), question.isProcent());
	}

	/**
	 * Checks all the possible skill representations of a student that submits a
	 * question result, such as correctness of the result and if the correct formula
	 * was used.
	 * 
	 * @param questionCalculation holds information about the question calculation
	 * @return QuestionAnswerCheck containing information about the answer check
	 */
	public QuestionAnswerCheck checkQuestionAnswer(QuestionCalculation questionCalculation,
			Map<Integer, Element> elementMap) {
		AnswerHandler answerHandler = AnswerHandler.getInstance();
		Question question = questionCatalogue.questionMap.get(questionCalculation.getQuestionId());

		Boolean correctAnswer = answerHandler.checkCorrectQuestionAnswer(question, questionCalculation);
		UserResult userResult = answerHandler.checkCorrectQuestionCalculation(questionCalculation);
		Float correctComponents = answerHandler.checkCorrectQuestionComponents();

		QuestionAnswerCheck questionAnswerCheck = new QuestionAnswerCheck(correctAnswer, userResult, correctComponents,
				question.getOptimalSolution(), (userResult.getUserCalculationResult() == question.getResult()));
		questionAnswerCheck.setAttributeOnly(question.getAttributeOnly());
		if (!question.getAttributeOnly()) {
			List<Attribute> convertedAttributes = AttributeUtils
					.convertTargetAttributes(questionCalculation.getContainedAttributes(), elementMap);
			questionAnswerCheck.setCorrectFormular(FormularUtils.checkIfFormularCorrect(question.getValidCalculations(),
					convertedAttributes, questionCalculation.getCalculationSteps()));

			questionAnswerCheck.setCorrectValues(FormularUtils.checkIfCorrectValuesPresent(
					question.getValidCalculations(), questionCalculation.getCalculationSteps()));
		}

		return questionAnswerCheck;
	}

	/**
	 * Calculates how many steps overall the student needed
	 * 
	 * @return steps needed
	 */
	public int calculateStepsNeeded() {
		questionCatalogue = QuestionHandler.getInstance().getQuestionCatalogue();
		List<Question> questions = questionCatalogue.getQuestions();

		Collections.sort(questions,
				(o1, o2) -> Integer.compare(o1.getOptimalSolution().getCalculationStats().getStepsNeeded(),
						o2.getOptimalSolution().getCalculationStats().getStepsNeeded()));
		Collections.reverse(questions);

		List<Question> relevantQuestions = new ArrayList<>();
		relevantQuestions.add(questions.get(0));

		for (int i = 1; i < questions.size(); i++) {
			if (!questionAlreadyCointained(relevantQuestions, questions.get(i))) {
				relevantQuestions.add(questions.get(i));
			}
		}

		int stepsNeeded = 0;

		for (Question question : relevantQuestions) {
			if (question.getAttributeOnly()) {
				stepsNeeded += question.getOptimalSolution().getCalculationStats().getStepsNeeded();
			} else {
				stepsNeeded += question.getOptimalSolution().getCalculationStats().getStepsNeeded() - 1;
			}
		}

		return stepsNeeded;
	}

	/**
	 * Checks if the question is already in the question pool
	 * 
	 * @param questions current question pool
	 * @param question  question to be added potentially
	 * @return boolean if question is already in the pool
	 */
	private boolean questionAlreadyCointained(List<Question> questions, Question question) {
		boolean contains = false;

		for (Question tempQuestion : questions) {
			if (tempQuestion.getOptimalSolution().getOptimalSolutionDetailed()
					.contains(question.getOptimalSolution().getOptimalSolutionDetailed())) {
				contains = true;
				System.out.println(tempQuestion.getOptimalSolution().getOptimalSolutionDetailed());
				System.out.println(question.getOptimalSolution().getOptimalSolutionDetailed());
			}
		}
		return contains;
	}

}
