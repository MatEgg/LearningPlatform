package com.learningplatform.learningplatform.templates;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.baseTemplates.BaseTemplate;
import com.learningplatform.learningplatform.templates.connectedTemplates.ConnectedTemplate;
import com.learningplatform.learningplatform.templates.supportTemplates.SupportTemplate;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TemplateHandler {

	private static TemplateHandler templateHandlerInstance;

	List<Template> templates;
	ListMultimap<QuestionAttribute, Element> elementsMap;
	WordProblem wordProblem;
	TemplateOverview templateOverview;
	Random random;
	EnumMap<Templates, Template> templateCointainedMap;
	static final List<Templates> MEASUREMENT_TEMPLATES;
	static final List<Templates> PERCENTAGE_TEMPLATES;

	static {
		MEASUREMENT_TEMPLATES = new ArrayList<>();
		PERCENTAGE_TEMPLATES = new ArrayList<>();

		MEASUREMENT_TEMPLATES.add(Templates.CALCULATE_AREA_OF_STORE);
		MEASUREMENT_TEMPLATES.add(Templates.BUILDS_A_NEW_THING);
		MEASUREMENT_TEMPLATES.add(Templates.PAYING_FOR_MATERIAL);

		PERCENTAGE_TEMPLATES.add(Templates.INCOME_VISITORS);
	}

	public enum Templates {
		STORE_PRODUCT_ANIMAL, CALCULATE_AREA_OF_STORE, PRODUCT_TIME_UNTIL_COMPLETION, BUILDS_A_NEW_THING,
		PAYING_FOR_FOOD, PAYING_FOR_MATERIAL, REPLACE, INCOME_VISITORS
	}

	public TemplateHandler() {
		this.wordProblem = WordProblem.getInstance();
		templateCointainedMap = new EnumMap<>(Templates.class);
		random = new Random();
		this.elementsMap = WordProblem.getInstance().getElementsMap();
		templates = new ArrayList<>();
		templateOverview = new TemplateOverview();
	}

	/**
	 * Lazy instantiation
	 * 
	 * @return TemplateHandler instance
	 */
	public static TemplateHandler getInstance() {
		if (templateHandlerInstance == null) {
			templateHandlerInstance = new TemplateHandler();
		}
		return templateHandlerInstance;
	}

	public static TemplateHandler getNewInstance() {
		templateHandlerInstance = new TemplateHandler();
		return templateHandlerInstance;
	}

	/**
	 * Find a random template
	 */
	public void findTemplatesRandomly() {
		addNewTemplate(getRandomBaseTemplate());
		addFittingConnectedTemplates();
		addFittingSupportTemplates();

		WordProblem.getInstance().setTemplates(templates);
	}

	/**
	 * Find a fitting connected template
	 */
	private void addFittingConnectedTemplates() {
		List<ConnectedTemplate> connectedTemplates = findAllFittingConnectedTemplates();
		for (ConnectedTemplate connectedTemplate : connectedTemplates) {
			addNewTemplate(connectedTemplate);
		}
	}

	/**
	 * Find a fitting support template
	 */
	private void addFittingSupportTemplates() {
		List<SupportTemplate> supportTemplates = findAllFittingSupportsTemplates();
		for (SupportTemplate supportTemplate : supportTemplates) {
			addNewTemplate(supportTemplate);
		}
	}

	/**
	 * Add a new template and add all its elements to the overall elements map
	 */
	private void addNewTemplate(Template template) {
		template.callFinishTemplate();
		templateCointainedMap.put(template.getTemplateType(), template);
		for (Element element : template.getQuestionElements()) {
			for (QuestionAttribute questionAttribute : element.getQuestionAttributes()) {
				elementsMap.put(questionAttribute, element);
			}
		}
		templates.add(template);
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public BaseTemplate getRandomBaseTemplate() {
		return templateOverview.getRandomBaseTemplate();
	}

	public ConnectedTemplate getRandomConnectedTemplate() {
		return templateOverview.getRandomConnectedTemplate();
	}

	public ConnectedTemplate addRandomFittingConnectedTemplate() {
		List<ConnectedTemplate> fittingTemplates = findAllFittingConnectedTemplates();
		return fittingTemplates.get(random.nextInt(fittingTemplates.size()));
	}

	/**
	 * Find a list of potential connected Templates
	 * 
	 * @return List of potential connected templates
	 */
	private List<ConnectedTemplate> findAllFittingConnectedTemplates() {
		List<ConnectedTemplate> fittingTemplates = new ArrayList<>();
		List<ConnectedTemplate> allConnectedTemplates = templateOverview.getAllConnectedTemplates();
		for (ConnectedTemplate connectedTemplate : allConnectedTemplates) {
			fittingTemplates.add(connectedTemplate);
		}
		return fittingTemplates;
	}

	/**
	 * Find a list of potential support Templates
	 * 
	 * @return List of potential support templates
	 */
	private List<SupportTemplate> findAllFittingSupportsTemplates() {
		List<SupportTemplate> fittingTemplates = new ArrayList<>();
		List<SupportTemplate> allSupportTemplates = templateOverview.getAllSupportTemplates();
		int replaceComplexityLevel = DifficultyHandler.getInstance().getDifficulty().getConceptualDifficulty()
				.getReplaceDifficultyCost().getLevel() * 10;
		int replaceCount = 0;
		
		// Find amount of replacement based on difficulty settings
		if (replaceComplexityLevel > 25 && replaceComplexityLevel <= 60) {
			replaceCount = 1;
		} else if (replaceComplexityLevel > 61 && replaceComplexityLevel <= 90) {
			replaceCount = 2;
		} else if (replaceComplexityLevel > 91) {
			replaceCount = 3;
		}

		for (int i = 0; i < replaceCount; i++) {
			if (i < allSupportTemplates.size()) {
				SupportTemplate supportTemplate = allSupportTemplates.get(i);
				fittingTemplates.add(supportTemplate);
			}
		}
		return fittingTemplates;
	}

	public static TemplateHandler getTemplateHandlerInstance() {
		return templateHandlerInstance;
	}

	public static void setTemplateHandlerInstance(TemplateHandler templateHandlerInstance) {
		TemplateHandler.templateHandlerInstance = templateHandlerInstance;
	}
}
