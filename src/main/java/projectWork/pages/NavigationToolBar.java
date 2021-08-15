package projectWork.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationToolBar extends AbstractPage {
    private Logger logger = LogManager.getLogger(NavigationToolBar.class);
    private By eventsButton = By.xpath("//ul[@class='evnt-navigation navbar-nav']/li[2]/a");
    private By videoButton = By.xpath("//ul[@class='evnt-navigation navbar-nav']/li[3]/a");
    private By calendarButton = By.xpath("//ul[@class='evnt-navigation navbar-nav']/li[1]/a");
    private By loader = By.className("evnt-loader");


    public NavigationToolBar(WebDriver driver) {
        super(driver);
    }

    public EventsPage goToEvents() {
        driver.findElement(eventsButton).click();
        this.waitForElementToBeGone(loader);
        logger.info("Events are opened");
        return new EventsPage(driver);
    }

    public TalksLibraryPage goToVideo() {
        driver.findElement(videoButton).click();
        this.waitForElementToBeGone(loader);
        return new TalksLibraryPage(driver);
    }
}
