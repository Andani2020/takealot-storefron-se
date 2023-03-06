package org.takealot.cucumber;

import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@smoke",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberRunnerOptions {
}
