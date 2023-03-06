package org.takealot.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetPageObjects {
    PageObjectsInterface pageObjectsInterface;
    String pageObjectName;

    public SetPageObjects(){

        this.pageObjectsInterface = null;
    }

    public SetPageObjects(String objectName) {
        this();
        this.pageObjectName = objectName;

    }

    private void setPageObject(PageObjectsEnum objName) {
        switch (objName) {
            case HOMEPAGE:
                this.pageObjectsInterface = new HomePage();
                break;
            case PRODUCTPAGE:
                this.pageObjectsInterface =  new ProductPage();
                break;
            default:

        }
    }

    public PageObjectsInterface  getPageObject(  ){

        switch(pageObjectName.toLowerCase().trim()) {

            case "home_page":
                this.setPageObject(PageObjectsEnum.HOMEPAGE);
                break;
            case "product_page":
                this.setPageObject(PageObjectsEnum.PRODUCTPAGE);
                break;
            default:
                this.pageObjectsInterface = null;
        }
        assert this.pageObjectsInterface != null;
        return this.pageObjectsInterface;

    }

}
