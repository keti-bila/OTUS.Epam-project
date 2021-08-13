package projectWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventsPage extends AbstractPage {
    private By upcomingEventsCounter = By.xpath("//*[.='Upcoming events']/following-sibling::span[@class='evnt-tab-counter evnt-label small white']");
    private By eventCard = By.className("evnt-event-card");
    private By pastEvents = By.xpath("//*[.='Past Events']");
    private By cardLang = By.xpath("//*[@class='language']/span");
    private By cardName = By.xpath("//*[@class='evnt-event-name']/h1/span");
    private By cardDate = By.xpath("//*[@class='date']");
    private By cardRegistration = By.xpath("//*[@class='status reg-close']");
    private By cardSpeaker = By.className("evnt-speaker");

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public int eventsCounterValue() {
        String numberOfEvents = driver.findElement(upcomingEventsCounter).getText();
        return Integer.parseInt(numberOfEvents);
    }

    public int numberOfEventCards() {
        return driver.findElements(eventCard).size();
    }


}
