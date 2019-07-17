package com.learningplatform.learningplatform.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningplatform.learningplatform.guiObjects.AdditionGui;
import com.learningplatform.learningplatform.guiObjects.DivisionGui;
import com.learningplatform.learningplatform.guiObjects.MultiplicationGui;
import com.learningplatform.learningplatform.guiObjects.StatsGui;
import com.learningplatform.learningplatform.guiObjects.SubtractionGui;
import com.learningplatform.learningplatform.guiObjects.UserGui;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.models.dao.difficulty.concept.ConceptDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.conceptual.ConceptualDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.AdditionDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.DivisionDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.MultiplicationDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.NumericalSkillsTypesDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.OperationalDifficultyDao;
import com.learningplatform.learningplatform.models.dao.difficulty.operational.SubtractionDifficultyDao;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.AdditionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.DivisionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.MultiplicationDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.NumericalSkillTypesDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.OperationalDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.SubtractionDifficultyModel;
import com.learningplatform.learningplatform.models.service.DifficultyService;
import com.learningplatform.learningplatform.models.service.SecurityService;
import com.learningplatform.learningplatform.models.service.SettingsService;
import com.learningplatform.learningplatform.models.service.UserService;
import com.learningplatform.learningplatform.models.service.skills.conceptual.ConceptualSkillService;
import com.learningplatform.learningplatform.models.service.skills.conceptual.GeneralPerformanceSkillService;
import com.learningplatform.learningplatform.models.service.skills.conceptual.MeasurementSkillService;
import com.learningplatform.learningplatform.models.service.skills.conceptual.NumberSkillService;
import com.learningplatform.learningplatform.models.service.skills.conceptual.RandomnessSkillService;
import com.learningplatform.learningplatform.models.service.skills.conceptual.SolvingTaskSkillService;
import com.learningplatform.learningplatform.models.service.skills.numerical.AdditionSkillService;
import com.learningplatform.learningplatform.models.service.skills.numerical.DivisionSkillService;
import com.learningplatform.learningplatform.models.service.skills.numerical.MultiplicationSkillService;
import com.learningplatform.learningplatform.models.service.skills.numerical.NumericalSkillService;
import com.learningplatform.learningplatform.models.service.skills.numerical.NumericalSkillTypesService;
import com.learningplatform.learningplatform.models.service.skills.numerical.SubtractionSkillService;
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
import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkillTypes;
import com.learningplatform.learningplatform.models.skills.numerical.SubtractionSkill;
import com.learningplatform.learningplatform.user.UserHandler;

/**
 * Controller for everything regarding users.
 *
 */
