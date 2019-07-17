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
import com.learningplatform.learningplatform.models.service.StoreService;

import mockit.Mock;
import mockit.MockUp;

@Transactional
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreServiceTest {

	@Autowired
	DaoFactory daoFactory;

	StoreService storeService;

	@Before
	public void setUp() throws Exception {
		storeService = daoFactory.getStoreService();
	}

	@Test
	public void getStoreAreaByPercentageTest() {
		
		new MockUp<Random>() {
			@Mock
			private float nextFloat() {
				return 0.51f;
			}
		};
		assertEquals("Zoohandlung", storeService.getStoreAreaByPercentage(0f).getName());
		assertEquals("Tierheim", storeService.getStoreAreaByPercentage(0.25f).getName());
		assertEquals("Tierhandlung", storeService.getStoreAreaByPercentage(0.50f).getName());
		assertEquals("Zoo", storeService.getStoreAreaByPercentage(1f).getName());
	}
}
