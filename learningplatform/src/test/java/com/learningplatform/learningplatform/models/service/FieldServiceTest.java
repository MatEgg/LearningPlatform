package com.learningplatform.learningplatform.models.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.models.service.FieldService;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FieldServiceTest {

	@Autowired
	DaoFactory daoFactory;

	FieldService fieldService;

	@Before
	public void setUp() throws Exception {
		fieldService = daoFactory.getFieldService();
	}

	@Test
	public void getClosestFieldByParamTest() {
		assertEquals("Garage", fieldService.getClosestFieldByParam("test", AttributeType.LENGTH, 10, false).get().getName());
		assertEquals("Volleyballfeld", fieldService.getClosestFieldByParam("test", AttributeType.WIDTH, 10, false).get().getName());
		assertEquals("Raum (klein)", fieldService.getClosestFieldByParam("test", AttributeType.AREA, 100, false).get().getName());
		assertEquals("Tennisplatz", fieldService.getClosestFieldByParam("test", AttributeType.VOLUME, 1000, false).get().getName());
	}

}
