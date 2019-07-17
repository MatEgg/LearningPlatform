package com.learningplatform.learningplatform.difficulty;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.ComputationalRulesDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.MeasurementDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.PercentCalculationDifficulty;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DecimalCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DigitCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.NumberTypeDifficultyCost;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;

import learningplatform.learningplatform.calculation.ValidCalculation;
import mockit.Mock;
import mockit.MockUp;

public class DifficultyAdjustmentAttributeUtilsTest {

	DifficultyCalculated difficultyCalculated;

	ComputationalRulesDifficulty compRules;
	MeasurementDifficulty measurementDifficulty;
	PercentCalculationDifficulty percentCalculationDifficulty;

	NumberTypeDifficultyCost numberType;
	DigitCountDifficultyCost digitCount;
	DecimalCountDifficultyCost decimalCount;
	List<Attribute> possibleAttributes;
	Attribute areaAttribute;
	Attribute lengthAttribute;
	Attribute widthAttribute;
	Element element;
	Store store;

	@Before
	public void setUp() throws Exception {

		possibleAttributes = new ArrayList<Attribute>();
		difficultyCalculated = new DifficultyCalculated();

		compRules = new ComputationalRulesDifficulty();
		measurementDifficulty = new MeasurementDifficulty();
		percentCalculationDifficulty = new PercentCalculationDifficulty();

		difficultyCalculated.setComputationalRulesDifficulty(compRules);
		difficultyCalculated.setMeasurementDifficulty(measurementDifficulty);
		difficultyCalculated.setPercentCalculationDifficulty(percentCalculationDifficulty);

		store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		element = new StoreElement(store, 1);

		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);
		areaAttribute = new Attribute("test", "", 100, element, AttributeType.AREA);
		possibleAttributes.add(widthAttribute);
		possibleAttributes.add(lengthAttribute);
		possibleAttributes.add(areaAttribute);

	}

	@Test
	public void chooseAttributeByDifficultyLowSkillTest() {
		difficultyCalculated.getComputationalRulesDifficulty().setDifficulty(10);
		assertTrue(
				DifficultyAdjustmentAttributeUtils.chooseAttributeByDifficulty(possibleAttributes, difficultyCalculated)
						.getAttributeType() != AttributeType.AREA);
	}

	@Test
	public void chooseAttributeByDifficultyHighSkillTest() {
		
		new MockUp<Random>() {
			@Mock
			private float nextFloat() {
				return 0.24f;
			}
		};
	
		difficultyCalculated.getComputationalRulesDifficulty().setDifficulty(100);
		Attribute chosenAttr = DifficultyAdjustmentAttributeUtils.chooseAttributeByDifficulty(possibleAttributes,
				difficultyCalculated);
		assertTrue(chosenAttr.getAttributeType() != AttributeType.LENGTH
				&& chosenAttr.getAttributeType() != AttributeType.WIDTH);
	}

	@Test
	public void getAverageConceptDifficulty() {
		difficultyCalculated.getComputationalRulesDifficulty().setDifficulty(100);
		difficultyCalculated.getMeasurementDifficulty().setDifficulty(50);
		difficultyCalculated.getPercentCalculationDifficulty().setDifficulty(25);

		float average = DifficultyAdjustmentAttributeUtils.getAverageConceptDifficulty(difficultyCalculated, true,
				true);
		assertEquals(58, average, 0);
	}

}