/**
 * @author Mathias
 *
 */

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private NumericalSkillTypesService numericalSkillTypesService;

	@Autowired
	private AdditionSkillService additionSkillService;

	@Autowired
	private SubtractionSkillService subtractionSkillService;

	@Autowired
	private MultiplicationSkillService multiplicationSkillService;

	@Autowired
	private DivisionSkillService divisionSkillService;

	@Autowired
	private GeneralPerformanceSkillService generalPerformanceSkillService;

	@Autowired
	private MeasurementSkillService measurementSkillService;

	@Autowired
	private NumberSkillService numberSkillService;

	@Autowired
	private RandomnessSkillService randomnessSkillService;

	@Autowired
	private SolvingTaskSkillService solvingTaskSkillService;

	@Autowired
	private NumericalSkillService numericalSkillService;

	@Autowired
	private ConceptualSkillService conceptualSkillService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private DifficultyService diffService;

	@Autowired
	AdditionDifficultyDao additionDifficultyDao;

	@Autowired
	SubtractionDifficultyDao subtractionDifficultyDao;

	@Autowired
	MultiplicationDifficultyDao multiplicationDifficultyDao;

	@Autowired
	DivisionDifficultyDao divisionDifficultyDao;

	@Autowired
	NumericalSkillsTypesDifficultyDao numericalSkillsTypesDifficultyDao;

	@Autowired
	OperationalDifficultyDao operationalDifficultyDao;

	@Autowired
	ConceptDifficultyDao conceptDifficultyDao;

	@Autowired
	ConceptualDifficultyDao conceptualDifficultyDao;

	@Autowired
	SettingsService settingsService;

	UserHandler userHandler;

	/**
	 * API target point to register user.
	 * 
	 * @param userGui information about the user form from the client
	 */
	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/registerUser")
	public void registerUser(@RequestBody UserGui userGui) {

		Settings settings = new Settings(0, addNewDifficulty(), 1);
		settingsService.save(settings);

		User user = new User(userGui.getName(), "123", settings, addNewNumericalSkills(), addNewConceptualSkills());
		userService.save(user);
		securityService.autologin(userGui.getName(), "123");

	}

	/**
	 * add new difficulty settings to the database of a new user
	 * 
	 * @return Difficulty new Difficulty settings
	 */
	private Difficulty addNewDifficulty() {
		ConceptualDifficultyModel conceptualDifficultyModel = new ConceptualDifficultyModel(50, 50, 50, 50, 50, 0);
		ConceptDifficultyModel conceptDifficultyModel = new ConceptDifficultyModel(50, 50, 50, 50);

		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelAddition = new NumericalSkillTypesDifficultyModel(
				50, 50, 50);
		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelSubtraction = new NumericalSkillTypesDifficultyModel(
				50, 50, 50);
		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelMultiplication = new NumericalSkillTypesDifficultyModel(
				50, 50, 50);
		NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModelDivision = new NumericalSkillTypesDifficultyModel(
				50, 50, 50);

		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelAddition);
		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelSubtraction);
		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelMultiplication);
		numericalSkillsTypesDifficultyDao.save(numericalSkillTypesDifficultyModelDivision);

		AdditionDifficultyModel additionDifficultyModel = new AdditionDifficultyModel(1,
				numericalSkillTypesDifficultyModelAddition, 0);
		SubtractionDifficultyModel subtractionDifficultyModel = new SubtractionDifficultyModel(1,
				numericalSkillTypesDifficultyModelSubtraction, 0);
		MultiplicationDifficultyModel multiplicationDifficultyModel = new MultiplicationDifficultyModel(1,
				numericalSkillTypesDifficultyModelMultiplication, 0);
		DivisionDifficultyModel divisionDifficultyModel = new DivisionDifficultyModel(1,
				numericalSkillTypesDifficultyModelDivision, 0);

		additionDifficultyDao.save(additionDifficultyModel);
		subtractionDifficultyDao.save(subtractionDifficultyModel);
		multiplicationDifficultyDao.save(multiplicationDifficultyModel);
		divisionDifficultyDao.save(divisionDifficultyModel);

		OperationalDifficultyModel operationalDifficultyModel = new OperationalDifficultyModel(additionDifficultyModel,
				subtractionDifficultyModel, multiplicationDifficultyModel, divisionDifficultyModel);

		operationalDifficultyDao.save(operationalDifficultyModel);
		conceptDifficultyDao.save(conceptDifficultyModel);
		conceptualDifficultyDao.save(conceptualDifficultyModel);

		Difficulty difficulty = new Difficulty(conceptualDifficultyModel, operationalDifficultyModel,
				conceptDifficultyModel);
		diffService.save(difficulty);
		return difficulty;

	}

	/**
	 * create new NumericalSkill object for a new user.
	 * 
	 * @return NumericalSkill new numerical skill object
	 */
	private NumericalSkill addNewNumericalSkills() {
		NumericalSkillTypes numericalSkillTypesAddition = new NumericalSkillTypes();
		NumericalSkillTypes numericalSkillTypesSubtraction = new NumericalSkillTypes();
		NumericalSkillTypes numericalSkillTypesMultiplication = new NumericalSkillTypes();
		NumericalSkillTypes numericalSkillTypesDivision = new NumericalSkillTypes();

		numericalSkillTypesService.save(numericalSkillTypesAddition);
		numericalSkillTypesService.save(numericalSkillTypesSubtraction);
		numericalSkillTypesService.save(numericalSkillTypesMultiplication);
		numericalSkillTypesService.save(numericalSkillTypesDivision);

		AdditionSkill additionSkill = new AdditionSkill(numericalSkillTypesAddition);
		SubtractionSkill subtractionSkill = new SubtractionSkill(numericalSkillTypesSubtraction);
		MultiplicationSkill multiplicationSkill = new MultiplicationSkill(numericalSkillTypesMultiplication);
		DivisionSkill divisionSkill = new DivisionSkill(numericalSkillTypesDivision);

		additionSkillService.save(additionSkill);
		subtractionSkillService.save(subtractionSkill);
		multiplicationSkillService.save(multiplicationSkill);
		divisionSkillService.save(divisionSkill);

		NumericalSkill numericalSkill = new NumericalSkill(additionSkill, subtractionSkill, multiplicationSkill,
				divisionSkill);

		numericalSkillService.save(numericalSkill);
		return numericalSkill;
	}

	/**
	 * create new ConceptualSkill object for a new user.
	 * 
	 * @return ConceptualSkill new conceptual skill object
	 */
	private ConceptualSkill addNewConceptualSkills() {
		GeneralPerformanceSkill generalPerformanceSkill = new GeneralPerformanceSkill();
		MeasurementSkill measurementSkill = new MeasurementSkill();
		NumberSkill numberSkill = new NumberSkill();
		RandomnessSkill randomnessSkill = new RandomnessSkill();
		SolvingTaskSkill solvingTaskSkill = new SolvingTaskSkill();

		generalPerformanceSkillService.save(generalPerformanceSkill);
		measurementSkillService.save(measurementSkill);
		numberSkillService.save(numberSkill);
		randomnessSkillService.save(randomnessSkill);
		solvingTaskSkillService.save(solvingTaskSkill);

		ConceptualSkill conceptualSkill = new ConceptualSkill(numberSkill, measurementSkill, randomnessSkill,
				solvingTaskSkill, generalPerformanceSkill);

		conceptualSkillService.save(conceptualSkill);
		return conceptualSkill;
	}

	/**
	 * gets the list of all users.
	 * 
	 * @return allUsersGui list of all users
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getUsers")
	public List<UserGui> sum() {
		List<User> allUsers = userService.getAllUsers();
		List<UserGui> allUsersGui = new ArrayList<>();
		for (User user : allUsers) {
			allUsersGui.add(new UserGui(user.getName()));
		}
		return allUsersGui;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getLoggedInUser")
	public UserGui getLoggedInUser() {
		return new UserGui(UserHandler.getInstance().getCurrentLoggedUser());
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/loginUser")
	public void loginUser(@RequestBody UserGui userGui) {
		securityService.autologin(userGui.getName(), "123");
		UserHandler.getInstance().setCurrentLoggedUser(securityService.findLoggedInUsername());
	}

	/**
	 * gets all the statistics about a user.
	 * 
	 * @return statsGui statistics about the user
	 */
	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/getStats")
	public StatsGui getStats() {
		StatsGui statsGui = new StatsGui();
		User user = userService.findByUsername(UserHandler.getInstance().getCurrentLoggedUser());
		ConceptualSkill conceptualSkill = user.getConceptualSkill();
		NumericalSkill numericalSkill = user.getNumericalSkill();

		AdditionGui additionGui = new AdditionGui(numericalSkill.getAdditionSkill());
		SubtractionGui subtractionGui = new SubtractionGui(numericalSkill.getSubtractionSkill());
		MultiplicationGui multiplicationGui = new MultiplicationGui(numericalSkill.getMultiplicationSkill());
		DivisionGui divisionGui = new DivisionGui(numericalSkill.getDivisionSkill());

		GeneralPerformanceSkill generalPerformanceSkill = conceptualSkill.getGeneralPerformanceSkill();
		MeasurementSkill measurementSkill = conceptualSkill.getMeasurementSkill();
		RandomnessSkill randomnessSkill = conceptualSkill.getRandomnessSkill();
		SolvingTaskSkill solvingTaskSkill = conceptualSkill.getSolvingTaskSkill();
		NumberSkill numberSkill = conceptualSkill.getNumberSkill();

		statsGui.addNumericalStats(additionGui, subtractionGui, multiplicationGui, divisionGui);
		statsGui.addConceptStats(numberSkill.getComputationalRulesSkill(), measurementSkill.getAreaCalculationSkill(),
				randomnessSkill.getCalculateProbabilitiesSkill());
		statsGui.addGeneralPerformanceStats(generalPerformanceSkill.getStepsNeededSkill(),
				generalPerformanceSkill.getSolvingCarryTasksSkill());
		statsGui.addSolvingTaskStats(solvingTaskSkill.getChooseCorrectFormulasSkill(),
				solvingTaskSkill.getChooseCorrectInformationSkill(), solvingTaskSkill.getInsertCorrectValuesSkill());

		return statsGui;
	}
}
