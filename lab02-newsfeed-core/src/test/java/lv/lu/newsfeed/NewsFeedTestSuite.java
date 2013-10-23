package lv.lu.newsfeed;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lv.lu.newsfeed.impl.JpaDAOImplTest;
import lv.lu.newsfeed.model.DataModelTest;

/**
 * Main test suite. Run as JUnit to execute all tests.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
		JpaDAOImplTest.class,
		DataModelTest.class})
public class NewsFeedTestSuite {
}