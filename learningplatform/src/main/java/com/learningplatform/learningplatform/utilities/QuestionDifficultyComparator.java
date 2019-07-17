package com.learningplatform.learningplatform.utilities;

import java.util.Comparator;

import com.learningplatform.learningplatform.types.Question;

public class QuestionDifficultyComparator implements Comparator<Question> {

	@Override
	public int compare(Question q1, Question q2) {
		return Integer.compare(q1.getDifficulty(), q2.getDifficulty());
	}

}
