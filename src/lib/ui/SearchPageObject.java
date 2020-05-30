package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject
{
    private static final String
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container",
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENTS = "id:org.wikipedia:id/page_list_item_container",
        SEARCH_RESULT_ID = "id:org.wikipedia:id/page_list_item_title",
        SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL = "xpath://ancestor::*[*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '{TITLE}')] and *[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text, '{DESCRIPTION}')]]";


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
        this.WaitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click SEARCH_INIT_ELEMENT", 5);
        this.WaitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search element after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.WaitForElementAndSendKeys(SEARCH_INPUT, "Cannot find SEARCH_TEXT_FIELD or send SEARCH_INPUT keys", 5, search_line);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.WaitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.WaitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForSearchResultsToDisapper()
    {
        this.WaitForElementNotPresent(SEARCH_RESULT_ELEMENTS, "Search results are still present", 5);
    }

    public void waitForSearchResultsToAppear()
    {
        this.WaitForElementPresent(SEARCH_RESULT_ID, "Cannot find any search result", 5);
    }

    public void clickCancelSearch()
    {
        this.WaitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot press cancel search button", 5);
    }

    public void checkSearchResultsCount(int expected_quantity)
    {
        this.assertElementsCountComparision(SEARCH_RESULT_ELEMENTS, "Not expected elements count", 5, expected_quantity);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.WaitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String result_comparition_xpath = getComparedResult(title, description);
        this.WaitForElementPresent(result_comparition_xpath, "No search result with same " + title + " and " + description, 5);
    }

    public int checkFoundElementsCountWithTitleAndDescriptionSearch(String title, String description)
    {
        String result_comparition_xpath = getComparedResult(title, description);
        return this.WaitForElementsPresentAndCount(result_comparition_xpath, "Cannot find enough search results", 5);
    }

}
