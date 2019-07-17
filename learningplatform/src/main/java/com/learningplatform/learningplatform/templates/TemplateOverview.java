package com.learningplatform.learningplatform.templates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.difficulty.DifficultyCalculated;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.baseTemplates.BaseTemplate;
import com.learningplatform.learningplatform.templates.baseTemplates.StoreProductAnimalTemplate;
import com.learningplatform.learningplatform.templates.connectedTemplates.BuildThing;
import com.learningplatform.learningplatform.templates.connectedTemplates.BuyingFood;
import com.learningplatform.learningplatform.templates.connectedTemplates.BuyingMaterial;
import com.learningplatform.learningplatform.templates.connectedTemplates.CalculateAreaOfStore;
import com.learningplatform.learningplatform.templates.connectedTemplates.ConnectedTemplate;
import com.learningplatform.learningplatform.templates.connectedTemplates.IncomeThroughVisitors;
import com.learningplatform.learningplatform.templates.connectedTemplates.ProductTimeUntilDepletion;
import com.learningplatform.learningplatform.templates.supportTemplates.ReplaceValueByOther;
import com.learningplatform.learningplatform.templates.supportTemplates.SupportTemplate;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

public class TemplateOverview {
	Random random;
	WordProblem wordProblem;
	Difficulty difficulty;
	ArrayList<Supplier<BaseTemplate>> baseTemplates;

	ArrayList<Supplier<ConnectedTemplate>> connectedTemplates;
	ArrayList<Supplier<SupportTemplate>> supportedTemplates;

	Settings settings;

	boolean measurementEnabled;
	boolean percentageEnabled;

	List<Templates> possibleTemplates;

	public TemplateOverview() {
		wordProblem = WordProblem.getInstance();
		supportedTemplates = new ArrayList<>();
		connectedTemplates = new ArrayList<>();
		possibleTemplates = new ArrayList<>();
		baseTemplates = new ArrayList<>();
		difficulty = WordProblem.getInstance().getSettings().getDifficulty();
		random = new Random();

		settings = WordProblem.getInstance().getSettings();
		measurementEnabled = settings.getMeasurementEnabled() == 1;
		percentageEnabled = settings.getPercentageEnabled() == 1;

		addBaseTemplates();
		addConnectedTemplates();
		addSupportedTemplates();
	}

	private void addBaseTemplates() {

		StoreProductAnimalTemplate storeProductAnimalTemplate = new StoreProductAnimalTemplate(wordProblem);
		baseTemplates.add(() -> storeProductAnimalTemplate);
		for (Templates templateType : StoreProductAnimalTemplate.getTemplateCompatibilityMap().keySet()) {
			if (!(!measurementEnabled && TemplateHandler.MEASUREMENT_TEMPLATES.contains(templateType))
					&& !(!percentageEnabled && TemplateHandler.PERCENTAGE_TEMPLATES.contains(templateType))) {
				possibleTemplates.add(templateType);
			}
		}
	}

	private void addConnectedTemplates() {

		DifficultyCalculated difficultyCalculated = DifficultyHandler.getInstance().getDifficulty();
		int conceptAmount = difficultyCalculated.getConceptualDifficulty().getDifferentConceptsDifficultyCost()
				.getLevel() * 100 + 1;
		int pick = 0;

		int accumulatedDifficulty = 0;
		List<Templates> filteredPossibleTemplate;

		while (possibleTemplates.size() > 0 && accumulatedDifficulty < conceptAmount) {

			filteredPossibleTemplate = filterPossibleTemplateByDifficulty(accumulatedDifficulty, conceptAmount,
					possibleTemplates);

			if (filteredPossibleTemplate.isEmpty()) {
				break;

			} else {
				pick = random.nextInt(filteredPossibleTemplate.size());
				int templateDifficulty = calculateOverUnder50(
						calculateDifficultyOfTemplate(filteredPossibleTemplate.get(pick)));
				accumulatedDifficulty += templateDifficulty;
				accumulatedDifficulty += addConnectedTemplateByEnum(filteredPossibleTemplate.get(pick),
						accumulatedDifficulty, calculateDifficultyOfTemplate(filteredPossibleTemplate.get(pick)),
						conceptAmount, filteredPossibleTemplate);
				filteredPossibleTemplate.remove(pick);
			}
			possibleTemplates = filteredPossibleTemplate;
		}

	}

	private List<Templates> filterPossibleTemplateByDifficulty(int accumulatedDifficulty, int conceptAmount,
			List<Templates> possibleTemplates) {
		List<Templates> filteredList = new ArrayList<>();

		for (Templates template : possibleTemplates) {
			if (accumulatedDifficulty
					+ calculateOverUnder50(calculateDifficultyOfTemplate(template)) <= conceptAmount) {
				filteredList.add(template);
			}
		}

		return filteredList;

	}

