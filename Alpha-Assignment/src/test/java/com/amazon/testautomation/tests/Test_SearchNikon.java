package com.amazon.testautomation.tests;

import com.amazon.testautomation.setup_teardown.SetUpTeardown;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = false,
        monochrome = false,
        format = {"pretty", "html:target/cucumber/html-report", "json:target/cucumber/json-report.json"},
        glue = {"com.amazon.testautomation"},
        features = {"src/test/resources/features/"},
        tags = {"@SearchNikon"})

public class Test_SearchNikon extends SetUpTeardown {
}
