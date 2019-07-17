package com.learningplatform.learningplatform.templates.baseTemplates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.models.service.StoreService;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ElementHandler.Elements;
import com.learningplatform.learningplatform.templates.Elements.NumericalElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

public class ListOfNumericalThings extends BaseTemplate {

	List<NumericalElement> numericalElements;
	Elements chosenElement;
	List<Attribute> attributes;
	List<Question> templateQuestions;
	List<Element> questionElements;
	Attribute attribute;
	Question question;

	public ListOfNumericalThings(WordProblem wordProblem) {
		super(wordProblem);
		this.wordProblem = wordProblem;
		questionText = getQuestionText();
		questionElements = new ArrayList<>();
		sb = getStringBuilder();
		numericalElements = new ArrayList<>();
		templateQuestions = new ArrayList<>();
		attributes = new ArrayList<>();
	}
	
	@Override
	public void setQuestionElements() {

		for (NumericalElement numericalElement : numericalElements) {
			questionElements.add(numericalElement);
		}
		addQuestionElements(questionElements);
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public QuestionText constructQuestion() {
		AttributeType attributeType = null;
		if (chosenElement.equals(Elements.STOREELEMENT)) {
			List<AttributeType> possibleAttributes = new ArrayList<>();
			possibleAttributes.add(AttributeType.AREA);
			possibleAttributes.add(AttributeType.LENGTH);
			possibleAttributes.add(AttributeType.WIDTH);
			attributeType = possibleAttributes.get(UtilitiesHelper.getRandom().nextInt(possibleAttributes.size()));
		}
		questionText = new QuestionText();
		for (NumericalElement numericalElement : numericalElements) {
			attribute = numericalElement.getAttributeByType(attributeType);
			questionText.addElementLine(numericalElement, PositionType.START);
			questionText.addTextLine(". ");
			attributes.add(attribute);
		}
		return questionText;
	}

	private void fillNumericalThingsList() {
		chosenElement = Elements.STOREELEMENT;
		StoreService storeService = WordProblem.getInstance().getDaoFactory().getStoreService();
		for (Store store : storeService.getAllStores()) {
			numericalElements.add(new StoreElement(store, this));
		}
	}

	@Override
	public void callFinishTemplate() {
		fillNumericalThingsList();
		finishTemplate();
	}

	@Override
	public void setRelevantAttributes() {
		addRelevantAttributes(attributes);
	}

}
