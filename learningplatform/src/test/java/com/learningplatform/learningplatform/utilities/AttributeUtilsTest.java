package com.learningplatform.learningplatform.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learningplatform.learningplatform.attributes.Area;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.utilities.AttributeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void checkVisibilityOfChildrenTest() {
		Store store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		Element element = new StoreElement(store, 1);
		Area areaAttr = new Area(10, element, 10, 10);
		AttributeUtils.checkVisibilityOfChildren(areaAttr);
	}

}
