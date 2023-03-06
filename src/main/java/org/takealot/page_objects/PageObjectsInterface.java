package org.takealot.page_objects;
import org.openqa.selenium.WebElement;
public interface PageObjectsInterface {

    WebElement getWebElement(String webElementName)throws InterruptedException;
    void typeText(String elementName,String textContent) throws InterruptedException;
    boolean verifyWebElementText(String attributeName,String action,String expectedValue) throws InterruptedException;
    boolean verifyWebElementIsDisplayed(String webElementName) throws InterruptedException;
    void clickWebElement(String elementName) throws InterruptedException;
    void clickItemByAttributeValue(String attribute, String action, String expectedValue);

}
