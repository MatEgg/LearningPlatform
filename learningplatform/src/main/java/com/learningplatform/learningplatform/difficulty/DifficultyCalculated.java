package com.learningplatform.learningplatform.difficulty;

import com.learningplatform.learningplatform.difficulty.conceptDifficulty.ComputationalRulesDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.MeasurementDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.PercentCalculationDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.ProbabilityCalculationDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptualDifficulty.ConceptualDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptualDifficulty.DifferentConceptsDifficultyCost;
import com.learningplatform.learningplatform.difficulty.conceptualDifficulty.DistractorDifficultyCost;
import com.learningplatform.learningplatform.difficulty.conceptualDifficulty.QuestionDifficultyDifficultyCost;
import com.learningplatform.learningplatform.difficulty.conceptualDifficulty.ReplaceDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.AdditionDifficulty;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DivisionDifficulty;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.MultiplicationDifficulty;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.SubtractionDifficulty;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.AdditionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.DivisionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.MultiplicationDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.OperationalDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.SubtractionDifficultyModel;
import com.learningplatform.learningplatform.models.skills.conceptual.ConceptualSkill;
import com.learningplatform.learningplatform.models.skills.conceptual.GeneralPerformanceSkill;
import com.learningplatform.learningplatform.models.skills.conceptual.MeasurementSkill;
import com.learningplatform.learningplatform.models.skills.conceptual.NumberSkill;
import com.learningplatform.learningplatform.models.skills.conceptual.RandomnessSkill;
import com.learningplatform.learningplatform.models.skills.conceptual.SolvingTaskSkill;
import com.learningplatform.learningplatform.models.skills.numerical.AdditionSkill;
import com.learningplatform.learningplatform.models.skills.numerical.DivisionSkill;
import com.learningplatform.learningplatform.models.skills.numerical.MultiplicationSkill;
import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkill;
import com.learningplatform.learningplatform.models.skills.numerical.SubtractionSkill;

/**
 * Representation of difficulty settings that will be used at runtime.
 *
 */
public class DifficultyCalculated {

	private ConceptualDifficulty conceptualDifficulty;
	private ComputationalRulesDifficulty computationalRulesDifficulty;
	private MeasurementDifficulty measurementDifficulty;
	private PercentCalculationDifficulty percentCalculationDifficulty;
	private ProbabilityCalculationDifficulty probabilityCalculationDifficulty;
	private AdditionDifficulty additionDifficulty;
	private SubtractionDifficulty subtractionDifficulty;
	private MultiplicationDifficulty multiplicationDifficulty;
	private DivisionDifficulty divisionDifficulty;
	private Difficulty difficulty;
	private int decimalEnabled;
	private Settings settings;
	private User user;

	private static final String NUMERICAL_DIFFICULTY = "Numerical Difficulty";
	private static final String DIGITCOUNT_DIFFICULTY = "Digit Count Difficulty";
	private static final String DECIMALCOUNT_DIFFICULTY = "Decimal Count Difficulty";

	public DifficultyCalculated(User user, Settings settings) {
		this.user = user;
		this.settings = settings;
		decimalEnabled = settings.getDecimalEnabled();
		difficulty = settings.getDifficulty();
		if (settings.getAutomaticEnabled() == 1) {
			automaticMapping();
		} else {
			manualMapping();
		}
		printDifficultyLevels();
	}

	public DifficultyCalculated() {

	}

	private void manualMapping() {
		conceptualDifficulty = new ConceptualDifficulty(difficulty.getConceptualDifficultyModel());

		computationalRulesDifficulty = new ComputationalRulesDifficulty(difficulty.getConceptDifficultyModel());
		measurementDifficulty = new MeasurementDifficulty(difficulty.getConceptDifficultyModel());
		percentCalculationDifficulty = new PercentCalculationDifficulty(difficulty.getConceptDifficultyModel());
		probabilityCalculationDifficulty = new ProbabilityCalculationDifficulty(difficulty.getConceptDifficultyModel());

		additionDifficulty = new AdditionDifficulty(
				difficulty.getOperationalDifficultyModel().getAdditionDifficultyModel(), settings.getDecimalEnabled());
		subtractionDifficulty = new SubtractionDifficulty(
				difficulty.getOperationalDifficultyModel().getSubtractionDifficultyModel(),
				settings.getDecimalEnabled());
		multiplicationDifficulty = new MultiplicationDifficulty(
				difficulty.getOperationalDifficultyModel().getMultiplicationDifficultyModel(),
				settings.getDecimalEnabled());
		divisionDifficulty = new DivisionDifficulty(
				difficulty.getOperationalDifficultyModel().getDivisionDifficultyModel(), settings.getDecimalEnabled());

		conceptualDifficulty.determineDifficultySettings();
		computationalRulesDifficulty.determineDifficultySettings();
		measurementDifficulty.determineDifficultySettings();
		percentCalculationDifficulty.determineDifficultySettings();
		probabilityCalculationDifficulty.determineDifficultySettings();

		additionDifficulty.determineDifficultySettings();
		subtractionDifficulty.determineDifficultySettings();
		multiplicationDifficulty.determineDifficultySettings();
		divisionDifficulty.determineDifficultySettings();
	}

