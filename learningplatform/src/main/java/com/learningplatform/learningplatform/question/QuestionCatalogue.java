package com.learningplatform.learningplatform.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.learningplatform.learningplatform.types.Question;

public class QuestionCatalogue {

	List<Question> questions;

	Map<Integer, Question> questionMap;

	public QuestionCatalogue() {
		questions = new ArrayList<>();
		questionMap = new HashMap<>();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void fillQuestionMap() {
		for (Question question : questions) {
			questionMap.put(question.getId(), question);
		}
	}

	public Map<Integer, Question> getQuestionMap() {
		return questionMap;
	}

	public void setQuestionMap(Map<Integer, Question> questionMap) {
		this.questionMap = questionMap;
	}
}
