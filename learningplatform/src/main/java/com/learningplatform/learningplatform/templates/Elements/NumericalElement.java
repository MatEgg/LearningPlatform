package com.learningplatform.learningplatform.templates.Elements;

import java.util.ArrayList;
import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Template;

public abstract class NumericalElement extends Element {

	int possibleEntities;
	List<AttributeType> attributeTypes;

	public NumericalElement(Template template) {
		super(template);
		attributeTypes = new ArrayList<>();
	}
	
	public NumericalElement(int id) {
		super(id);
		attributeTypes = new ArrayList<>();
	}

	public void setPossibleEntities(int possibleEntities) {
		this.possibleEntities = possibleEntities;
	}

	public int getPossibleEntities() {
		return possibleEntities;
	}

	public Attribute getRandomAttribute() {
		return attributeList.get(random.nextInt(attributeList.size()));
	}
	
	public AttributeType getRandomAttributeType() {
		return attributeTypes.get(random.nextInt(attributeTypes.size()));
	}

	public List<AttributeType> getAttributeTypes() {
		return attributeTypes;
	}

	public void setAttributeTypes(List<AttributeType> attributeTypes) {
		this.attributeTypes = attributeTypes;
	}
	
}
