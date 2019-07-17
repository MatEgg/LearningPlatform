package com.learningplatform.learningplatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learningplatform.learningplatform.bkt.BktUpdateCreator;
import com.learningplatform.learningplatform.guiObjects.WordproblemGui;
import com.learningplatform.learningplatform.question.AnswerHandler;
import com.learningplatform.learningplatform.question.AnswerHandlerUtils;
import com.learningplatform.learningplatform.question.AttributeAnswerCheck;
import com.learningplatform.learningplatform.question.QuestionAnswerCheck;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.types.AttributeCalculationType;
import com.learningplatform.learningplatform.types.QuestionCalculation;

import learningplatform.learningplatform.calculation.CompletionInformation;

/**
 * Controller for everything regarding the wordproblem page.
 *
 */
@RestController
public class WordProblemController {

	@Autowired
	DaoFactory daoFactory;

	/**
	 * Query the API to get a new Word problem
	 */
	@GetMapping("/wordProblemGet")
	public WordproblemGui getWordProblem() {
		return new WordproblemGui(WordProblem.getNewInstance(daoFactory));
	}

	/**
	 * Query the API to analyse a question answer
	 */
	@PostMapping("/getQuestionAnswer")
	public double getQuestionAnswer(@RequestBody int questionId) {
		QuestionHandler qh = QuestionHandler.getInstance();

		return qh.getQuestionCatalogue().getQuestionMap().get(questionId).getResult();
	}

	/**
	 * Submit an attribute result to the API
	 * 
	 * @param attributeCalculation holds information about the student's submission
	 * @return
	 */
	@PostMapping("/submitAttributeResult")
	public AttributeAnswerCheck submitResult(@RequestBody AttributeCalculationType attributeCalculation) {
		AnswerHandler ah = AnswerHandler.getInstance();

		BktUpdateCreator bktUpdateCreator = new BktUpdateCreator();

		AttributeAnswerCheck attributeAnswerCheck = ah.checkAttributeAnswer(attributeCalculation,
				WordProblem.getInstance().getElementIdMap());
		bktUpdateCreator.updateByAttributeCalculation(attributeCalculation, attributeAnswerCheck);
		return attributeAnswerCheck;
	}

	/**
	 * Submit completion information to the API
	 * 
	 * @param completionInformation Holds information about the completion
	 *                              information
	 */
	@PostMapping("/submitCompletionInformation")
	public void submitCompletionInformation(@RequestBody CompletionInformation completionInformation) {
		completionInformation.setQuestionCount(
				QuestionHandler.getInstance().getQuestionCatalogue().getQuestionMap().keySet().size());
		BktUpdateCreator bktUpdateCreator = new BktUpdateCreator();
		bktUpdateCreator.updateByCompletion(completionInformation);
	}

	/**
	 * Submit an question result to the API
	 * 
	 * @param calculation contains information about the question calculation
	 * @return questionAnswerCheck answer check of the question
	 */
	@PostMapping("/submitQuestionResult")
	public QuestionAnswerCheck submitQuestionResult(@RequestBody QuestionCalculation calculation) {
		QuestionHandler qh = QuestionHandler.getInstance();
		BktUpdateCreator bktUpdateCreator = new BktUpdateCreator();

		QuestionAnswerCheck questionAnswerCheck = qh.checkQuestionAnswer(calculation,
				WordProblem.getInstance().getElementIdMap());
		bktUpdateCreator.updateByQuestionCalculation(calculation, questionAnswerCheck);

		return questionAnswerCheck;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}
