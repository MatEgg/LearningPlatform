package com.learningplatform.learningplatform.utilities;

import java.util.Comparator;

import com.learningplatform.learningplatform.types.Question;

public class QuestionComparator implements Comparator<Question> {

	@Override
	public int compare(Question q1, Question q2) {
		return Integer.compare(q1.getOptimalSolution().getCalculationStats().getStepsNeeded(),
				q2.getOptimalSolution().getCalculationStats().getStepsNeeded());
	}

}
