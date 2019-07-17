package com.learningplatform.learningplatform.models.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.models.service.AnimalService;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AnimalServiceTest {
	
	@Autowired
	DaoFactory daoFactory;
	
	AnimalService animalService;

	@Before
	public void setUp() throws Exception {
		animalService = daoFactory.getAnimalService();
	}

	@Test
	public void getClosestAnimalByFoodReqTest() {
		assertEquals("Hamster", animalService.getClosestAnimalByFoodReq(0).getName());
		assertEquals("Schildkröten", animalService.getClosestAnimalByFoodReq(5).getName());
		assertEquals("Hunde", animalService.getClosestAnimalByFoodReq(10).getName());
	}

}
