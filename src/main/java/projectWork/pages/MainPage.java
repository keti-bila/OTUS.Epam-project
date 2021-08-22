package projectWork.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(MainPage.class);
    private By cookieButton = By.id("onetrust-accept-btn-handler");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Main page is open")
    public static MainPage open(String url, WebDriver driver) {
        driver.get(url);
        logger.info("Main page is open");
        return new MainPage(driver);
    }

    public NavigationToolBar navigationToolbar() {
        return new NavigationToolBar(driver);
    }

    @Step("Cookies are accepted")
    public MainPage acceptCookie() {
        if (cookieMessageVisible(cookieButton)) {
            driver.findElement(cookieButton).click();
            logger.info("Cookies are accepted");
        }
        return this;
    }

    private boolean cookieMessageVisible(By selector) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
