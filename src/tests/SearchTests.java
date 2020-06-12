package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test //Ex3: Тест: отмена поиска
    public void testSearchCancellation()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkSearchResultsCount(1);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsToDisapper();
    }

    @Test //Ex9*: Рефакторинг темплейта
    public void testSearchResultsTitlesCheck()
    {
        String textToSearch = "Hitler";
        int expected_results_count = 3;
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.waitForElementByTitleAndDescription(textToSearch, textToSearch);
        int actual_results_count = SearchPageObject.checkFoundElementsCountWithTitleAndDescriptionSearch(textToSearch,textToSearch);
        Assert.assertTrue("Actual resulsts count " + actual_results_count + " is lower then expected " + expected_results_count, actual_results_count > expected_results_count);
    }
}
