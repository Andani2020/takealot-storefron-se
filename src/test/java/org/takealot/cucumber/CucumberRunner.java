package org.takealot.cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org/takealot/stepdefinitions/MyStepdefs"},
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber-reports"}


)
public class CucumberRunner {
}
