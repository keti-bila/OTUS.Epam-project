package projectWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventCardPage extends AbstractPage {
    private WebElement element;
    private By cardLang = By.xpath("//*[@class='language']/span");
    private By cardName = By.xpath("//*[@class='evnt-event-name']/h1/span");
    private By cardDate = By.xpath("//*[@class='date']");
    private By cardRegistration = By.xpath("//*[@class='status reg-close']");
    private By cardSpeaker = By.className("evnt-speaker");

    public EventCardPage(WebDriver driver, WebElement element) {
        super(driver);
        this.element = element;
    }

    public String getCardLang() {
        return element.findElement(cardLang).getText();
    }

    public String getCardName() {
        return element.findElement(cardName).getText();
    }

    public String getCardDate() {
        return element.findElement(cardDate).getText();
    }

    public String getCardRegistration() {
        return element.findElement(cardRegistration).getText();
    }

    public boolean getCardSpeaker() {
        return element.findElement(cardSpeaker).isDisplayed();
    }

    public boolean checkIfDateIsInPast() throws ParseException {
        Date today = new Date();
        String date = this.getCardDate();
        if (date.contains(" - ")) {
            String[] dates = date.split(" - ");
            date = dates[1];
        }
        Date eventDate = new SimpleDateFormat("d MMM yyyy").parse(date);
        return eventDate.before(today);
    }
}
