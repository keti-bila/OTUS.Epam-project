package projectWork.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TalksLibraryPage extends AbstractPage {
    private Logger logger = LogManager.getLogger(TalksLibraryPage.class);
    private By loader = By.className("evnt-loader");
    private By footer = By.className("evnt-footer-wrapper");
    private By moreFilters = By.xpath("//span[.='More Filters']");
    private By categoryFilter = By.xpath("//span[.='Category']");
    private By locationFilter = By.xpath("//span[.='Location']");
    private By languageFilter = By.xpath("//span[.='Language']");
    private By talkCardName = By.xpath("//div[@class='evnt-talk-name']/h1/span");
    private By searchInput = By.xpath("//input[@placeholder='Search by Talk Name']");

    public TalksLibraryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click more filters")
    public TalksLibraryPage chooseMoreFilters() {
        driver.findElement(moreFilters).click();
        waitForElementToBeGone(loader);
        return this;
    }

    @Step("Filter by category")
    public TalksLibraryPage filterByCategory(String category) {
        applyFilter(categoryFilter, category);
        logger.info("Filter by category {} is applied", category);
        return this;
    }

    @Step("Filter by location")
    public TalksLibraryPage filterByLocation(String location) {
        applyFilter(locationFilter, location);
        logger.info("Filter by location {} is applied", location);
        return this;
    }

    @Step("Filter by language")
    public TalksLibraryPage filterByLanguage(String language) {
        applyFilter(languageFilter, language);
        logger.info("Filter by language {} is applied", language);
        return this;
    }

    private void applyFilter(By filterName, String filterValue) {
        By filter = By.xpath("//label[@data-value='" + filterValue + "']");
        driver.findElement(filterName).click();
        driver.findElement(filter).click();
        waitForElementToBeGone(loader);
    }

    @Step("Open first talk card")
    public TalkCardPage openFirstTalkCard() {
        List<WebElement> listOfTalks = driver.findElements(talkCardName);
        WebElement firstTalkCard = listOfTalks.get(0);
        actions.moveToElement(driver.findElement(footer)).perform();
        firstTalkCard.click();
        waitForElementToBeGone(loader);
        logger.info("First Talk card is open");
        return new TalkCardPage(driver);
    }

    @Step("Searching by keyword")
    public TalksLibraryPage searchByKeyword(String keyword) {
        driver.findElement(searchInput).sendKeys(keyword);
        waitForElementToBeGone(loader);
        logger.info("Search word {} is entered", keyword);
        return this;
    }

    @Step("Get list of talk names")
    public ArrayList<String> getListOfTalkNames() {
        ArrayList<String> listOfTalkNames = new ArrayList<>();
        List<WebElement> talkNamesElements = driver.findElements(talkCardName);
        String name;
        for (WebElement talkName : talkNamesElements) {
            name = talkName.getText();
            listOfTalkNames.add(name);
        }
        return listOfTalkNames;
    }
}
