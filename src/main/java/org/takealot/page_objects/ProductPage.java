package org.takealot.page_objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.text.MessageFormat;
import java.util.List;

public class ProductPage implements PageObjectsInterface {

    static final Logger logger = LogManager.getLogger(ProductPage.class.getName());
    WebDriver driver;
    Wait<WebDriver> wait;

    By productList = By.xpath("//div[@class='listing-card listing-card-module_listing-card_yG8If']");
    By viewListButton = By.xpath("//div [@class='grid-list-toggle-module_list_219_N grid-list-toggle-module_active_L4DIQ grid-list-toggle-module_toggle-option_gCW0Q']");
    By productTittleList = By.xpath("//h3[@class=\"title\"]");
    By productTittle = By.xpath("//div[@class='product-title']");
    By addToCart = By.xpath("//a[@class='button  expanded add-to-cart-button add-to-cart-button-module_add-to-cart-button_1a9gT']");
    By addedToCart = By.xpath("//span[@class='drawer-screen-module_one-line_3dnN0']");
    By go_to_cart_button = By.xpath("//button[@class='button checkout-now dark']");
    By shoppingCart = By.xpath("//h1[@class='cart-content-module_title_24yoi']");
    By cartProductTittle = By.xpath("//h3[@class='cart-item-module_item-title_1M9cq']");
    By checkOutButton = By.xpath("//a[contains(@class,'checkout-button')]");

    @Override
    public WebElement getWebElement(String webElementName) {
        WebElement el = null;
        switch (webElementName.toLowerCase()) {
            case "product_list":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(productList));
                break;
            case "view_list_button":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(viewListButton));
                break;
            case "product_tittle_list":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(productTittleList));
                break;
            case "pdp_product_tittle":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(productTittle));
                break;
            case "add_to_cart":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart));
                break;
            case "added_to_cart":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(addedToCart));
                break;
            case "go_to_cart":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(go_to_cart_button));
                break;
            case "shopping_cart":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCart));
                break;
            case "cart_product_tittle":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductTittle));
                break;
            case "check_out_button":
                el = this.wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutButton));
                break;
        }
        assert el !=null;
        return el;
    }
    @Override
    public void typeText(String elementName, String textContent) {
        WebElement webElement = this.getWebElement(elementName);
        assert webElement != null;
        webElement.sendKeys(textContent);

    }
    @Override
    public void clickWebElement(String elementName) {
        WebElement webElement = this.getWebElement(elementName);
        assert webElement != null;
        webElement.click();
        driver.quit();

    }
    public void clickItemByAttributeValue(String attribute, String action, String expectedValue) {
        WebElement childElement;
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
                    }
                } else if (action.equals("contains")) {
                    logger.debug(MessageFormat.format("Checking if \"{0}\" is in \"{1}\"", expectedValue, actualText));
                    if (actualText.contains(expectedValue)) {
                        childElement.click();
                    }
                } else {
                    logger.error(MessageFormat.format("Action \"{0}\" is not supported. Supported actions are \"equal_to\" or \"contains\"", action));

                }

            }
        }

    }

    @Override
    public boolean verifyWebElementText(String pageElementName, String action, String expectedValue) {
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
    public boolean verifyWebElementIsDisplayed(String webElementName) {
        WebElement pageElement;
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
