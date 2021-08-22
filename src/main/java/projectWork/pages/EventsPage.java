package projectWork.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class EventsPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(EventsPage.class);
    private By upcomingEventsCounter = By.xpath("//*[.='Upcoming events']/following-sibling::span[@class='evnt-tab-counter evnt-label small white']");
    private By pastEventsCounter = By.xpath("//*[.='Past Events']/following-sibling::span[@class='evnt-tab-counter evnt-label small white']");
    private By eventCard = By.className("evnt-event-card");
    private By pastEvents = By.xpath("//*[.='Past Events']");
    private By loader = By.className("evnt-loader");
    private By locationFilter = By.xpath("//span[.='Location']");

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checking the number of upcoming events in counter")
    public int upcomingEventsCounterValue() {
        String numberOfEvents = driver.findElement(upcomingEventsCounter).getText();
        return Integer.parseInt(numberOfEvents);
    }

    @Step("Checking the number of past events in counter")
    public int pastEventsCounterValue() {
        String numberOfEvents = driver.findElement(pastEventsCounter).getText();
        return Integer.parseInt(numberOfEvents);
    }

    @Step("Checking the number of event cards")
    public int numberOfEventCards() {
        return driver.findElements(eventCard).size();
    }

    @Step("Switch to past events")
    public EventsPage goToPastEvents() {
        driver.findElement(pastEvents).click();
        this.waitForElementToBeGone(loader);
        logger.info("Switched to past events");
        return this;
    }

    @Step("Getting list of event cards")
    public List<EventCardPage> eventCards() {
        List<WebElement> eventWebElements = driver.findElements(eventCard);
        List<EventCardPage> eventCards = new ArrayList<>();
        for (int i = 0; i < eventWebElements.size(); i++) {
            WebElement cardWebElement = eventWebElements.get(i);
            EventCardPage cardPage = new EventCardPage(driver, cardWebElement);
            eventCards.add(cardPage);
        }
        return eventCards;
    }

    @Step("Filtering by location")
    public EventsPage filterByLocation(String country) {
        driver.findElement(locationFilter).click();
        By countryFilter = By.xpath("//label[@data-value='" + country + "']");
        driver.findElement(countryFilter).click();
        waitForElementToBeGone(loader);
        logger.info("Filter by location {} is applied", country);
        return this;
    }
}
