package projectWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TalksLibraryPage extends AbstractPage {
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

    public TalksLibraryPage chooseMoreFilters() {
        driver.findElement(moreFilters).click();
        waitForElementToBeGone(loader);
        return this;
    }

    public TalksLibraryPage filterByCategory(String category) {
        applyFilter(categoryFilter, category);
        return this;
    }

    public TalksLibraryPage filterByLocation(String location) {
        applyFilter(locationFilter, location);
        return this;
    }

    public TalksLibraryPage filterByLanguage(String language) {
        applyFilter(languageFilter, language);
        return this;
    }

    private void applyFilter(By filterName, String filterValue) {
        By filter = By.xpath("//label[@data-value='" + filterValue + "']");
        driver.findElement(filterName).click();
        driver.findElement(filter).click();
        waitForElementToBeGone(loader);
    }

    public TalkCardPage openLastTalkCard() {
        List<WebElement> listOfTalks = driver.findElements(talkCardName);
        WebElement lastTalkCard = listOfTalks.get(listOfTalks.size() - 1);
        actions.moveToElement(driver.findElement(footer)).perform();
        lastTalkCard.click();
        return new TalkCardPage(driver);
    }

    public TalksLibraryPage searchByKeyword(String keyword) {
        driver.findElement(searchInput).sendKeys(keyword);
        waitForElementToBeGone(loader);
        return this;
    }

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
