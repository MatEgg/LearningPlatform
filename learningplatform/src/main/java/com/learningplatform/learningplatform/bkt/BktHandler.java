package com.learningplatform.learningplatform.bkt;

import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.models.service.UserService;
import com.learningplatform.learningplatform.models.skills.conceptual.ConceptualSkill;
import com.learningplatform.learningplatform.models.skills.numerical.AdditionSkill;
import com.learningplatform.learningplatform.models.skills.numerical.DivisionSkill;
import com.learningplatform.learningplatform.models.skills.numerical.MultiplicationSkill;
import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkill;
import com.learningplatform.learningplatform.models.skills.numerical.SubtractionSkill;
import com.learningplatform.learningplatform.tasks.WordProblem;

import learningplatform.learningplatform.calculation.MostDifficultyCalculation;

/**
 * First receiver of requests for updating the student's skill.
 *
 */
public class BktHandler {

	private static BktHandler bktHandlerInstance;
	private static User user;
	private static UserService userService;
	private static ConceptualSkill conceptualSkill;
	private static NumericalSkill numericalSkill;
	static float transit = 0.09f;
	static float slip = 0.09f;
	static float guess = 0.14f;

	private BktHandler() {

	}

	/**
	 * Lazy instantiation of the BktHandler instance.
	 * 
	 * @return BktHandler singleton
	 */
	public static BktHandler getInstance() {

		if (bktHandlerInstance == null) {
			bktHandlerInstance = new BktHandler();
			initialize();
			return bktHandlerInstance;
		} else {
			initialize();
			return bktHandlerInstance;
		}
	}

	/**
	 * Lazy instantiation of the BktHandler instance.
	 * 
	 * @return BktHandler singleton
	 */
	public static BktHandler getInstanceNewDao(DaoFactory daoFactory, int userId) {

		if (bktHandlerInstance == null) {
			bktHandlerInstance = new BktHandler();
			initializeWithDao(daoFactory, userId);
			return bktHandlerInstance;
		} else {
			initializeWithDao(daoFactory, userId);
			return bktHandlerInstance;
		}
	}

	private static void initialize() {
		user = WordProblem.getInstance().getDaoFactory().getUserService()
				.getUserByID(WordProblem.getInstance().getUser().getUid());
		userService = WordProblem.getInstance().getDaoFactory().getUserService();
		conceptualSkill = user.getConceptualSkill();
		numericalSkill = user.getNumericalSkill();
	}

	private static void initializeWithDao(DaoFactory daoFactory, int userId) {
		user = daoFactory.getUserService().getUserByID(userId);
		userService = daoFactory.getUserService();
		conceptualSkill = user.getConceptualSkill();
		numericalSkill = user.getNumericalSkill();
	}

	public static BktHandler getNewInstance() {
		bktHandlerInstance = new BktHandler();
		return bktHandlerInstance;
	}

	/**
	 * Target point of transferring update packets to make an update for conceptual
	 * skills.
	 * 
	 * @param bktUpdateConceptual the update packet that contains all the
	 *                            information for a potential update
	 */
	public User updateSkillMasteryConceptual(BktUpdateConceptual bktUpdateConceptual) {
		switch (bktUpdateConceptual.getBktTypeConceptual()) {
		case COMPUTATIONAL_RULES_SKILL:
			return updateComputationalRulesSkill(bktUpdateConceptual);
		case AREA_CALCULATION_SKILL:
			return updateAreaCalculationSkill(bktUpdateConceptual);
		case CALCULATE_PROBABILITIES_SKILL:
			return updateCalculateProbabilitiesSkill(bktUpdateConceptual);
		case CHOOSE_CORRECT_FORMULAS_SKILL:
			return updateChooseCorrectFormulasSkill(bktUpdateConceptual);
		case CHOOSE_CORRECT_INFORMATION_SKILL:
			return updateChooseCorrectInformationSkill(bktUpdateConceptual);
		case INSERT_CORRECT_VALUES_SKILL:
			return updateInsertCorrectValuesSkill(bktUpdateConceptual);
		case SOLVING_CARRY_TASKS_SKILL:
			return updateSolvingCarryTasksSkill(bktUpdateConceptual);
		case STEPS_NEEDED_SKILL:
			return updateStepsNeededSkill(bktUpdateConceptual);
		default:
			return null;
		}

	}

