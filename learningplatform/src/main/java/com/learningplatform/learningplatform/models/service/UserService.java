package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.models.dao.UserDao;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving user models.
 *
 */
@Service
public class UserService implements ModelService<User> {

	@Autowired
	UserDao userDao;

	@Autowired

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getUserByID(int id) {
		return userDao.findByUid(id);
	}

	@Override
	public User getRandomModel() {
		return userDao.findByUid(UtilitiesHelper.getRandom().nextInt(userDao.findAll().size()) + 1);
	}

	@Override
	public Model getMinimumValue(AttributeType attributeType) {
		return null;
	}

	public User findByUsername(String username) {
		return userDao.findByName(username);
	}

	@Override
	public Boolean save(User user) {
		return userDao.save(user) != null;
	}

}
