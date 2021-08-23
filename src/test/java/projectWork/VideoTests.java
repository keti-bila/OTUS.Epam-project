package projectWork;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectWork.pages.MainPage;
import projectWork.pages.TalkCardPage;
import projectWork.pages.TalksLibraryPage;
import projectWork.util.BaseHooks;

import java.util.ArrayList;

@Epic("Talk library page testing")
@Feature("Going to videos page and validating the data")
public class VideoTests extends BaseHooks {

    @Test(description = "Open https://events.epam.com/, go to video page, apply filters, verify the first card has information that was filtered")
    @Story("Go to video page, apply filters, verify the first card has information that was filtered")
    @Description("Open https://events.epam.com/, go to video page, apply filters, verify the first card has information that was filtered")
    public void filterVideosTest() {
        String category = "Testing";
        String location = "Belarus";
        String language = "ENGLISH";
        TalksLibraryPage talksLibraryPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToVideo();
        TalkCardPage talkCard = talksLibraryPage.chooseMoreFilters().filterByCategory(category)
                .filterByLocation(location).filterByLanguage(language)
                .openFirstTalkCard();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(talkCard.getLocation().contains(location), "Location of talk card is not equal one in filter");
        softAssert.assertEquals(talkCard.getLanguage(), language, "Language of talk card is not equal one in filter");
        softAssert.assertTrue(talkCard.getListOfCategories().contains(category), "Category of talk card is not equal one in filter");
    }

    @Test(description = "Open https://events.epam.com/, go to video page, enter keyword in search, verify all the card names have a keyword")
    @Story("Go to video page, apply filters, enter keyword in search, verify all the card names have a keyword")
    @Description("Open https://events.epam.com/, go to video page, enter keyword in search, verify all the card names have a keyword")
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
