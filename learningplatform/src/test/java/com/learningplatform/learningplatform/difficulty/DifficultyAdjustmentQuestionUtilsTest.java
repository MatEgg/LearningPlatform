package com.learningplatform.learningplatform.difficulty;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.types.Question;

import learningplatform.learningplatform.Calculation.CalculationStats;
import learningplatform.learningplatform.Calculation.OptimalSolution;

public class DifficultyAdjustmentQuestionUtilsTest {

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

	@Before
	public void setUp() throws Exception {

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
		calcStats1.setStepsNeeded(1);
		calcStats2 = new CalculationStats();
		calcStats2.setStepsNeeded(2);
		calcStats3 = new CalculationStats();
		calcStats3.setStepsNeeded(3);
		calcStats4 = new CalculationStats();
		calcStats4.setStepsNeeded(4);
		calcStats5 = new CalculationStats();
		calcStats5.setStepsNeeded(5);

		optimalSolution1.setCalculationStats(calcStats1);
		optimalSolution2.setCalculationStats(calcStats2);
		optimalSolution3.setCalculationStats(calcStats3);
		optimalSolution4.setCalculationStats(calcStats4);
		optimalSolution5.setCalculationStats(calcStats5);

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
		
		only2Questions.add(question1);
		only2Questions.add(question2);
	}

	@Test
	public void curateQuestionsTest() {
		List<Question> curatedQuestions = DifficultyAdjustmentQuestionUtils.curateQuestions(questions, 5);
		assertEquals(3, curatedQuestions.size(), 0);
	}
	
	@Test
	public void curateQuestionsOnly2Test() {
		List<Question> curatedQuestions = DifficultyAdjustmentQuestionUtils.curateQuestions(only2Questions, 5);
		assertEquals(2, curatedQuestions.size(), 0);
	}

}
