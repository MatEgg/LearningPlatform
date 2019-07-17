package com.learningplatform.learningplatform.templates;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.templates.connectedTemplates.CalculateAreaOfStore;
import com.learningplatform.learningplatform.templates.connectedTemplates.IncomeThroughVisitors;

public class TemplateOverviewUtilsTest {
	CalculateAreaOfStore calcOfStore;
	IncomeThroughVisitors incomeVisitors;

	@Before
	public void setUp() throws Exception {
		calcOfStore = new CalculateAreaOfStore();
		incomeVisitors = new IncomeThroughVisitors();
	}

	@Test
	public void diffOver70AreaTest() {
		assertEquals(10, TemplateOverviewUtils.diffOver70(95, calcOfStore, 0, 95));
	}

	@Test
	public void diffOver70IncomeTest() {
		assertEquals(10, TemplateOverviewUtils.diffOver70Income(95, incomeVisitors, 0, 95));
	}

	@Test
	public void diffBetween35And75AreaTest() {
		assertEquals(10, TemplateOverviewUtils.diffBetween35And75(65, calcOfStore, 0, 65));
	}

	@Test
	public void diffBetween35And75IncomeTest() {
		assertEquals(10, TemplateOverviewUtils.diffBetween35And70(65, incomeVisitors, 0, 65));
	}

}
