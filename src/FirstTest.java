import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

//    private MainPageObject MainPageObject;
//    protected void setUp() throws Exception
//    {
//        super.setUp();
//
//        MainPageObject = new MainPageObject(driver);
//    }

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
        //Assert.assertEquals("Articles with successfully compared titles and descriptions are lower " + actual_results_count + " then expected " + expected_results_count, expected_results_count, actual_results_count);
        Assert.assertTrue("Actual resulsts count " + actual_results_count + " is lower then expected " + expected_results_count, actual_results_count > expected_results_count);
    }



}
