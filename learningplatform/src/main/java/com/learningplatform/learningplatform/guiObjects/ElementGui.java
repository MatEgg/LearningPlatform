package com.learningplatform.learningplatform.guiObjects;

import java.util.ArrayList;
import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.templates.Elements.Element;

import learningplatform.learningplatform.Calculation.OptimalSolution;

/**
 * Overview of elements that are included in the Word problem.
 *
 */
public class ElementGui {

	int id;
	String text;
	List<AttributeGui> attributes;
	Boolean isClickVisible = true;

	public ElementGui(int id, String text, Element element) {
		this.id = id;
		attributes = new ArrayList<>();
		OptimalSolution optimalSolution = null;
		for (Attribute attribute : element.getAttributeList()) {
			if (attribute != null && (attribute.getIsShown() || !attribute.getIsVisible())) {
				if (!attribute.isManualHelp()) {
					optimalSolution = new OptimalSolution(attribute.getAttributeCalculation());
				} else {
					optimalSolution = attribute.getOptimalSolution();
				}
				attributes.add(new AttributeGui(attribute.getId(), attribute.getName(), attribute.getUnit(),
						attribute.getNumericalValue(), attribute.getIsVisible(), optimalSolution));
			}
		}
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public List<AttributeGui> getAttributes() {
		return attributes;
	}

	public Boolean getIsClickVisible() {
		return isClickVisible;
	}

	public void setIsClickVisible(Boolean isClickVisible) {
		this.isClickVisible = isClickVisible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
