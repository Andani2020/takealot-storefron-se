package org.takealot.page_objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.List;

public class HomePage implements PageObjectsInterface {

    static final Logger logger = LogManager.getLogger(HomePage.class.getName());

    WebDriver driver;
    Wait<WebDriver> wait;


    By takealotLogo = By.xpath("//img[@class='top-nav-module_logo_R1oac']");
    By productSearchField = By.xpath("//input[@class='search-field ']");
    By searchButton = By.xpath("//button[@class='button search-btn search-icon']");


    @Override
    public WebElement getWebElement(String webElementName) throws InterruptedException {
        WebElement el = null;

        switch (webElementName.toLowerCase()) {
            case "takealot_logo":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(takealotLogo));
                break;
            case "search_button":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
                break;
            case "product_search_field":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(productSearchField));
                break;

        }
        assert el !=null;
        return el;
    }

    @Override
    public void typeText(String elementName, String textContent) throws InterruptedException {
        WebElement webElement = this.getWebElement(elementName);
        assert webElement != null;
        webElement.sendKeys(textContent);

    }
    @Override
    public void clickWebElement(String elementName) throws InterruptedException {
        WebElement webElement = this.getWebElement(elementName);
        assert webElement != null;
        webElement.click();
        driver.quit();

    }

    public void clickItemByAttributeValue(String attribute, String action, String expectedValue) {
        WebElement childElement = null;
        List<WebElement> childElements = driver.findElements(By.className(attribute));

        logger.debug(MessageFormat.format("Child element count = \"{0}\"", childElements.size()));
        for (int i = 0; i <= (childElements.size() - 1); i++) {
            childElement = childElements.get(i);
            String actualText = childElement.getAttribute(attribute);
            logger.debug(MessageFormat.format("Value of name in the child element \"{0}\" ", i, actualText));
            if (actualText != null) {
                if (action.equals("equal_to")) {
                    logger.debug(MessageFormat.format("Checking if \"{0}\" is equal to \"{1}\"", expectedValue, actualText));
                    if (actualText.equals(expectedValue)) {
                        childElement.click();
                    };
                } else if (action.equals("contains")) {
                    logger.debug(MessageFormat.format("Checking if \"{0}\" is in \"{1}\"", expectedValue, actualText));
                    if (actualText.contains(expectedValue)) {
                        childElement.click();
                    };
                } else {
                    logger.error(MessageFormat.format("Action \"{0}\" is not supported. Supported actions are \"equal_to\" or \"contains\"", action));

                }

            }
        }

    }

    @Override
    public boolean verifyWebElementText(String pageElementName, String action, String expectedValue) throws InterruptedException {
        WebElement element = this.getWebElement(pageElementName);
        String actualValue = element.getText().trim();
        logger.debug("Actual value = "+ actualValue);
        logger.debug("Expected value = "+ expectedValue);

        if(action.equals("contains")){
            return actualValue.contains(expectedValue.trim());
        } else if (action.equals("is_equal_to")) {
            return actualValue.equals(expectedValue);
        }else {

            logger.error(MessageFormat.format("Comparison type {0} is unsupported",action));
        }
        return false;
    }

    @Override
    public boolean verifyWebElementIsDisplayed(String webElementName) throws InterruptedException {
        WebElement pageElement = null;
        pageElement = getWebElement(webElementName);
        logger.debug("Attempting to verify element where name = " + webElementName + " is displayed");

        if (pageElement.isDisplayed()) {
            logger.debug("Element is Displayed = " + pageElement.isDisplayed());
            return true;
        }
        logger.debug(webElementName + " Element is not visible= " + pageElement.isDisplayed());
        return false;
    }

}
