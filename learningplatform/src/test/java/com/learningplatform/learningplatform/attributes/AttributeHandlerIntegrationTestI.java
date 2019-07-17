package com.learningplatform.learningplatform.attributes;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ElementHandler;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AttributeHandlerIntegrationTestI {

	@Mocked
	ElementHandler eh;

	@Tested
	AttributeHandler ah;

	@Mocked
	Random random;

	Element element;
	Store store;
	Attribute areaAttribute;
	Attribute lengthAttribute;
	Attribute widthAttribute;
	Attribute volumeAttribute;
	Attribute heightAttribute;
	EnumMap<AttributeType, Attribute> map;

	@Before
	public void setUp() throws Exception {

		new MockUp<DifficultyAdjustmentUtils>() {
			@Mock
			public float getMultiplierBasedOnDifficulty(int bound, Operators operator) {
				return 10f;
			}
		};

		new MockUp<ElementHandler>() {
			@Mock
			public Element getNewRandomElementSameType(Element element, Template template, float wishMultiplier,
					AttributeType attributeType) {
				return element;
			}
		};

		new MockUp<Random>() {
			@Mock
			public boolean nextBoolean() {
				return true;
			}
		};

		store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		element = new StoreElement(store, 1);
		map = new EnumMap<>(AttributeType.class);

		element.setAttributeMap(map);
	}

	@Test
	public void replaceAttributWithRandomTypeTestForTypeArea() {

		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);

		map.put(AttributeType.AREA, areaAttribute);
		map.put(AttributeType.LENGTH, lengthAttribute);

		Attribute attr = ah.replaceAttributWithRandomType(element, areaAttribute, false);
		assertNotNull(ah.replaceAttributWithRandomType(element, areaAttribute, false));
		assertEquals(AttributeType.AREA, attr.getAttributeType());
		assertTrue(!lengthAttribute.getIsVisible());

	}

	@Test
	public void replaceAttributWithRandomTypeTestLength() {

		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);

		map.put(AttributeType.AREA, areaAttribute);
		map.put(AttributeType.LENGTH, lengthAttribute);

		assertNotNull(ah.replaceAttributWithRandomType(element, lengthAttribute, false));
		assertEquals(AttributeType.LENGTH,
				ah.replaceAttributWithRandomType(element, lengthAttribute, false).getAttributeType());
		assertTrue(!areaAttribute.getIsVisible());
	}

	@Test
	public void replaceAttributWithRandomTypeTestWidth() {

		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);

		map.put(AttributeType.AREA, areaAttribute);
		map.put(AttributeType.WIDTH, widthAttribute);

		assertNotNull(ah.replaceAttributWithRandomType(element, widthAttribute, false));
		assertEquals(AttributeType.WIDTH,
				ah.replaceAttributWithRandomType(element, widthAttribute, false).getAttributeType());
		assertTrue(!areaAttribute.getIsVisible());
	}

	@Test
	public void replaceAttributWithRandomTypeTestVolumeLength() {

		new MockUp<Random>() {
			@Mock
			private int nextInt(int bound) {
				return 0;
			}
		};

		volumeAttribute = new Attribute("test", "", 10, element, AttributeType.VOLUME);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);

		map.put(AttributeType.VOLUME, volumeAttribute);
		map.put(AttributeType.LENGTH, lengthAttribute);

		assertNotNull(ah.replaceAttributWithRandomType(element, volumeAttribute, false));
		assertEquals(AttributeType.VOLUME,
				ah.replaceAttributWithRandomType(element, volumeAttribute, false).getAttributeType());
		assertTrue(!lengthAttribute.getIsVisible());

	}

	@Test
	public void replaceAttributWithRandomTypeTestVolumeWidth() {

		new MockUp<Random>() {
			@Mock
			private int nextInt(int bound) {
				return 1;
			}
		};

		volumeAttribute = new Attribute("test", "", 10, element, AttributeType.VOLUME);
		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);

		map.put(AttributeType.VOLUME, volumeAttribute);
		map.put(AttributeType.WIDTH, widthAttribute);

		assertNotNull(ah.replaceAttributWithRandomType(element, volumeAttribute, false));
		assertEquals(AttributeType.VOLUME,
				ah.replaceAttributWithRandomType(element, volumeAttribute, false).getAttributeType());
		assertTrue(!widthAttribute.getIsVisible());

	}

	@Test
	public void replaceAttributWithRandomTypeTestVolumeArea() {

		new MockUp<Random>() {
			@Mock
			private int nextInt(int bound) {
				return 2;
			}
		};

		volumeAttribute = new Attribute("test", "", 10, element, AttributeType.VOLUME);
		heightAttribute = new Attribute("test", "", 10, element, AttributeType.HEIGHT);

		map.put(AttributeType.VOLUME, volumeAttribute);
		map.put(AttributeType.HEIGHT, heightAttribute);

		assertNotNull(ah.replaceAttributWithRandomType(element, volumeAttribute, false));
		assertEquals(AttributeType.VOLUME,
				ah.replaceAttributWithRandomType(element, volumeAttribute, false).getAttributeType());
		assertTrue(!heightAttribute.getIsVisible());

	}

}
