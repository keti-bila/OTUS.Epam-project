package projectWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsPage extends AbstractPage {
    private By upcomingEventsCounter = By.xpath("//*[.='Upcoming events']/following-sibling::span[@class='evnt-tab-counter evnt-label small white']");
    private By pastEventsCounter = By.xpath("//*[.='Past Events']/following-sibling::span[@class='evnt-tab-counter evnt-label small white']");
    private By eventCard = By.className("evnt-event-card");
    private By pastEvents = By.xpath("//*[.='Past Events']");
    private By loader = By.className("evnt-loader");
    private By locationFilter = By.xpath("//span[.='Location']");

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public int upcomingEventsCounterValue() {
        String numberOfEvents = driver.findElement(upcomingEventsCounter).getText();
        return Integer.parseInt(numberOfEvents);
    }

    public int pastEventsCounterValue() {
        String numberOfEvents = driver.findElement(pastEventsCounter).getText();
        return Integer.parseInt(numberOfEvents);
    }

    public int numberOfEventCards() {
        return driver.findElements(eventCard).size();
    }

    public EventsPage goToPastEvents() {
        actions.moveToElement(driver.findElement(pastEvents)).perform();
        driver.findElement(pastEvents).click();
        this.waitForElementToBeGone(loader);
        return this;
    }

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

    public EventsPage filterByLocation(String country) {
        driver.findElement(locationFilter).click();
        By countryFilter = By.xpath("//label[@data-value='" + country + "']");
        driver.findElement(countryFilter).click();
        waitForElementToBeGone(loader);
        return this;
    }
}
