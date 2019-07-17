package com.learningplatform.learningplatform.templates.Elements;

import java.util.Optional;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.models.Field;
import com.learningplatform.learningplatform.models.service.FieldService;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

public class ElementHandler {

	static ElementHandler elementHandlerInstance;

	public enum Elements {
		STOREELEMENT, FIELDELEMENT
	}

	public ElementHandler() {
	}

	public static ElementHandler getInstance() {
		if (elementHandlerInstance == null) {
			elementHandlerInstance = new ElementHandler();
			return elementHandlerInstance;
		} else {
			return elementHandlerInstance;
		}
	}

	public static ElementHandler getNewInstance() {
		elementHandlerInstance = new ElementHandler();
		return elementHandlerInstance;
	}

	/**
	 * Finds a random element that has an attribute with the same attribute type
	 * that can be used for replacement.
	 * 
	 * @param element        element of which one of the attributes is to be
	 *                       replaced
	 * @param template       template that the element is included in
	 * @param wishMultiplier the desired ratio between the attributes
	 * @param attributeType  type of the attribute
	 * @return Element with the same type as the given element
	 */
	public static Element getNewRandomElementSameType(Element element, Template template, float wishMultiplier,
			AttributeType attributeType) {
		FieldService fieldService = WordProblem.getInstance().getDaoFactory().getFieldService();
		Optional<Field> optionalField = fieldService.getClosestFieldByParam(element.getElementText(), attributeType,
				wishMultiplier, DifficultyHandler.getInstance().getDifficulty().getDecimalenabled());
		if (optionalField.isPresent()) {
			Field field = optionalField.get();
			field.setUsed(1);
			fieldService.save(field);
			return new FieldElement(field, template);
		}
		return null;
	}

}