	private int extractRelevantTemplateDifficulty(AttributeConcept attributeConcept) {
		DifficultyCalculated difficultyCalculated = DifficultyHandler.getInstance().getDifficulty();
		switch (attributeConcept) {
		case AREA_CALCULATION_SKILL:
			return difficultyCalculated.getMeasurementDifficulty().getDifficulty();
		case CALCULATE_PROBABILITIES_SKILL:
			return difficultyCalculated.getProbabilityCalculationDifficulty().getDifficulty();
		case COMPUTATIONAL_RULES_SKILL:
			return difficultyCalculated.getComputationalRulesDifficulty().getDifficulty();
		case PERCENTAGE_CALCULATION_SKILL:
			return difficultyCalculated.getPercentCalculationDifficulty().getDifficulty();
		default:
			break;

		}
		return 0;
	}

	static int calculateOverUnder50(int diff) {
		int tempDiff = 100;
		if (diff < 50) {
			tempDiff += (50 - diff) * 2;
		}

		if (diff > 50) {
			tempDiff -= (diff - 50) * 2;
		}
		return tempDiff;
	}

	private int calculateDifficultyOfTemplate(Templates template) {
		switch (template) {
		case BUILDS_A_NEW_THING:
			return extractRelevantTemplateDifficulty(AttributeConcept.AREA_CALCULATION_SKILL);
		case CALCULATE_AREA_OF_STORE:
			return extractRelevantTemplateDifficulty(AttributeConcept.AREA_CALCULATION_SKILL);
		case PAYING_FOR_FOOD:
			return extractRelevantTemplateDifficulty(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		case PAYING_FOR_MATERIAL:
			return extractRelevantTemplateDifficulty(AttributeConcept.AREA_CALCULATION_SKILL);
		case PRODUCT_TIME_UNTIL_COMPLETION:
			return extractRelevantTemplateDifficulty(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		case REPLACE:
			return extractRelevantTemplateDifficulty(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		case STORE_PRODUCT_ANIMAL:
			return extractRelevantTemplateDifficulty(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		case INCOME_VISITORS:
			return extractRelevantTemplateDifficulty(AttributeConcept.PERCENTAGE_CALCULATION_SKILL);
		default:
			break;
		}
		return 1;
	}

	private void addBuildsaNewThing(List<Templates> templates) {
		BuildThing buildThing = new BuildThing(wordProblem);
		for (Templates templateTypeTemp : BuildThing.getTemplateCompatibilityMap().keySet()) {
			possibleTemplates.add(templateTypeTemp);
		}
		connectedTemplates.add(() -> buildThing);

		for (Templates templateTypeTemp : BuildThing.getTemplateCompatibilityMap().keySet()) {
			templates.add(templateTypeTemp);
		}

	}

	private int addCalculateAreaOfStore(int accumulatedDifficulty, int templateDifficulty, int conceptDifficulty) {
		CalculateAreaOfStore calculateAreaOfStore = new CalculateAreaOfStore(wordProblem);
		int addedDifficultyScalingArea = 0;
		if (templateDifficulty >= 70) {

			addedDifficultyScalingArea += TemplateOverviewUtils.diffOver70(templateDifficulty, calculateAreaOfStore,
					accumulatedDifficulty, conceptDifficulty);

		} else if (templateDifficulty >= 35 && templateDifficulty < 70) {

			addedDifficultyScalingArea += TemplateOverviewUtils.diffBetween35And75(templateDifficulty,
					calculateAreaOfStore, accumulatedDifficulty, conceptDifficulty);
		}

		for (Templates templateTypeTemp : CalculateAreaOfStore.getTemplateCompatibilityMap().keySet()) {
			possibleTemplates.add(templateTypeTemp);
		}
		connectedTemplates.add(() -> calculateAreaOfStore);

		return addedDifficultyScalingArea;
	}

	private void addPayingForFood() {
		BuyingFood buyingFood = new BuyingFood(wordProblem);
		for (Templates templateTypeTemp : BuyingFood.getTemplateCompatibilityMap().keySet()) {
			possibleTemplates.add(templateTypeTemp);
		}
		connectedTemplates.add(() -> buyingFood);
	}

	private void addPayingForMaterial() {
		BuyingMaterial buyingMaterial = new BuyingMaterial(wordProblem);
		for (Templates templateTypeTemp : BuyingMaterial.getTemplateCompatibilityMap().keySet()) {
			possibleTemplates.add(templateTypeTemp);
		}
		connectedTemplates.add(() -> buyingMaterial);
	}

	private void addProductTimeUntilCompletion() {
		ProductTimeUntilDepletion productTimeUntilDepletion = new ProductTimeUntilDepletion(wordProblem);
		for (Templates templateTypeTemp : ProductTimeUntilDepletion.getTemplateCompatibilityMap().keySet()) {
			possibleTemplates.add(templateTypeTemp);
		}
		connectedTemplates.add(() -> productTimeUntilDepletion);
	}

	private int addIncomeVisitors(int accumulatedDifficulty, int templateDifficulty, int conceptDifficulty) {
		IncomeThroughVisitors incomeThroughVisitors = new IncomeThroughVisitors(wordProblem);
		int addedDifficultyScalingIncome = 0;
		if (templateDifficulty >= 70) {
			addedDifficultyScalingIncome += TemplateOverviewUtils.diffOver70Income(templateDifficulty,
					incomeThroughVisitors, conceptDifficulty, accumulatedDifficulty);

		} else if (templateDifficulty >= 35 && templateDifficulty < 70) {

			addedDifficultyScalingIncome += TemplateOverviewUtils.diffBetween35And70(templateDifficulty,
					incomeThroughVisitors, conceptDifficulty, accumulatedDifficulty);

		}

		for (Templates templateTypeTemp : IncomeThroughVisitors.getTemplateCompatibilityMap().keySet()) {
			possibleTemplates.add(templateTypeTemp);
		}
		connectedTemplates.add(() -> incomeThroughVisitors);
		return addedDifficultyScalingIncome;
	}

	private int addConnectedTemplateByEnum(Templates templateType, int accumulatedDifficulty, int templateDifficulty,
			int conceptDifficulty, List<Templates> templates) {
		switch (templateType) {
		case BUILDS_A_NEW_THING:
			addBuildsaNewThing(templates);
			break;
		case CALCULATE_AREA_OF_STORE:
			return addCalculateAreaOfStore(accumulatedDifficulty, templateDifficulty, conceptDifficulty);
		case PAYING_FOR_FOOD:
			addPayingForFood();
			break;
		case PAYING_FOR_MATERIAL:
			addPayingForMaterial();
			break;
		case PRODUCT_TIME_UNTIL_COMPLETION:
			addProductTimeUntilCompletion();
			break;
		case REPLACE:
			break;
		case STORE_PRODUCT_ANIMAL:
			break;
		case INCOME_VISITORS:
			return addIncomeVisitors(accumulatedDifficulty, templateDifficulty, conceptDifficulty);
		default:
			return 0;
		}
		return 0;
	}

	private void addSupportedTemplates() {
		for (int i = 1; i < 10; i++) {
			supportedTemplates.add(() -> new ReplaceValueByOther(wordProblem));
		}
	}

	public ArrayList<Supplier<BaseTemplate>> getBaseTemplates() {
		return baseTemplates;
	}

	public ArrayList<Supplier<ConnectedTemplate>> getConnectedTemplates() {
		return connectedTemplates;
	}

	public ArrayList<Supplier<SupportTemplate>> getSupportedTemplates() {
		return supportedTemplates;
	}

	public BaseTemplate getRandomBaseTemplate() {
		return baseTemplates.get(random.nextInt(baseTemplates.size())).get();
	}

	public ConnectedTemplate getRandomConnectedTemplate() {
		return connectedTemplates.get(random.nextInt(connectedTemplates.size())).get();
	}

	public SupportTemplate getRandomSupportTemplate() {
		return supportedTemplates.get(random.nextInt(supportedTemplates.size())).get();
	}

	/**
	 * retrieve all connected templates
	 * 
	 * @return allConnectedTemplates list of all connected templates
	 */
	public List<ConnectedTemplate> getAllConnectedTemplates() {
		List<ConnectedTemplate> allConnectedTemplates = new ArrayList<>();
		for (int i = 0; i < connectedTemplates.size(); i++) {
			allConnectedTemplates.add(connectedTemplates.get(i).get());
		}
		return allConnectedTemplates;
	}

	/**
	 * retrieve all support templates
	 * 
	 * @return allConnectedTemplates list of all support templates
	 */
	public List<SupportTemplate> getAllSupportTemplates() {
		List<SupportTemplate> allSupportTemplates = new ArrayList<>();
		for (int i = 0; i < supportedTemplates.size(); i++) {
			allSupportTemplates.add(supportedTemplates.get(i).get());
		}
		return allSupportTemplates;
	}

}
