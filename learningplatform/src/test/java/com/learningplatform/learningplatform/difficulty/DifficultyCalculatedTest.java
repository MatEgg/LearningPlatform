package com.learningplatform.learningplatform.difficulty;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learningplatform.learningplatform.controllers.DaoFactory;
import com.learningplatform.learningplatform.difficulty.DifficultyCalculated;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.User;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DifficultyCalculatedTest {

	DifficultyCalculated difficultyCalculated;

	@Autowired
	DaoFactory daoFactory;

	User user;
	Settings settings;

	@Before
	public void setUp() throws Exception {

		user = daoFactory.getUserService().getUserByID(1251);
		settings = user.getSettings();
	}

	@Test
	public void difficultyCalculatedAutomaticTest() {
		settings.setAutomaticEnabled(1);
		difficultyCalculated = new DifficultyCalculated(user, settings);
		assertNotNull(difficultyCalculated.getAdditionDifficulty());
	}
	
	@Test
	public void difficultyCalculatedManualTest() {
		settings.setAutomaticEnabled(0);
		difficultyCalculated = new DifficultyCalculated(user, settings);
		assertNotNull(difficultyCalculated.getAdditionDifficulty());
	}

}
