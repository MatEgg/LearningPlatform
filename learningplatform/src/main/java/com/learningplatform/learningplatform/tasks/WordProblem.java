package com.learningplatform.learningplatform.tasks;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.ExpensesConstruction;
import com.learningplatform.learningplatform.attributes.ExpensesFood;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.attributes.IncomeVisitors;
import com.learningplatform.learningplatform.bkt.BktHandler;
import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.controllers.WordProblemController;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentQuestionUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.guiObjects.QuestionGui;
import com.learningplatform.learningplatform.guiObjects.TemplateGui;
import com.learningplatform.learningplatform.models.Field;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.models.service.UserService;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.question.QuestionHandler.QuestionType;
import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.templates.TemplateHandler;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.CityElement;
import com.learningplatform.learningplatform.templates.Elements.CustomerElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ElementHandler;
import com.learningplatform.learningplatform.templates.Elements.MaterialElement;
import com.learningplatform.learningplatform.templates.Elements.NewBuildingElement;
import com.learningplatform.learningplatform.templates.Elements.ProductElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.ConceptEnum;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.user.UserHandler;
import com.learningplatform.learningplatform.utilities.AttributeUtils;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import learningplatform.learningplatform.calculation.AttributeCalculation;
import learningplatform.learningplatform.calculation.OptimalSolution;
import learningplatform.learningplatform.calculation.ValidCalculation;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.core.env.MissingRequiredPropertiesException;

public class WordProblem implements TaskInterface {

	String questionText;
	DaoFactory daoFactory;
	User user;
	Settings settings;

	public int elementID = 1;
	public int questionID = 1;

	Map<AlreadyAsked, Boolean> alreadyAskedMap;

	public enum AlreadyAsked {
		BOUGHTFOOD, BOUGHTMATERIAL
	}

	List<Template> templates;

	ListMultimap<QuestionAttribute, Element> elementsMap;
	List<Question> questions;
	Map<Integer, Question> questionIdMap;
	Map<Integer, Element> elementIdMap;

	List<QuestionGui> questionList;

	QuestionHandler questionHandler;
	TemplateHandler templateHandler;
	ElementHandler elementHandler;
	AttributeHandler attributeHandler;
	DifficultyHandler difficultyHandler;
	UserHandler userHandler;
	BktHandler bktHandler;

	ListMultimap<AttributeType, Attribute> relevantAttributes;
	List<TemplateGui> templateList;

	private static WordProblem instance;

	public static WordProblem getNewInstance(DaoFactory daoFactory) {
		instance = new WordProblem(daoFactory);
		QuestionHandler.getNewInstance();
		instance.createTask();
		return instance;
	}

	public static WordProblem getInstance() {
		return instance;
	}

	public WordProblem(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		for (Field field : daoFactory.getFieldService().getAllFields()) {
			field.setUsed(0);
			daoFactory.getFieldService().save(field);
		}
		alreadyAskedMap = new EnumMap<>(AlreadyAsked.class);
		alreadyAskedMap.put(AlreadyAsked.BOUGHTFOOD, false);
		alreadyAskedMap.put(AlreadyAsked.BOUGHTMATERIAL, false);
		questionIdMap = new HashMap<>();
		elementIdMap = new HashMap<>();
		questionText = "test";
		questions = new ArrayList<>();
		elementsMap = ArrayListMultimap.create();
		templates = new ArrayList<>();
		templateList = new ArrayList<>();
		questionList = new ArrayList<>();
		relevantAttributes = ArrayListMultimap.create();
	}

	@Override
	public void createTask() {
		// identify user first
		userIdentification();
		// initialize the user
		initializeSettings(user);
		// initialize the difficulty
		intializeDifficulty();
		// create the templates used in the word problem
		createTemplates();
		// Fix things in the word problem
		postProcessing();
		// Create questions based on the word problem's content
		createQuestions();
		// Add additional distractors to the object information
		addDistractors();
		// Add everything together and construct the task
		constructTask();
		printAllAttributes();
		printAllCalculations();
		// Initialize the Update Mechanisms
		bktInitial();
	}

	private void userIdentification() {
		UserService userService = this.daoFactory.getUserService();
		user = userService.findByUsername(UserHandler.getInstance().getCurrentLoggedUser());
	}

	private void bktInitial() {
		bktHandler = BktHandler.getInstance();
	}

	private void initializeSettings(User user) {
		settings = user.getSettings();
	}

	private void intializeDifficulty() {
		difficultyHandler = DifficultyHandler.getNewInstance();
	}

	private void createTemplates() {
		templateHandler = new TemplateHandler();
		templateHandler.findTemplatesRandomly();
		templates = templateHandler.getTemplates();
	}

