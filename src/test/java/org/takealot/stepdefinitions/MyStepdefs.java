package org.takealot.stepdefinitions;
import static org.assertj.core.api.Assertions.assertThat;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class MyStepdefs {
    private static final Logger logger = LogManager.getLogger(MyStepdefs.class);
    WebDriver driver;
    public MyStepdefs() {}



        @Given("^accept an alert if it exists$")
        public void acceptAlertIfExists() {
            try {
                Alert alert = this.driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException e) {
                logger.debug("No alert popped up");
            } finally {
                assertThat(true).isTrue();
            }
        }
    }

