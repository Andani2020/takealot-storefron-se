package org.takealot.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.takealot.page_objects.HomePage;
import org.takealot.page_objects.ProductPage;
import org.takealot.page_objects.SetPageObjects;

import java.text.MessageFormat;

public class MyStepdefs {
    private static final Logger logger = LogManager.getLogger(MyStepdefs.class);
    WebDriver driver;
    SetPageObjects setPageObjects;
    HomePage homePage;
    ProductPage productPage;
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

    @Given("^the user navigates to \"([^\"]*)\" url")
    public void theUserNavigatesToUrl(String url) {

        // creating object of ChromeDriver
        driver = new ChromeDriver();
        // to configure the path of the chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/\n");

        driver.get(url);
    }

    @And("^the user clicks \"([^\"]*)\" page element on \"([^\"]*)\" page")
    public void theUserClicksPageElement(String elementName, String pageObjectName) {

        try {
            this.setPageObjects = new SetPageObjects(pageObjectName);
            this.setPageObjects.getPageObject().clickWebElement(elementName);
        } catch (Exception e) {
            logger.error(MessageFormat.format("An unexpected exception occurred while attempting to click item \"{0}\"", elementName));
            logger.debug(MessageFormat.format("Error cause: \"{0}\"", e.getCause()));
            e.printStackTrace();
        }

    }

    @And("^the user types \"([^\"]*)\" into \"([^\"]*)\" page element on \"([^\"]*)\" page")
    public void theUserTypesIntoPageElementOnPage(String textContent, String elementName, String pageObjectName) {

        try {
            this.setPageObjects = new SetPageObjects(pageObjectName);
            this.setPageObjects.getPageObject().typeText(elementName,textContent);
        } catch (Exception e) {
            logger.error(MessageFormat.format("An unexpected exception occurred while typing text into \"{0}\"", elementName));
            logger.debug(MessageFormat.format("Error cause: \"{0}\"", e.getCause()));
            e.printStackTrace();
        }

    }

    @And("^the user verifies that the text content of \"([^\"]*)\" page element \"([^\"]*)\" \"([^\"]*)\" on \"([^\"]*)\" page")
    public void theUserVerifiesThatTheTextContentOfPageElementOnPage(String pageElementName, String action, String expectedValue,String pageObjectName ) {

        try {
            this.setPageObjects = new SetPageObjects(pageObjectName);
            assertThat(this.setPageObjects.getPageObject().verifyWebElementText(pageElementName,action,expectedValue)).isTrue();
        } catch (Exception e) {
            logger.error(MessageFormat.format("An unexpected exception occurred while trying to compare page element text content \"{0}\" and  attribute value: \"{1}\"", pageElementName,expectedValue));
            logger.debug(MessageFormat.format("Error cause: \"{0}\"", e.getCause()));
            e.printStackTrace();
        }

    }

    @And("^the user clicks web element from the list with attribute \"([^\"]*)\" where attribute value \"([^\"]*)\" \"([^\"]*)\" on \"([^\"]*)\"  page")
    public void theUserClicksWebElementWithAttributeWhereAttributeValueOnPage(String attribute, String action, String expectedValue,String pageObjectName) {

        try {
            this.setPageObjects = new SetPageObjects(pageObjectName);
            this.setPageObjects.getPageObject().clickItemByAttributeValue(attribute,action,expectedValue);
        } catch (Exception e) {
            logger.error(MessageFormat.format("An unexpected exception occurred while trying to click a page element with attribute \"{0}\" and attribute value: \"{1}\"", attribute,expectedValue));
            logger.debug(MessageFormat.format("Error cause: \"{0}\"", e.getCause()));
            e.printStackTrace();
        }
    }
}