	/**
	 * Target point of transferring update packets to make an update for operational
	 * skills.
	 * 
	 * @param bktPotentialOperationalUpdate the update packet that contains all the
	 *                                      information for a potential update
	 */
	public User updateSkillMasteryOperational(BktPotentialOperationalUpdate bktPotentialOperationalUpdate) {
		switch (bktPotentialOperationalUpdate.getMostDifficultyCalculation().getOperator()) {
		case ADDITION:
			return checkIfUpdateAddition(bktPotentialOperationalUpdate);
		case SUBSTRACTION:
			return checkIfUpdateSubtraction(bktPotentialOperationalUpdate);
		case MULTIPLICATION:
			return checkIfUpdateMultiplication(bktPotentialOperationalUpdate);
		case DIVISION:
			return checkIfUpdateDivision(bktPotentialOperationalUpdate);
		default:
			return user;
		}
	}

	private User updateComputationalRulesSkill(BktUpdateConceptual bktUpdate) {
		int computationalRulesProficiency = conceptualSkill.getNumberSkill().getComputationalRulesSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(computationalRulesProficiency, bktUpdate.isResult(), slip,
				guess, transit);
		int level = BktHandlerUtils.extractLevel(computationalRulesProficiency);
		conceptualSkill.getNumberSkill().setComputationalRulesSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));
		userService.save(user);
		return user;

	}

	private User updateAreaCalculationSkill(BktUpdateConceptual bktUpdate) {
		int areaCalculationProficiency = conceptualSkill.getMeasurementSkill().getAreaCalculationSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(areaCalculationProficiency, bktUpdate.isResult(), slip,
				guess, transit);
		int level = BktHandlerUtils.extractLevel(areaCalculationProficiency);

		conceptualSkill.getMeasurementSkill().setAreaCalculationSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;
	}

	private User updateCalculateProbabilitiesSkill(BktUpdateConceptual bktUpdate) {

		int calculateProbabilitiesProficiency = conceptualSkill.getRandomnessSkill().getCalculateProbabilitiesSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(calculateProbabilitiesProficiency, bktUpdate.isResult(),
				slip, guess, transit);
		int level = BktHandlerUtils.extractLevel(calculateProbabilitiesProficiency);

		conceptualSkill.getRandomnessSkill().setCalculateProbabilitiesSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;

	}

	private User updateChooseCorrectFormulasSkill(BktUpdateConceptual bktUpdate) {

		int chooseCorrectFormulasProficiency = conceptualSkill.getSolvingTaskSkill().getChooseCorrectFormulasSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(chooseCorrectFormulasProficiency, bktUpdate.isResult(), slip,
				guess, transit);
		int level = BktHandlerUtils.extractLevel(chooseCorrectFormulasProficiency);

		conceptualSkill.getSolvingTaskSkill().setChooseCorrectFormulasSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;

	}

	private User updateChooseCorrectInformationSkill(BktUpdateConceptual bktUpdate) {

		int chooseCorrectInformationProficiency = conceptualSkill.getSolvingTaskSkill()
				.getChooseCorrectInformationSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(chooseCorrectInformationProficiency, bktUpdate.isResult(),
				slip, guess, transit);
		int level = BktHandlerUtils.extractLevel(chooseCorrectInformationProficiency);

		conceptualSkill.getSolvingTaskSkill().setChooseCorrectInformationSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;
	}

	private User updateInsertCorrectValuesSkill(BktUpdateConceptual bktUpdate) {

		int insertCorrectValuesProficiency = conceptualSkill.getSolvingTaskSkill().getInsertCorrectValuesSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(insertCorrectValuesProficiency, bktUpdate.isResult(), slip,
				guess, transit);
		int level = BktHandlerUtils.extractLevel(insertCorrectValuesProficiency);

		conceptualSkill.getSolvingTaskSkill().setInsertCorrectValuesSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;

	}

	private User updateSolvingCarryTasksSkill(BktUpdateConceptual bktUpdate) {
		int solvingCarryTasksProficiency = conceptualSkill.getGeneralPerformanceSkill().getSolvingCarryTasksSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(solvingCarryTasksProficiency, bktUpdate.isResult(), slip,
				guess, transit);
		int level = BktHandlerUtils.extractLevel(solvingCarryTasksProficiency);

		conceptualSkill.getGeneralPerformanceSkill().setSolvingCarryTasksSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;
	}

	private User updateStepsNeededSkill(BktUpdateConceptual bktUpdate) {
		int stepsNeededProficiency = conceptualSkill.getGeneralPerformanceSkill().getStepsNeededSkill();
		int updatedMastery = BktHandlerUtils.updateMastery(stepsNeededProficiency, bktUpdate.isResult(), slip, guess,
				transit);
		int level = BktHandlerUtils.extractLevel(stepsNeededProficiency);

		conceptualSkill.getGeneralPerformanceSkill().setStepsNeededSkill(BktHandlerUtils.calculateNewValue(level,
				updatedMastery, 10, user.getSettings().getSensibility(), bktUpdate.isResult()));

		userService.save(user);
		return user;
	}

	private User checkIfUpdateAddition(BktPotentialOperationalUpdate bktPotentialOperationalUpdate) {
		AdditionSkill additionSkill = numericalSkill.getAdditionSkill();

		int oldDecimalLevel = BktHandlerUtils.extractLevel(additionSkill.getNumericalSkillTypes().getDecimalHandling());
		int oldBigNumberLevel = BktHandlerUtils
				.extractLevel(additionSkill.getNumericalSkillTypes().getBigNumberHandling());
		int oldNumericalTypeLevel = BktHandlerUtils
				.extractLevel(additionSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		BktUpdateOperational bktUpdateOperational = BktHandlerUtils.checkIfLevelHigher(oldDecimalLevel,
				oldBigNumberLevel, oldNumericalTypeLevel, bktPotentialOperationalUpdate);

		if (bktUpdateOperational.isUpdate()) {
			return updateAdditionSkill(bktUpdateOperational);
		} else {
			return user;
		}
	}

	private User checkIfUpdateSubtraction(BktPotentialOperationalUpdate bktPotentialOperationalUpdate) {

		SubtractionSkill subtractionSkill = numericalSkill.getSubtractionSkill();

		int oldDecimalLevel = BktHandlerUtils
				.extractLevel(subtractionSkill.getNumericalSkillTypes().getDecimalHandling());
		int oldBigNumberLevel = BktHandlerUtils
				.extractLevel(subtractionSkill.getNumericalSkillTypes().getBigNumberHandling());
		int oldNumericalTypeLevel = BktHandlerUtils
				.extractLevel(subtractionSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		BktUpdateOperational bktUpdateOperational = BktHandlerUtils.checkIfLevelHigher(oldDecimalLevel,
				oldBigNumberLevel, oldNumericalTypeLevel, bktPotentialOperationalUpdate);

		if (bktUpdateOperational.isUpdate()) {
			return updateSubtractionSkill(bktUpdateOperational);
		} else {
			return user;
		}
	}

	private User checkIfUpdateMultiplication(BktPotentialOperationalUpdate bktPotentialOperationalUpdate) {

		MultiplicationSkill multiplicationSkill = numericalSkill.getMultiplicationSkill();

		int oldDecimalLevel = BktHandlerUtils
				.extractLevel(multiplicationSkill.getNumericalSkillTypes().getDecimalHandling());
		int oldBigNumberLevel = BktHandlerUtils
				.extractLevel(multiplicationSkill.getNumericalSkillTypes().getBigNumberHandling());
		int oldNumericalTypeLevel = BktHandlerUtils
				.extractLevel(multiplicationSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		BktUpdateOperational bktUpdateOperational = BktHandlerUtils.checkIfLevelHigher(oldDecimalLevel,
				oldBigNumberLevel, oldNumericalTypeLevel, bktPotentialOperationalUpdate);

		if (bktUpdateOperational.isUpdate()) {
			return updateMultiplicationSkill(bktUpdateOperational);
		} else {
			return user;
		}
	}

	private User checkIfUpdateDivision(BktPotentialOperationalUpdate bktPotentialOperationalUpdate) {

		DivisionSkill divisionSkill = numericalSkill.getDivisionSkill();

		int oldDecimalLevel = BktHandlerUtils.extractLevel(divisionSkill.getNumericalSkillTypes().getDecimalHandling());
		int oldBigNumberLevel = BktHandlerUtils
				.extractLevel(divisionSkill.getNumericalSkillTypes().getBigNumberHandling());
		int oldNumericalTypeLevel = BktHandlerUtils
				.extractLevel(divisionSkill.getNumericalSkillTypes().getNumericalTypeHandling());

		BktUpdateOperational bktUpdateOperational = BktHandlerUtils.checkIfLevelHigher(oldDecimalLevel,
				oldBigNumberLevel, oldNumericalTypeLevel, bktPotentialOperationalUpdate);

		if (bktUpdateOperational.isUpdate()) {
			return updateDivisionSkill(bktUpdateOperational);
		} else {
			return user;
		}
	}

	/**
	 * updates the addition skill of the student
	 * 
	 * @param bktUpdateOperational update packet
	 */
	private User updateAdditionSkill(BktUpdateOperational bktUpdateOperational) {
		AdditionSkill additionSkill = numericalSkill.getAdditionSkill();

		if (bktUpdateOperational.isDecimal()) {
			int decimalProficiency = additionSkill.getNumericalSkillTypes().getDecimalHandling();
			int updatedMasteryDecimal = BktHandlerUtils.updateMastery(decimalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int leveldecimal = BktHandlerUtils.extractLevel(decimalProficiency);

			additionSkill.getNumericalSkillTypes().setDecimalHandling(BktHandlerUtils.calculateNewValue(leveldecimal,
					updatedMasteryDecimal, 10, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isBigNumber()) {

			int bigNumberProficiency = additionSkill.getNumericalSkillTypes().getBigNumberHandling();
			int updatedMasteryBigNumber = BktHandlerUtils.updateMastery(bigNumberProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelBigNumber = BktHandlerUtils.extractLevel(bigNumberProficiency);

			additionSkill.getNumericalSkillTypes()
					.setBigNumberHandling(BktHandlerUtils.calculateNewValue(levelBigNumber, updatedMasteryBigNumber, 10,
							user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isNumericalType()) {

			int numericalProficiency = additionSkill.getNumericalSkillTypes().getNumericalTypeHandling();
			int updatedMasteryNumerical = BktHandlerUtils.updateMastery(numericalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelnumerical = BktHandlerUtils.extractLevel(numericalProficiency);

			additionSkill.getNumericalSkillTypes()
					.setNumericalTypeHandling(BktHandlerUtils.calculateNewValue(levelnumerical, updatedMasteryNumerical,
							4, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		userService.save(user);
		return user;
	}

	/**
	 * updates the subtraction skill of the student
	 * 
	 * @param bktUpdateOperational update packet
	 */
	private User updateSubtractionSkill(BktUpdateOperational bktUpdateOperational) {
		SubtractionSkill subtractionSkill = numericalSkill.getSubtractionSkill();

		if (bktUpdateOperational.isDecimal()) {
			int decimalProficiency = subtractionSkill.getNumericalSkillTypes().getDecimalHandling();
			int updatedMasteryDecimal = BktHandlerUtils.updateMastery(decimalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int leveldecimal = BktHandlerUtils.extractLevel(decimalProficiency);

			subtractionSkill.getNumericalSkillTypes().setDecimalHandling(BktHandlerUtils.calculateNewValue(leveldecimal,
					updatedMasteryDecimal, 10, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isBigNumber()) {

			int bigNumberProficiency = subtractionSkill.getNumericalSkillTypes().getBigNumberHandling();
			int updatedMasteryBigNumber = BktHandlerUtils.updateMastery(bigNumberProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelBigNumber = BktHandlerUtils.extractLevel(bigNumberProficiency);

			subtractionSkill.getNumericalSkillTypes()
					.setBigNumberHandling(BktHandlerUtils.calculateNewValue(levelBigNumber, updatedMasteryBigNumber, 10,
							user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isNumericalType()) {

			int numericalProficiency = subtractionSkill.getNumericalSkillTypes().getNumericalTypeHandling();
			int updatedMasteryNumerical = BktHandlerUtils.updateMastery(numericalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelnumerical = BktHandlerUtils.extractLevel(numericalProficiency);

			subtractionSkill.getNumericalSkillTypes()
					.setNumericalTypeHandling(BktHandlerUtils.calculateNewValue(levelnumerical, updatedMasteryNumerical,
							4, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}

		userService.save(user);
		return user;
	}

	/**
	 * updates the multiplication skill of the student
	 * 
	 * @param bktUpdateOperational update packet
	 */
	private User updateMultiplicationSkill(BktUpdateOperational bktUpdateOperational) {
		MultiplicationSkill multiplicationSkill = numericalSkill.getMultiplicationSkill();

		if (bktUpdateOperational.isDecimal()) {
			int decimalProficiency = multiplicationSkill.getNumericalSkillTypes().getDecimalHandling();
			int updatedMasteryDecimal = BktHandlerUtils.updateMastery(decimalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int leveldecimal = BktHandlerUtils.extractLevel(decimalProficiency);

			multiplicationSkill.getNumericalSkillTypes()
					.setDecimalHandling(BktHandlerUtils.calculateNewValue(leveldecimal, updatedMasteryDecimal, 10,
							user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isBigNumber()) {

			int bigNumberProficiency = multiplicationSkill.getNumericalSkillTypes().getBigNumberHandling();
			int updatedMasteryBigNumber = BktHandlerUtils.updateMastery(bigNumberProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelBigNumber = BktHandlerUtils.extractLevel(bigNumberProficiency);

			multiplicationSkill.getNumericalSkillTypes()
					.setBigNumberHandling(BktHandlerUtils.calculateNewValue(levelBigNumber, updatedMasteryBigNumber, 10,
							user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isNumericalType()) {

			int numericalProficiency = multiplicationSkill.getNumericalSkillTypes().getNumericalTypeHandling();
			int updatedMasteryNumerical = BktHandlerUtils.updateMastery(numericalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelnumerical = BktHandlerUtils.extractLevel(numericalProficiency);

			multiplicationSkill.getNumericalSkillTypes()
					.setNumericalTypeHandling(BktHandlerUtils.calculateNewValue(levelnumerical, updatedMasteryNumerical,
							4, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}

		userService.save(user);
		return user;
	}

	/**
	 * updates the division skill of the student
	 * 
	 * @param bktUpdateOperational update packet
	 */
	private User updateDivisionSkill(BktUpdateOperational bktUpdateOperational) {
		DivisionSkill divisionSkill = numericalSkill.getDivisionSkill();

		if (bktUpdateOperational.isDecimal()) {
			int decimalProficiency = divisionSkill.getNumericalSkillTypes().getDecimalHandling();
			int updatedMasteryDecimal = BktHandlerUtils.updateMastery(decimalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int leveldecimal = BktHandlerUtils.extractLevel(decimalProficiency);

			divisionSkill.getNumericalSkillTypes().setDecimalHandling(BktHandlerUtils.calculateNewValue(leveldecimal,
					updatedMasteryDecimal, 10, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isBigNumber()) {

			int bigNumberProficiency = divisionSkill.getNumericalSkillTypes().getBigNumberHandling();
			int updatedMasteryBigNumber = BktHandlerUtils.updateMastery(bigNumberProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelBigNumber = BktHandlerUtils.extractLevel(bigNumberProficiency);

			divisionSkill.getNumericalSkillTypes()
					.setBigNumberHandling(BktHandlerUtils.calculateNewValue(levelBigNumber, updatedMasteryBigNumber, 10,
							user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}
		if (bktUpdateOperational.isNumericalType()) {

			int numericalProficiency = divisionSkill.getNumericalSkillTypes().getNumericalTypeHandling();
			int updatedMasteryNumerical = BktHandlerUtils.updateMastery(numericalProficiency,
					bktUpdateOperational.isResult(), slip, guess, transit);
			int levelnumerical = BktHandlerUtils.extractLevel(numericalProficiency);

			divisionSkill.getNumericalSkillTypes()
					.setNumericalTypeHandling(BktHandlerUtils.calculateNewValue(levelnumerical, updatedMasteryNumerical,
							4, user.getSettings().getSensibility(), bktUpdateOperational.isResult()));

		}

		userService.save(user);
		return user;
	}

}