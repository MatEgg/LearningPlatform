package com.learningplatform.learningplatform.models.service;

import static org.junit.Assert.*;

import java.util.Random;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.models.service.CityService;

import mockit.Mock;
import mockit.MockUp;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CityServiceTest {

	@Autowired
	DaoFactory daoFactory;
	
	CityService cityService;

	@Before
	public void setUp() throws Exception {
		cityService = daoFactory.getCityService();
		
		new MockUp<Random>() {
			@Mock
			private float nextFloat() {
				return 0.24f;
			}
		};
		
	}

	@Test
	public void getCityByPercentageTest() {
		assertEquals("Varel", cityService.getCityByPercentage(0.25f).getName());
		assertEquals("Leer", cityService.getCityByPercentage(0.50f).getName());
		assertEquals("Bremerhaven", cityService.getCityByPercentage(0.75f).getName());
	}


}
