package lib.ui;

import io.appium.java_client.AppiumDriver;
import net.sf.cglib.asm.$AnnotationVisitor;
import org.openqa.selenium.By;
import sun.jvm.hotspot.utilities.Assert;

public class SearchPageObject extends MainPageObject
{
    private static final String
        SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENTS = "org.wikipedia:id/page_list_item_container",
        SEARCH_RESULT_ID = "org.wikipedia:id/page_list_item_title",
        SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL = "//ancestor::*[*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '{TITLE}')] and *[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text, '{DESCRIPTION}')]]";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public static String getComparedResult(String title, String description)
    {
        return SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput()
    {
        this.WaitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click SEARCH_INIT_ELEMENT", 5);
        this.WaitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Cannot find search element after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.WaitForElementAndSendKeys(By.id(SEARCH_INPUT), "Cannot find SEARCH_TEXT_FIELD or send SEARCH_INPUT keys", 5, search_line);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.WaitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.WaitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForSearchResultsToDisapper()
    {
        this.WaitForElementNotPresent(By.id(SEARCH_RESULT_ELEMENTS), "Search results are still present", 5);
    }

    public void waitForSearchResultsToAppear()
    {
        this.WaitForElementPresent(By.id(SEARCH_RESULT_ID), "Cannot find any search result", 5);
    }

    public void clickCancelSearch()
    {
        this.WaitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot press cancel search button", 5);
    }

    public void checkSearchResultsCount(int expected_quantity)
    {
        this.assertElementsCountComparision(By.id(SEARCH_RESULT_ELEMENTS), "Not expected elements count", 5, expected_quantity);
        //int results_count = this.WaitForElementsPresentAndCount(By.id(SEARCH_RESULT_ELEMENTS),"Cannot find search results", 5);

    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.WaitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String result_comparition_xpath = getComparedResult(title, description);
        this.WaitForElementPresent(By.xpath(result_comparition_xpath), "No search result with same " + title + " and " + description, 5);
    }

    public int checkFoundElementsCountWithTitleAndDescriptionSearch(String title, String description)
    {
        String result_comparition_xpath = getComparedResult(title, description);
        return this.WaitForElementsPresentAndCount(By.xpath(result_comparition_xpath), "Cannot find enough search results", 5);
    }

}