	private void automaticMapping() {
		automaticConceptMapping();
		automaticConceptualMapping();
		automaticOperationalMapping();
	}

	private void automaticConceptMapping() {
		ConceptualSkill conceptualSkill = user.getConceptualSkill();
		MeasurementSkill measurementSkill = conceptualSkill.getMeasurementSkill();
		NumberSkill numberSkill = conceptualSkill.getNumberSkill();

		computationalRulesDifficulty = new ComputationalRulesDifficulty();

		int computationalRulesLevel = extractLevel((long) (numberSkill.getComputationalRulesSkill()));

		int measurementLevel = extractLevel((long) (measurementSkill.getAreaCalculationSkill()));

		computationalRulesDifficulty.getComputationalRulesDifficultyCost().setLevel(computationalRulesLevel);

		measurementDifficulty = new MeasurementDifficulty();
		measurementDifficulty.getMeasurementDifficultyCost().setLevel(measurementLevel);

		MultiplicationSkill multiplicationSkill = user.getNumericalSkill().getMultiplicationSkill();
		int percentCalculationLevel = extractLevel(
				(long) ((multiplicationSkill.getNumericalSkillTypes().getDecimalHandling() / 4) * 10));

		RandomnessSkill randomnessSkill = conceptualSkill.getRandomnessSkill();
		int randomnessLevel = extractLevel((long) (randomnessSkill.getCalculateProbabilitiesSkill()));

		percentCalculationDifficulty = new PercentCalculationDifficulty();
		percentCalculationDifficulty.getPercentCalculationDifficultyCost().setLevel(percentCalculationLevel);

		probabilityCalculationDifficulty = new ProbabilityCalculationDifficulty();
		probabilityCalculationDifficulty.getProbabilityCalculationDifficultyCost().setLevel(randomnessLevel);

		ConceptDifficultyModel conceptDifficultyModel = difficulty.getConceptDifficultyModel();

		conceptDifficultyModel
				.setComputationalRulesDifficulty(computationalRulesDifficulty.determineManualDifficulty());
		conceptDifficultyModel.setMeasurementDifficulty(measurementDifficulty.determineManualDifficulty());
		conceptDifficultyModel.setPercentDifficulty(percentCalculationDifficulty.determineManualDifficulty());
		conceptDifficultyModel.setProbabilityDifficulty(probabilityCalculationDifficulty.determineManualDifficulty());

	}

	private void automaticConceptualMapping() {

		conceptualDifficulty = new ConceptualDifficulty();

		ConceptualSkill conceptualSkill = user.getConceptualSkill();

		ReplaceDifficultyCost replaceDifficultyCost = conceptualDifficulty.getReplaceDifficultyCost();
		DifferentConceptsDifficultyCost differentConceptsDifficultyCost = conceptualDifficulty
				.getDifferentConceptsDifficultyCost();
		QuestionDifficultyDifficultyCost questionDifficultyDifficultyCost = conceptualDifficulty
				.getQuestionDifficultyDifficultyCost();

		GeneralPerformanceSkill generalPerformanceSkill = conceptualSkill.getGeneralPerformanceSkill();

		SolvingTaskSkill solvingTaskSkill = conceptualSkill.getSolvingTaskSkill();
		float solvingCarryTasks = generalPerformanceSkill.getSolvingCarryTasksSkill();
		float stepsNeeded = generalPerformanceSkill.getStepsNeededSkill();
		float chooseCorrectFormulas = solvingTaskSkill.getChooseCorrectFormulasSkill();
		float chooseCorrectInformation = solvingTaskSkill.getChooseCorrectInformationSkill();
		float insertCorrectValues = solvingTaskSkill.getInsertCorrectValuesSkill();

		int replaceLevel = extractLevel((long) ((solvingCarryTasks * 0.8) + (insertCorrectValues * 0.2)));

		int differentConceptsLevel = Math.max(2,
				extractLevel((long) ((solvingCarryTasks * 0.8) + (chooseCorrectFormulas * 0.2))));

		int questionDifficultyLevel = extractLevel((long) ((solvingCarryTasks * 0.8) + (stepsNeeded * 0.2)));

		DistractorDifficultyCost distractorDifficultyCost = conceptualDifficulty.getDistractorDifficultyCost();
		int distractorDifficultyLevel = extractLevel((long) (chooseCorrectInformation));

		replaceDifficultyCost.setLevel(replaceLevel);
		differentConceptsDifficultyCost.setLevel(differentConceptsLevel);
		questionDifficultyDifficultyCost.setLevel(questionDifficultyLevel);
		distractorDifficultyCost.setLevel(distractorDifficultyLevel);

		ConceptualDifficultyModel conceptualDifficultyModel = difficulty.getConceptualDifficultyModel();

		conceptualDifficultyModel.setReplaceDifficulty(replaceLevel * 10);
		conceptualDifficultyModel.setDifferentConceptsDifficulty(differentConceptsLevel * 10);
		conceptualDifficultyModel.setQuestionDifficulty(questionDifficultyLevel * 10);
		conceptualDifficultyModel.setDistractorsDifficulty(distractorDifficultyLevel * 10);
		conceptualDifficultyModel.setConceptualDifficulty(conceptualDifficulty.determineManualDifficulty());

	}

