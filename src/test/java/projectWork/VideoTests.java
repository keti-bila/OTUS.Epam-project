package projectWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectWork.pages.MainPage;
import projectWork.pages.TalkCardPage;
import projectWork.pages.TalksLibraryPage;
import projectWork.util.BaseHooks;

import java.util.ArrayList;

public class VideoTests extends BaseHooks {
    private static final Logger LOGGER = LogManager.getLogger(VideoTests.class);

    @Test
    public void filterVideosTest() {
        String category = "Testing";
        String location = "Belarus";
        String language = "ENGLISH";
        TalksLibraryPage talksLibraryPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToVideo();
        TalkCardPage talkCard = talksLibraryPage.chooseMoreFilters().filterByCategory(category)
                .filterByLocation(location).filterByLanguage(language)
                .openLastTalkCard();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(talkCard.getLocation().contains(location), "Location of talk card is not equal one in filter");
        softAssert.assertEquals(talkCard.getLanguage(), language, "Language of talk card is not equal one in filter");
        softAssert.assertTrue(talkCard.getListOfCategories().contains(category), "Category of talk card is not equal one in filter");
    }

    @Test
    public void searchTalksByKeywordTest() {
        String keyword = "QA";
        TalksLibraryPage talksLibraryPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToVideo();
        talksLibraryPage.searchByKeyword(keyword);
        ArrayList<String> listOfNames = talksLibraryPage.getListOfTalkNames();
        for (String name : listOfNames) {
            Assert.assertTrue(name.contains(keyword));
        }
    }
}
