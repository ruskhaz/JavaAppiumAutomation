package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUi extends MainPageObject{

    protected static String
        MY_LISTS_BUTTON;
    //= "xpath://*[@content-desc='My lists']";

    public NavigationUi(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.WaitForElementAndClick(
                MY_LISTS_BUTTON,
                "Cannot find 'My lists' button",
                5
        );
    }

}