	private void createQuestions() {
		questionHandler = QuestionHandler.getInstance();
		questionHandler.generateQuestions(getQuestionElements());
		questionHandler.getQuestionCatalogue().setQuestions(DifficultyAdjustmentQuestionUtils
				.curateQuestions(questionHandler.getQuestionCatalogue().getQuestions(), DifficultyHandler.getInstance()
						.getDifficulty().getConceptualDifficulty().getQuestionDifficultyDifficultyCost().getLevel()));
		questionList = questionHandler.getListOfQuestionGui();
	}

	/**
	 * checks the visibility of attributes contained in the complete task.
	 */
	private void postProcessing() {
		for (Template template : templates) {
			for (Element element : template.getQuestionElements()) {
				for (Attribute attribute : element.getAttributeList()) {
					AttributeUtils.checkVisibilityOfChildren(attribute);
				}
			}
		}

		calculateIncomeAndExpenses();
	}

	private void calculateBuyingFoodIncome(StoreElement storeElement, AnimalElement animalElement,
			ProductElement productElement, NewBuildingElement newBuildingElement) {
		Attribute expenseFood = new ExpensesFood(42, storeElement);
		storeElement.setExpenseFood(expenseFood);
		storeElement.addOneAttribute(expenseFood);
		expenseFood.setAttributeCalculation(new AttributeCalculation(expenseFood));
		expenseFood.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		Attribute foodRequirementAttribute = animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT);
		Attribute costAttribute = productElement.getCost();
		Attribute animalCountAttribute = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT);

		float cost = productElement.getCost().getNumericalValue();
		float animalCount = (float) storeElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue();

		if (newBuildingElement != null) {
			animalCount = animalCount
					+ newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT).getNumericalValue();
		}

		float foodRequirement = foodRequirementAttribute.getNumericalValue();
		float days = storeElement.getDaysWorthOfFood();

		List<Attribute> expenseCalculation = new ArrayList<>();
		expenseCalculation.add(costAttribute);
		expenseCalculation.add(animalCountAttribute);
		expenseCalculation.add(foodRequirementAttribute);

		Float[] attributeValues = new Float[] { cost, animalCount, foodRequirement };

		expenseFood.getValidCalculations()
				.add(new ValidCalculation(attributeValues, Formula.BUYINGFOODCALC, Operators.MULTIPLICATION));

		expenseFood.setNumericalValue(cost * animalCount * foodRequirement * days);

		expenseFood
				.setAttributeCalculation(new AttributeCalculation(expenseCalculation, days, Operators.MULTIPLICATION));
		expenseFood.setIsVisible(false);
	}

	private void calculateBuyingMaterialIncome(StoreElement storeElement, NewBuildingElement newBuildingElement,
			MaterialElement materialElement) {
		Attribute expenseConstruction = new ExpensesConstruction(70, storeElement);
		storeElement.setExpenseConstruction(expenseConstruction);
		storeElement.addOneAttribute(expenseConstruction);
		expenseConstruction.setAttributeCalculation(new AttributeCalculation(expenseConstruction));
		expenseConstruction.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		float cost = materialElement.getAttributeByType(AttributeType.PRICE).getNumericalValue();
		float area = newBuildingElement.getAttributeByType(AttributeType.AREA).getNumericalValue();
		float materialPerArea = newBuildingElement.getMaterialPerArea();
		float result = area * cost * materialPerArea;

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(newBuildingElement.getAttributeByType(AttributeType.AREA));
		calcAttributes.add(materialElement.getAttributeByType(AttributeType.PRICE));

		AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes,
				newBuildingElement.getMaterialPerArea(), Operators.MULTIPLICATION);
		expenseConstruction.setAttributeCalculation(attributeCalc);

		expenseConstruction.setNumericalValue(result);

		Float[] attributeValues = new Float[] { area, cost, materialPerArea };

		expenseConstruction.getValidCalculations()
				.add(new ValidCalculation(attributeValues, Formula.BUYINGMATERIALCALC, Operators.MULTIPLICATION));

		expenseConstruction.setIsVisible(false);
	}

	private void calculateIncomeThroughVisitors(StoreElement storeElement, CityElement cityElement,
			CustomerElement customerElement) {
		Attribute incomeVisitors = new IncomeVisitors(42, storeElement);
		storeElement.setIncomeVisitors(incomeVisitors);
		storeElement.addOneAttribute(incomeVisitors);

		List<Attribute> calcAttributes = new ArrayList<>();
		calcAttributes.add(cityElement.getAttributeByType(AttributeType.POPULATION));
		calcAttributes.add(customerElement.getAttributeByType(AttributeType.CUSTOMERSPENDING));

		float spentPerVisitor = customerElement.getSpentPerVisitor();
		float percentageOfVisitorsSpending = customerElement.getPercentageOfVisitorsSpending();
		float population = cityElement.getAttributeByType(AttributeType.POPULATION).getNumericalValue();
		float taxes = customerElement.getTaxes();

		float result = ((percentageOfVisitorsSpending * population) * spentPerVisitor) * (1 - taxes);
		AttributeCalculation attributeCalc = new AttributeCalculation(calcAttributes, percentageOfVisitorsSpending,
				Operators.MULTIPLICATION);
		incomeVisitors.setAttributeCalculation(attributeCalc);

		Float[] values;
		if (taxes != 0) {
			values = new Float[] { spentPerVisitor, percentageOfVisitorsSpending, population, taxes };
			OptimalSolution optimalSolution = new OptimalSolution(attributeCalc);
			optimalSolution.setOptimalSolutionText(optimalSolution.getOptimalSolutionText() + " * " + (1 - taxes));
			incomeVisitors.setOptimalSolution(optimalSolution);

			incomeVisitors.setManualHelp(true);
		} else {
			values = new Float[] { spentPerVisitor, percentageOfVisitorsSpending, population };
		}

		incomeVisitors.getValidCalculations()
				.add(new ValidCalculation(values, Formula.INCOMEVISITORS, Operators.MULTIPLICATION));
		incomeVisitors.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.PERCENTAGE_CALCULATION_SKILL);
		incomeVisitors.getAttributeCalculation().setCorrectRoundedAnswer(QuestionTextHelper.roundNumber(result, 2));
		incomeVisitors.setNumericalValue(QuestionTextHelper.roundNumber(result, 3));
	}

	/**
	 * Analyses all income and expanses that can be found in the scenario.
	 */
	private void calculateIncomeAndExpenses() {
		Map<QuestionType, Map<QuestionAttribute, Element>> questionTypeFlags = QuestionHandler.getInstance()
				.getQuestionTypeFlags();
		StoreElement storeElement = null;

		Map<QuestionAttribute, Element> buyingFoodMap = null;
		Map<QuestionAttribute, Element> incomeVisitorsMap = null;
		Map<QuestionAttribute, Element> newBuildingMap = questionTypeFlags.get(QuestionType.BUILDING);
		Map<QuestionAttribute, Element> buyingMaterialMap = null;
		Map<QuestionAttribute, Element> feedingMap = questionTypeFlags.get(QuestionType.FEEDING);

		NewBuildingElement newBuildingElement = null;
		CityElement cityElement = null;
		CustomerElement customerElement = null;

		if (newBuildingMap != null) {
			newBuildingElement = (NewBuildingElement) newBuildingMap.get(QuestionAttribute.BUILD);
		}

		ProductElement productElement = null;
		AnimalElement animalElement = null;
		MaterialElement materialElement = null;

		storeElement = (StoreElement) feedingMap.get(QuestionAttribute.STORE);

		if (questionTypeFlags.containsKey(QuestionType.BUYINGFOOD)) {
			buyingFoodMap = questionTypeFlags.get(QuestionType.BUYINGFOOD);
			productElement = (ProductElement) buyingFoodMap.get(QuestionAttribute.ANIMALFOOD);
			animalElement = (AnimalElement) buyingFoodMap.get(QuestionAttribute.ANIMAL);
		}

		if (questionTypeFlags.containsKey(QuestionType.BUYINGMATERIAL)) {
			buyingMaterialMap = questionTypeFlags.get(QuestionType.BUYINGMATERIAL);
			materialElement = (MaterialElement) buyingMaterialMap.get(QuestionAttribute.BUILDMATERIAL);
		}

		if (questionTypeFlags.containsKey(QuestionType.INCOMEVISITORS)) {
			incomeVisitorsMap = questionTypeFlags.get(QuestionType.INCOMEVISITORS);
			cityElement = (CityElement) incomeVisitorsMap.get(QuestionAttribute.CITY);
			customerElement = (CustomerElement) incomeVisitorsMap.get(QuestionAttribute.CUSTOMER);

		}

		if (storeElement != null) {

			if (buyingFoodMap != null) {
				calculateBuyingFoodIncome(storeElement, animalElement, productElement, newBuildingElement);
			}

			if (buyingMaterialMap != null && newBuildingElement != null) {

				calculateBuyingMaterialIncome(storeElement, newBuildingElement, materialElement);
			}

			if (incomeVisitorsMap != null) {
				calculateIncomeThroughVisitors(storeElement, cityElement, customerElement);
			}
		}
	}

	/**
	 * Adds distractors to the object information
	 */
	private void addDistractors() {
		int distractorLevel = DifficultyHandler.getInstance().getDifficulty().getConceptualDifficulty()
				.getDistractorDifficultyCost().getLevel();
		Set<Attribute> possibleDistractorAttributeSet = new HashSet<>();
		// Find all possible distractors
		for (Template template : templates) {
			for (Element element : template.getQuestionElements()) {
				for (Attribute attribute : element.getAttributeList()) {
					if (attribute != null && attribute.getIsDistractor()) {
						possibleDistractorAttributeSet.add(attribute);
					}
				}
			}
		}

		List<Attribute> possibleDistractorAttributeList = new ArrayList<>(possibleDistractorAttributeSet);

		// calculate how many distractors should be shown
		int amountAdded = (int) ((float) distractorLevel / 10 * possibleDistractorAttributeList.size());

		int nextIndex;
		// randomly add the desired amount of distractors
		for (int i = 0; i < amountAdded; i++) {
			nextIndex = UtilitiesHelper.getRandom().nextInt(possibleDistractorAttributeList.size());
			possibleDistractorAttributeList.get(nextIndex).setIsShown(true);
			possibleDistractorAttributeList.remove(nextIndex);
		}

	}

	public ListMultimap<QuestionAttribute, Element> getElementsMap() {
		return elementsMap;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public ListMultimap<AttributeType, Attribute> getRelevantAttributes() {
		return relevantAttributes;
	}

	private void printAllAttributes() {
		StringBuilder sbPrint = new StringBuilder();
		for (AttributeType attributeType : AttributeType.values()) {
			sbPrint.append(attributeType.toString());
			sbPrint.append(": ");
			sbPrint.append("\n");
			for (Attribute attribute : relevantAttributes.get(attributeType)) {
				sbPrint.append("Element: ");
				sbPrint.append(attribute.getElement().getElementText());
				sbPrint.append(" ");
				sbPrint.append(attribute.getNumericalValue());
				sbPrint.append("\n");
				sbPrint.append(attribute.getIsVisible());
				sbPrint.append("\n");
				sbPrint.append(attribute.getIsReplacement());
				sbPrint.append("\n");
			}
			sbPrint.append("\n");
			sbPrint.append("\n");
		}
	}

	private void printAllCalculations() {
		StringBuilder sbPrint = new StringBuilder();
		for (Element element : getQuestionElements()) {
			sbPrint.append(element.getElementText());
			sbPrint.append(": ");
			sbPrint.append("\n");
			for (Attribute attribute : element.getAttributeList()) {
				if (attribute != null) {
					sbPrint.append(attribute.getName());
					sbPrint.append(": ");
					sbPrint.append("\n");
				}
			}
			sbPrint.append("\n");
			sbPrint.append("\n");
		}
	}

	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public List<Element> getQuestionElements() {
		List<Element> elements = new ArrayList<>();
		for (Template template : templates) {
			for (Element element : template.getQuestionElements()) {
				if (!elements.contains(element)) {
					elements.add(element);
				}
			}
		}
		return elements;
	}

	/**
	 * Add all templates together
	 */
	private void constructTask() {
		QuestionText currentQuestionText;
		for (Template template : templates) {
			currentQuestionText = template.getQuestionText();
			currentQuestionText.fillStringBuilder();
			String text = currentQuestionText.getQuestionText();

			if (!text.equals("dummy")) {
				text = text.replaceAll("Eine Das", "Eine");
				text = text.replaceAll("Eine Der", "Eine");
				text = text.replaceAll("Eine Die", "Eine");

				TemplateGui templateGui = new TemplateGui(text, template);
				templateList.add(templateGui);
			}

		}
	}

	public List<TemplateGui> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<TemplateGui> templateList) {
		this.templateList = templateList;
	}

	public List<QuestionGui> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionGui> questionList) {
		this.questionList = questionList;
	}

	public int getElementID() {
		return elementID;
	}

	public void setElementID(int elementID) {
		this.elementID = elementID;
	}

	public Map<Integer, Question> getQuestionIdMap() {
		return questionIdMap;
	}

	public void setQuestionIdMap(Map<Integer, Question> questionIdMap) {
		this.questionIdMap = questionIdMap;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public Map<Integer, Element> getElementIdMap() {
		return elementIdMap;
	}

	public void setElementIdMap(Map<Integer, Element> elementIdMap) {
		this.elementIdMap = elementIdMap;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public TemplateHandler getTemplateHandler() {
		return templateHandler;
	}

	public void setTemplateHandler(TemplateHandler templateHandler) {
		this.templateHandler = templateHandler;
	}

	public Map<AlreadyAsked, Boolean> getAlreadyAskedMap() {
		return alreadyAskedMap;
	}

	public void setAlreadyAskedMap(Map<AlreadyAsked, Boolean> alreadyAskedMap) {
		this.alreadyAskedMap = alreadyAskedMap;
	}

}
