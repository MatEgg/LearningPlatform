package com.learningplatform.learningplatform.templates.Elements;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.service.ModelService;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

public abstract class Element {

	WordProblem wordProblem;

	Boolean alreadyInList = false;
	int id;
	String normalElementOccurence;
	String startElementOccurence;
	String pluralElementOccurence;
	List<QuestionAttribute> questionAttributes;
	List<Attribute> attributeList;
	Map<Integer, Attribute> attributeIdMap;

	EnumMap<AttributeType, Attribute> attributeMap;
	Random random;

	Template template;
	String activeString;
	String stringReplacement;
	private ModelService<? extends Model> modelService;

	public Element(Template template) {
		wordProblem = WordProblem.getInstance();
		attributeList = new ArrayList<>();
		attributeMap = new EnumMap<>(AttributeType.class);
		attributeIdMap = new HashMap<>();
		this.template = template;
		questionAttributes = new ArrayList<>();
		random = new Random();
		id = wordProblem.elementID++;
	}

	public Element(int id) {
		attributeList = new ArrayList<>();
		attributeMap = new EnumMap<>(AttributeType.class);
		attributeIdMap = new HashMap<>();
		questionAttributes = new ArrayList<>();
		random = new Random();
		this.id = id;
	}
	
	public void assignNames() {
		normalElementOccurence = elementText();
		startElementOccurence = elementTextStart();
		pluralElementOccurence = elementTextPlural();
		stringReplacement = replacementString();
	}

	public void finishElementConstruction() {
		normalElementOccurence = elementText();
		startElementOccurence = elementTextStart();
		pluralElementOccurence = elementTextPlural();
		stringReplacement = replacementString();
		addQuestionAttributes();
		wordProblem.getElementIdMap().put(id, this);

	}

	public Attribute getAttributeByType(AttributeType attributeType) {
		return attributeMap.get(attributeType);
	}

	abstract String elementText();

	abstract String elementTextStart();

	abstract String elementTextPlural();

	abstract String replacementString();

	abstract void addQuestionAttributes();

	abstract void addAttributeList();

	abstract void findElement();

	public void fillAttributeMap(EnumMap<AttributeType, Attribute> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public Map<AttributeType, Attribute> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(EnumMap<AttributeType, Attribute> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public String getElementText() {
		return normalElementOccurence;
	}

	public String getElementTextStart() {
		return startElementOccurence;
	}

	public String getElementTextPlural() {
		return pluralElementOccurence;
	}

	public List<QuestionAttribute> getQuestionAttributes() {
		return questionAttributes;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getActiveString() {
		return activeString;
	}

	public void setActiveString(String activeString) {
		this.activeString = activeString;
	}

	public String getReplacementString() {
		return stringReplacement;
	}

	public void setReplacementString(String replacementString) {
		this.stringReplacement = replacementString;
	}

	public List<Attribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<Attribute> attributeList) {
		this.attributeList = attributeList;
	}

	public Boolean getAlreadyInList() {
		return alreadyInList;
	}

	public void setAlreadyInList(Boolean alreadyInList) {
		this.alreadyInList = alreadyInList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Attribute> getAttributeIdMap() {
		return attributeIdMap;
	}

	public void setAttributeIdMap(Map<Integer, Attribute> attributeIdMap) {
		this.attributeIdMap = attributeIdMap;
	}

	public ModelService<? extends Model> getModelService() {
		return modelService;
	}

	public void setModelService(ModelService<? extends Model> modelService) {
		this.modelService = modelService;
	}
}
