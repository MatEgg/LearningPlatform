package com.learningplatform.learningplatform.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;

public class QuestionTextTest {
	QuestionText questionText;
	final String ELEMENT_TEXT = "start";
	Element element;
	Store store;

	@Before
	public void setUp() throws Exception {
		questionText = new QuestionText();

		store = new Store(ELEMENT_TEXT, "plural", "start", "startstart", "middle", 5, 5, 5, 5, 5);
		element = new StoreElement(store, 1);

	}

	@Test
	public void addElementLineTest() {
		questionText.addElementLine(element, PositionType.START);
		questionText.fillStringBuilder();
		assertTrue(questionText.getQuestionText().equals(ELEMENT_TEXT));
	}

}
