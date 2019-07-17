package com.learningplatform.learningplatform.templates.Elements;

import java.util.ArrayList;
import java.util.List;

import com.learningplatform.learningplatform.attributes.AnimalCount;
import com.learningplatform.learningplatform.attributes.Area;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.Length;
import com.learningplatform.learningplatform.attributes.Volume;
import com.learningplatform.learningplatform.attributes.Width;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.attributes.Height;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.Calculation.AttributeCalculation;

public class NewBuildingElement extends NumericalElement {

	Attribute area;
	Attribute length;
	Attribute width;
	Attribute height;
	Attribute volume;
	Attribute animalCount;
	int materialPerArea = 1;
	String name;
	Attribute chosenAttribute;

	public NewBuildingElement(Template template) {
		super(template);
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return name;
	}

	@Override
	String elementTextStart() {
		return name;
	}

	@Override
	String elementTextPlural() {
		return name;
	}

	@Override
	String replacementString() {
		return name;
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.BUILD);
	}

	@Override
	void addAttributeList() {

		attributeList.add(area);
		attributeTypes.add(AttributeType.AREA);

		attributeList.add(length);
		attributeTypes.add(AttributeType.LENGTH);

		attributeList.add(width);
		attributeTypes.add(AttributeType.WIDTH);

		attributeList.add(height);
		attributeTypes.add(AttributeType.HEIGHT);

		attributeList.add(volume);
		attributeTypes.add(AttributeType.VOLUME);

		attributeList.add(animalCount);
		attributeTypes.add(AttributeType.ANIMALCOUNT);

		attributeMap.put(AttributeType.AREA, area);
		attributeMap.put(AttributeType.LENGTH, length);
		attributeMap.put(AttributeType.WIDTH, width);
		attributeMap.put(AttributeType.HEIGHT, height);
		attributeMap.put(AttributeType.VOLUME, volume);
		attributeMap.put(AttributeType.ANIMALCOUNT, animalCount);

		setAttributeTypes(attributeTypes);

	}

	@Override
	void findElement() {
		name = "Gehege";
		
		int lengthVal = (int) Math.max(2,DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.NEWBUILDING_LENGTH, Operators.MULTIPLICATION));
		int widthVal = (int) Math.max(2,DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.NEWBUILDING_WIDTH, Operators.MULTIPLICATION));
		int heightVal = (int) Math.max(2,DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.NEWBUILDING_HEIGHT, Operators.MULTIPLICATION));
		int areaVal = lengthVal * widthVal;
		int volumeVal = lengthVal * widthVal * heightVal;
		int animalCountVal = 10;

		materialPerArea = (int) DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.MATERIAL_PER_AREA, Operators.MULTIPLICATION);

		area = new Area(areaVal, this, widthVal, lengthVal);
		length = new Length(lengthVal, this, areaVal, widthVal);
		width = new Width(widthVal, this, areaVal, lengthVal);
		height = new Height(heightVal, this);
		volume = new Volume(volumeVal, this, widthVal, lengthVal, heightVal);
		animalCount = new AnimalCount(animalCountVal, this);

		List<Attribute> attributesArea = new ArrayList<>();
		attributesArea.add(length);
		attributesArea.add(width);
		area.setAttributeCalculation(new AttributeCalculation(attributesArea, Operators.MULTIPLICATION));

		area.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		width.setAttributeCalculation(new AttributeCalculation(width));
		width.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		length.setAttributeCalculation(new AttributeCalculation(length));
		length.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		List<Attribute> attributesVolume = new ArrayList<>();
		attributesVolume.add(length);
		attributesVolume.add(width);
		attributesVolume.add(height);

		volume.setAttributeCalculation(new AttributeCalculation(attributesVolume, Operators.MULTIPLICATION));
		volume.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		addAttributeList();

	}

	public int getMaterialPerArea() {
		return materialPerArea;
	}

	public void setMaterialPerArea(int materialPerArea) {
		this.materialPerArea = materialPerArea;
	}

}
