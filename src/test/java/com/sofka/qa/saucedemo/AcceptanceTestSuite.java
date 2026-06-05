package com.sofka.qa.saucedemo;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Runner principal: descubre los features de Cucumber y los ejecuta a traves del
 * motor JUnit 5 Platform. El glue y los plugins de Serenity se configuran en
 * src/test/resources/junit-platform.properties.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class AcceptanceTestSuite {
}
