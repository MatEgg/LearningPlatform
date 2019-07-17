package com.learningplatform.learningplatform.models.service;

/**
 * Service for handling security
 *
 */
public interface SecurityService {
	
	String findLoggedInUsername();

	void autologin(String username, String password);
}
