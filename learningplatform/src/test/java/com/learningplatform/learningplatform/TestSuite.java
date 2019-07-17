package com.learningplatform.learningplatform;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses("**/*Test.class")
@SpringBootTest
public class TestSuite {
	
}
