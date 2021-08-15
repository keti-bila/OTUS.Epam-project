package projectWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
    }

    boolean isElementDisplayed(By elementSelector) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, 1);
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(elementSelector));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    void waitForElementToBeGone(By elementSelector) {
        if (isElementDisplayed(elementSelector)) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(elementSelector));
        }
    }
}
