package com.learningplatform.learningplatform.templates.connectedTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.MaterialElement;
import com.learningplatform.learningplatform.templates.Elements.NewBuildingElement;
import com.learningplatform.learningplatform.templates.Elements.ProductElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.QuestionText;

public class BuyingMaterial extends ComputationalConnectedTemplate {

	ListMultimap<QuestionAttribute, Element> elementsMap;
	List<Attribute> attributes;
	ProductElement productElement;
	StoreElement storeElement;
	AnimalElement animalElement;
	MaterialElement materialElement;
	NewBuildingElement newBuildingElement;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);

	}

	public BuyingMaterial(WordProblem wordProblem) {
		super(wordProblem);
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		elementsMap = wordProblem.getElementsMap();
		setRequiredAttr();
	}

	@Override
	public void callFinishTemplate() {
		this.storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);
		this.productElement = (ProductElement) elementsMap.get(QuestionAttribute.ANIMALFOOD).get(0);
		this.animalElement = (AnimalElement) elementsMap.get(QuestionAttribute.ANIMAL).get(0);

		newBuildingElement = (NewBuildingElement) elementsMap.get(QuestionAttribute.BUILD).get(0);
		finishTemplate();
		EnumMap<QuestionAttribute, Element> buyingAttributes = new EnumMap<>(QuestionAttribute.class);
		buyingAttributes.put(QuestionAttribute.STORE, storeElement);
		buyingAttributes.put(QuestionAttribute.BUILDMATERIAL, materialElement);
		buyingAttributes.put(QuestionAttribute.BUILD, newBuildingElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.BUYINGMATERIAL,
				buyingAttributes);

	}

	@Override
	public void setQuestionElements() {
		elems = new ArrayList<>();
		elems.add(materialElement);
		addQuestionElements(elems);
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void setRequiredAttr() {
		List<List<QuestionAttribute>> requiredAttributesList = new ArrayList<>();

		List<QuestionAttribute> requiredAttributesBuild = new ArrayList<>();
		requiredAttributesBuild.add(QuestionAttribute.BUILD);

		requiredAttributesList.add(requiredAttributesBuild);

		addRequiredAttributes(requiredAttributesList);

	}

	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.PAYING_FOR_MATERIAL);
	}

	@Override
	public QuestionText constructQuestion() {
		questionText = new QuestionText();
		materialElement = new MaterialElement(this);
		questionText.addTextLine(" Für den neuen Anbau werden pro m2 Fläche ");
		questionText.addTextLine(String.valueOf(newBuildingElement.getMaterialPerArea()));
		questionText.addTextLine(" ");
		questionText.addTextLine(materialElement.getElementText());
		questionText.addTextLine(" benötigt.");

		materialElement.getAttributeByType(AttributeType.PRICE).setIsDistractor(false);

		return questionText;
	}

	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}

}
