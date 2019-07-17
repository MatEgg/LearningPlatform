package com.learningplatform.learningplatform.difficulty;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.utilities.QuestionComparator;
import com.learningplatform.learningplatform.utilities.QuestionDifficultyComparator;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

public class DifficultyAdjustmentQuestionUtils {

	private static void addQuestionsToLists(List<Question> questions, List<Question> level1Questions,
			List<Question> level2Questions, List<Question> level3Questions, List<Question> level4Questions) {
		for (Question question : questions) {
			if (question.getDifficulty() == 1) {
				level1Questions.add(question);
			} else if (question.getDifficulty() == 2) {
				level2Questions.add(question);
			} else if (question.getDifficulty() == 3) {
				level3Questions.add(question);
			} else if (question.getDifficulty() == 4) {
				level4Questions.add(question);
			}
		}
	}

	/**
	 * Used to curate the questions. Lowers the amount of questions and removes
	 * duplicates
	 * 
	 * @param questions current question catalogue
	 * @return List<Question> new list of curated questions
	 */
	public static List<Question> curateQuestions(List<Question> questions, float questionDifficultyLevel) {
		List<Question> level1Questions = new ArrayList<>();
		List<Question> level2Questions = new ArrayList<>();
		List<Question> level3Questions = new ArrayList<>();
		List<Question> level4Questions = new ArrayList<>();

		addQuestionsToLists(questions, level1Questions, level2Questions, level3Questions, level4Questions);

		QuestionComparator qc = new QuestionComparator();
		QuestionDifficultyComparator qdc = new QuestionDifficultyComparator();

		// Sort the questions based on amount of steps needed
		List<Question> sortedQuestionsSteps = questions.stream().sorted((o1, o2) -> qc.compare(o1, o2))
				.collect(Collectors.toList());

		// Sort the questions based on their difficulty
		List<Question> sortedQuestionsDifficulty = questions.stream().sorted((o1, o2) -> qdc.compare(o1, o2))
				.collect(Collectors.toList());

		List<Question> filteredQuestions = questions.stream().filter(question -> question.getOptimalSolution()
				.getCalculationStats().getStepsNeeded() <= questionDifficultyLevel).collect(Collectors.toList());

		Set<Question> finalQuestionsSet = new LinkedHashSet<>();
		List<Question> finalQuestions = new ArrayList<>();

		// Add questions to a set, so that duplicates can later be removed
		if (filteredQuestions.size() == 0) {
			if (sortedQuestionsSteps.size() != 0) {
				finalQuestions.add(sortedQuestionsSteps.get(0));
			}
		} else {
			List<Question> tempSortedList = filteredQuestions.stream().sorted((o1, o2) -> qc.compare(o1, o2))
					.collect(Collectors.toList());
			finalQuestions.add(tempSortedList.get(tempSortedList.size() - 1));
			finalQuestionsSet.add(tempSortedList.get(tempSortedList.size() - 1));
		}

		int questionPick = 0;
		int randomPick = 0;
		Question tempQuestion;

		// based on difficulty, add questions from the pool of different question
		// difficulties
		while (finalQuestionsSet.size() < 3 && sortedQuestionsDifficulty.size() > 0) {
			questionPick = (int) (questionDifficultyLevel / 10 * sortedQuestionsDifficulty.size() - 1);

			tempQuestion = sortedQuestionsDifficulty.get(questionPick);

			if (tempQuestion.getDifficulty() == 1 && !level1Questions.isEmpty()) {
				randomPick = UtilitiesHelper.getRandom().nextInt(level1Questions.size());
				finalQuestionsSet.add(level1Questions.get(randomPick));

			} else if (tempQuestion.getDifficulty() == 2 && !level2Questions.isEmpty()) {
				randomPick = UtilitiesHelper.getRandom().nextInt(level2Questions.size());
				finalQuestionsSet.add(level2Questions.get(randomPick));

			} else if (tempQuestion.getDifficulty() == 3 && !level3Questions.isEmpty()) {
				randomPick = UtilitiesHelper.getRandom().nextInt(level3Questions.size());
				finalQuestionsSet.add(level3Questions.get(randomPick));

			} else if (tempQuestion.getDifficulty() == 4 && !level4Questions.isEmpty()) {
				randomPick = UtilitiesHelper.getRandom().nextInt(level4Questions.size());
				finalQuestionsSet.add(level4Questions.get(randomPick));

			}
			sortedQuestionsDifficulty.remove(questionPick);
		}

		return new ArrayList<>(finalQuestionsSet);
	}
}
