package org.takealot.page_objects;
import org.openqa.selenium.WebElement;
public interface PageObjectsInterface {

    public WebElement getWebElement(String webElementName)throws InterruptedException;
    public void typeText(String elementName,String textContent) throws InterruptedException;
    public boolean verifyWebElementText(String attributeName,String action,String expectedValue) throws InterruptedException;
    public boolean verifyWebElementIsDisplayed(String webElementName) throws InterruptedException;
    public void clickWebElement(String elementName) throws InterruptedException;
    public void clickItemByAttributeValue(String attribute, String action, String expectedValue);

}
