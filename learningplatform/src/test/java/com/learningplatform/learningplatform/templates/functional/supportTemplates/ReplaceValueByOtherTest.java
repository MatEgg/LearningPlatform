package com.learningplatform.learningplatform.templates.functional.supportTemplates;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ElementHandler;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.templates.supportTemplates.ReplaceValueByOther;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.QuestionText;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class ReplaceValueByOtherTest {

	ReplaceValueByOther replaceValueByOther;
	StoreElement element;
	StoreElement elementReplaced;
	EnumMap<AttributeType, Attribute> map;
	Store store;
	Store storeReplaced;
	QuestionText questionText;

	Attribute widthAttribute;
	Attribute widthAttributeReplaced;

	@Mocked
	AttributeHandler ah;

	@Before
	public void setUp() throws Exception {

		new MockUp<ElementHandler>() {
			@Mock
			public Attribute replaceAttributWithRandomType(Element oldElement, Attribute attribute,
					boolean isReplacement) {

				return widthAttributeReplaced;
			}
		};

		new MockUp<DifficultyAdjustmentUtils>() {
			@Mock
			public float roundNumber(float number, Operators operator) {

				return 10f;
			}
		};

		questionText = new QuestionText();
		questionText.setQuestionWording("This is a test");

		store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		storeReplaced = new Store("", "", "", "", "", 50, 50, 50, 50, 50);

		element = new StoreElement(store, 1);
		elementReplaced = new StoreElement(storeReplaced, 2);

		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);
		widthAttributeReplaced = new Attribute("test2", "", 10, element, AttributeType.WIDTH);

		element.setChosenAttribute(widthAttribute);

		replaceValueByOther = new ReplaceValueByOther(element);
		replaceValueByOther.setQuestionText(questionText);
		element.setTemplate(replaceValueByOther);

		map = new EnumMap<>(AttributeType.class);

		map.put(AttributeType.WIDTH, widthAttribute);

		element.setAttributeMap(map);

		new Expectations(ReplaceValueByOther.class) {
			{
				replaceValueByOther.addRelevantAttributes((List<Attribute>) any);
			}
		};

	}

	@Test
	public void test() {
		replaceValueByOther.constructQuestion();
		replaceValueByOther.getQuestionText().fillStringBuilder();
		assertTrue(replaceValueByOther.getQuestionText().getQuestionText().contains("10"));
	}

}
