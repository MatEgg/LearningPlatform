package com.learningplatform.learningplatform.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.TargetAttribute;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.calculation.AttributeCalculation;

public class AttributeUtils {
	
	/**
	 * Checks the visibility of the area attribute if it is present in the element.
	 * If not, the length and width attributes are made invisible, so to make the
	 * calculation of the area attribute not too easy.
	 * 
	 * @param attribute the attribute to be checked
	 */
	public static void checkVisibilityOfChildren(Attribute attribute) {
		// check if the attribute has a replacement attribute and is the area type
		if (attribute != null && attribute.getAttributeType() == AttributeType.AREA
				&& attribute.getReplacementAttribute() != null && !attribute.getIsVisible()) {
			Element tempElement = attribute.getElement();
			tempElement.getAttributeByType(AttributeType.LENGTH).setNotVisibleIfNotBlockedAndReplaced();
			tempElement.getAttributeByType(AttributeType.WIDTH).setNotVisibleIfNotBlockedAndReplaced();
		}
	}
	
	/**
	 * Helper method to convert TargetAttributes to Attributes
	 * 
	 * @param targetAttributes List of TargetAttributes gathered from the Client
	 * @return list of converted Attributes
	 */
	public static List<Attribute> convertTargetAttributes(List<TargetAttribute> targetAttributes, Map<Integer, Element> elementMap) {
		List<Attribute> convertedAttributes = new ArrayList<>();
		Element element;
		Map<Integer, Attribute> attributeMap;
		for (TargetAttribute targetAttribute : targetAttributes) {
			element = elementMap.get(targetAttribute.getElementId());

			attributeMap = element.getAttributeIdMap();
			convertedAttributes.add(attributeMap.get(targetAttribute.getId()));

		}

		return convertedAttributes;

	}

	/**
	 * Set all the valid calculations of store attributes
	 * 
	 * @param attribute    the attribute of which to set the new valid calculation
	 * @param storeElement the element that holds the relevant attribute
	 */
	public static void changeAttributeCalculation(Attribute attribute, StoreElement storeElement) {

		Attribute width = storeElement.getAttributeByType(AttributeType.WIDTH);
		Attribute length = storeElement.getAttributeByType(AttributeType.LENGTH);
		Attribute area = storeElement.getAttributeByType(AttributeType.AREA);
		Attribute volume = storeElement.getAttributeByType(AttributeType.VOLUME);
		Attribute height = storeElement.getAttributeByType(AttributeType.HEIGHT);

		if (attribute.getAttributeType() == AttributeType.AREA) {

			List<Attribute> areaAttributes = new ArrayList<>();
			areaAttributes.add(length);
			areaAttributes.add(width);

			area.setAttributeCalculation(new AttributeCalculation(areaAttributes, Operators.MULTIPLICATION));
			area.getAttributeCalculation().getCalculationInformation()
					.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		} else if (attribute.getAttributeType() == AttributeType.WIDTH) {

			List<Attribute> widthAttributes = new ArrayList<>();
			widthAttributes.add(area);
			widthAttributes.add(length);

			width.setAttributeCalculation(new AttributeCalculation(widthAttributes, Operators.DIVISION));
			width.getAttributeCalculation().getCalculationInformation()
					.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		} else if (attribute.getAttributeType() == AttributeType.LENGTH) {

			List<Attribute> lengthAttributes = new ArrayList<>();
			lengthAttributes.add(area);
			lengthAttributes.add(width);

			length.setAttributeCalculation(new AttributeCalculation(lengthAttributes, Operators.DIVISION));
			length.getAttributeCalculation().getCalculationInformation()
					.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		} else if (attribute.getAttributeType() == AttributeType.VOLUME) {

			List<Attribute> volumeAttributes = new ArrayList<>();
			volumeAttributes.add(length);
			volumeAttributes.add(width);
			volumeAttributes.add(height);

			volume.setAttributeCalculation(new AttributeCalculation(volumeAttributes, Operators.MULTIPLICATION));
			volume.getAttributeCalculation().getCalculationInformation()
					.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		}

	}

}