	private int extractLevel(long skill) {

		if (skill >= 1000) {
			return 10;
		}
		return String.valueOf(Math.abs(skill)).charAt(0) - '0';
	}

	private int extractLevel(int skillLevel) {
		if (skillLevel >= 1000) {
			return 10;
		} else if (skillLevel < 100) {
			return 1;
		}
		return String.valueOf(skillLevel).charAt(0) - '0';
	}

	private void automaticOperationalMapping() {
		NumericalSkill numericalSkill = user.getNumericalSkill();
		AdditionSkill additionSkill = numericalSkill.getAdditionSkill();
		OperationalDifficultyModel operationalDifficultyModel = difficulty.getOperationalDifficultyModel();

		additionDifficulty = new AdditionDifficulty();

		int decimalAddition = extractLevel(additionSkill.getNumericalSkillTypes().getDecimalHandling());
		int bigDigitAddition = extractLevel(additionSkill.getNumericalSkillTypes().getBigNumberHandling());
		int numericalAddition = extractLevel(additionSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		additionDifficulty.getDecimalCountDifficultyCost().setLevel(decimalAddition);
		additionDifficulty.getDigitCountDifficultyCost().setLevel(bigDigitAddition);
		additionDifficulty.getNumberTypeDifficultyCost().setLevel(Math.max(1, numericalAddition));

		int difficultyAddition = additionDifficulty.determineManualDifficulty();

		AdditionDifficultyModel additionDifficultyModel = operationalDifficultyModel.getAdditionDifficultyModel();
		additionDifficulty.setDifficulty(difficultyAddition);
		additionDifficultyModel.setOverallDifficulty(difficultyAddition);
		additionDifficultyModel.getNumericalSkillTypesDifficultyModel().setDecimalDifficulty(decimalAddition * 10);
		additionDifficultyModel.getNumericalSkillTypesDifficultyModel().setMagnitudeDifficulty(bigDigitAddition * 10);
		additionDifficultyModel.getNumericalSkillTypesDifficultyModel().setNumericalDifficulty(numericalAddition * 10);

		subtractionDifficulty = new SubtractionDifficulty();

		SubtractionSkill subtractionSkill = numericalSkill.getSubtractionSkill();
		int decimalSubtraction = extractLevel(subtractionSkill.getNumericalSkillTypes().getDecimalHandling());
		int bigdigitSubtraction = extractLevel(subtractionSkill.getNumericalSkillTypes().getBigNumberHandling());
		int numericalSubtraction = extractLevel(subtractionSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		subtractionDifficulty.getDecimalCountDifficultyCost().setLevel(decimalSubtraction);
		subtractionDifficulty.getDigitCountDifficultyCost().setLevel(bigdigitSubtraction);
		subtractionDifficulty.getNumberTypeDifficultyCost().setLevel(Math.max(1, numericalSubtraction));
		int difficultySubtraction = subtractionDifficulty.determineManualDifficulty();
		subtractionDifficulty.setDifficulty(difficultySubtraction);

		SubtractionDifficultyModel subtractionDifficultyModel = operationalDifficultyModel
				.getSubtractionDifficultyModel();
		subtractionDifficultyModel.setOverallDifficulty(difficultySubtraction);
		subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel()
				.setDecimalDifficulty(decimalSubtraction * 10);
		subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel()
				.setMagnitudeDifficulty(bigdigitSubtraction * 10);
		subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel()
				.setNumericalDifficulty(numericalSubtraction * 10);

		MultiplicationSkill multiplicationSkill = numericalSkill.getMultiplicationSkill();
		multiplicationDifficulty = new MultiplicationDifficulty();
		int decimalMultiplication = extractLevel(multiplicationSkill.getNumericalSkillTypes().getDecimalHandling());
		int bigdigitMultiplication = extractLevel(multiplicationSkill.getNumericalSkillTypes().getBigNumberHandling());
		int numericalMultiplication = extractLevel(
				multiplicationSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		multiplicationDifficulty.getDecimalCountDifficultyCost().setLevel(decimalMultiplication);
		multiplicationDifficulty.getDigitCountDifficultyCost().setLevel(bigdigitMultiplication);
		multiplicationDifficulty.getNumberTypeDifficultyCost().setLevel(Math.max(1, numericalMultiplication));
		int difficultyMultiplication = multiplicationDifficulty.determineManualDifficulty();
		multiplicationDifficulty.setDifficulty(difficultyMultiplication);

		MultiplicationDifficultyModel multiplicationDifficultyModel = operationalDifficultyModel
				.getMultiplicationDifficultyModel();
		multiplicationDifficultyModel.setOverallDifficulty(difficultyMultiplication);
		multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel()
				.setDecimalDifficulty(decimalMultiplication * 10);
		multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel()
				.setMagnitudeDifficulty(bigdigitMultiplication * 10);
		multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel()
				.setNumericalDifficulty(numericalMultiplication * 10);

		DivisionSkill divisionSkill = numericalSkill.getDivisionSkill();
		divisionDifficulty = new DivisionDifficulty();
		int decimalDivision = extractLevel(divisionSkill.getNumericalSkillTypes().getDecimalHandling());
		int bigdigitDivision = extractLevel(divisionSkill.getNumericalSkillTypes().getBigNumberHandling());
		int numericalDivision = extractLevel(divisionSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		divisionDifficulty.getDecimalCountDifficultyCost().setLevel(decimalDivision);
		divisionDifficulty.getDigitCountDifficultyCost().setLevel(bigdigitDivision);
		divisionDifficulty.getNumberTypeDifficultyCost().setLevel(Math.max(1, numericalDivision));
		int difficultyDivision = divisionDifficulty.determineManualDifficulty();
		subtractionDifficulty.setDifficulty(difficultyDivision);

		DivisionDifficultyModel divisionDifficultyModel = operationalDifficultyModel.getDivisionDifficultyModel();
		divisionDifficultyModel.setOverallDifficulty(difficultyDivision);
		divisionDifficultyModel.getNumericalSkillTypesDifficultyModel().setDecimalDifficulty(decimalDivision * 10);
		divisionDifficultyModel.getNumericalSkillTypesDifficultyModel().setMagnitudeDifficulty(bigdigitDivision * 10);
		divisionDifficultyModel.getNumericalSkillTypesDifficultyModel().setNumericalDifficulty(numericalDivision * 10);

	}

	public ConceptualDifficulty getConceptualDifficulty() {
		return conceptualDifficulty;
	}

	public void setConceptualDifficulty(ConceptualDifficulty conceptualDifficulty) {
		this.conceptualDifficulty = conceptualDifficulty;
	}

	/**
	 * Prints out the difficulty levels.
	 */
	public void printDifficultyLevels() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----------------------------------  \n");
		sb.append("\n");
		sb.append("Levels and Difficulties \n");
		sb.append("Concept Difficulties:  \n");
		sb.append("Computational Rules: "
				+ computationalRulesDifficulty.getComputationalRulesDifficultyCost().getLevel() + "\n");
		sb.append("Measurement: " + measurementDifficulty.getMeasurementDifficultyCost().getLevel() + "\n");
		sb.append("Percent Calculation: "
				+ percentCalculationDifficulty.getPercentCalculationDifficultyCost().getLevel() + "\n");
		sb.append("Probability Calculation: "
				+ probabilityCalculationDifficulty.getProbabilityCalculationDifficultyCost().getLevel() + "\n");
		sb.append("\n");
		sb.append("Conceptual Difficulty:  \n");
		sb.append("ReplaceDifficultyCost: " + conceptualDifficulty.getReplaceDifficultyCost().getLevel() + "\n");
		sb.append("\n");
		sb.append("Operational Difficulty:  \n");
		sb.append("Addition Difficulty:  \n");
		if (decimalEnabled == 1) {
			sb.append(DECIMALCOUNT_DIFFICULTY + ": " + additionDifficulty.getDecimalCountDifficultyCost().getLevel()
					+ "\n");
		}
		sb.append(DIGITCOUNT_DIFFICULTY + ": " + additionDifficulty.getDigitCountDifficultyCost().getLevel() + "\n");
		sb.append(NUMERICAL_DIFFICULTY + ": " + additionDifficulty.getNumberTypeDifficultyCost().getLevel() + "\n");
		sb.append("Subtraction Difficulty:  \n");
		if (decimalEnabled == 1) {
			sb.append(DECIMALCOUNT_DIFFICULTY + ": " + subtractionDifficulty.getDecimalCountDifficultyCost().getLevel()
					+ "\n");
		}
		sb.append(DIGITCOUNT_DIFFICULTY + ": " + subtractionDifficulty.getDigitCountDifficultyCost().getLevel() + "\n");
		sb.append(NUMERICAL_DIFFICULTY + ": " + subtractionDifficulty.getNumberTypeDifficultyCost().getLevel() + "\n");
		sb.append("Multiplication Difficulty:  \n");
		if (decimalEnabled == 1) {
			sb.append("Decimal Count Difficulty: " + multiplicationDifficulty.getDecimalCountDifficultyCost().getLevel()
					+ "\n");
		}
		sb.append(DIGITCOUNT_DIFFICULTY + ": " + multiplicationDifficulty.getDigitCountDifficultyCost().getLevel()
				+ "\n");
		sb.append(
				NUMERICAL_DIFFICULTY + ": " + multiplicationDifficulty.getNumberTypeDifficultyCost().getLevel() + "\n");
		sb.append("Division Difficulty:  \n");
		if (decimalEnabled == 1) {
			sb.append("Decimal Count Difficulty: " + divisionDifficulty.getDecimalCountDifficultyCost().getLevel()
					+ "\n");
		}
		sb.append(DIGITCOUNT_DIFFICULTY + ": " + divisionDifficulty.getDigitCountDifficultyCost().getLevel() + "\n");
		sb.append(NUMERICAL_DIFFICULTY + ": " + divisionDifficulty.getNumberTypeDifficultyCost().getLevel() + "\n");
	}

	public ComputationalRulesDifficulty getComputationalRulesDifficulty() {
		return computationalRulesDifficulty;
	}

	public void setComputationalRulesDifficulty(ComputationalRulesDifficulty computationalRulesDifficulty) {
		this.computationalRulesDifficulty = computationalRulesDifficulty;
	}

	public MeasurementDifficulty getMeasurementDifficulty() {
		return measurementDifficulty;
	}

	public void setMeasurementDifficulty(MeasurementDifficulty measurementDifficulty) {
		this.measurementDifficulty = measurementDifficulty;
	}

	public PercentCalculationDifficulty getPercentCalculationDifficulty() {
		return percentCalculationDifficulty;
	}

	public void setPercentCalculationDifficulty(PercentCalculationDifficulty percentCalculationDifficulty) {
		this.percentCalculationDifficulty = percentCalculationDifficulty;
	}

	public ProbabilityCalculationDifficulty getProbabilityCalculationDifficulty() {
		return probabilityCalculationDifficulty;
	}

	public void setProbabilityCalculationDifficulty(ProbabilityCalculationDifficulty probabilityCalculationDifficulty) {
		this.probabilityCalculationDifficulty = probabilityCalculationDifficulty;
	}

	public AdditionDifficulty getAdditionDifficulty() {
		return additionDifficulty;
	}

	public void setAdditionDifficulty(AdditionDifficulty additionDifficulty) {
		this.additionDifficulty = additionDifficulty;
	}

	public SubtractionDifficulty getSubtractionDifficulty() {
		return subtractionDifficulty;
	}

	public void setSubtractionDifficulty(SubtractionDifficulty subtractionDifficulty) {
		this.subtractionDifficulty = subtractionDifficulty;
	}

	public MultiplicationDifficulty getMultiplicationDifficulty() {
		return multiplicationDifficulty;
	}

	public void setMultiplicationDifficulty(MultiplicationDifficulty multiplicationDifficulty) {
		this.multiplicationDifficulty = multiplicationDifficulty;
	}

	public DivisionDifficulty getDivisionDifficulty() {
		return divisionDifficulty;
	}

	public void setDivisionDifficulty(DivisionDifficulty divisionDifficulty) {
		this.divisionDifficulty = divisionDifficulty;
	}

	public Boolean getDecimalenabled() {
		return decimalEnabled == 1;
	}

	public void setDecimalEnabled(int decimalEnabled) {
		this.decimalEnabled = decimalEnabled;
	}
}
