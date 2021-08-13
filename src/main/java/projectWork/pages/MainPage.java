package projectWork.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage{
    private static Logger logger = LogManager.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public static MainPage open(String url, WebDriver driver) {
        driver.get(url);
        logger.info("Main page is open");
        return new MainPage(driver);
    }

    public NavigationToolBar goToNavigate() {
        return new NavigationToolBar(driver);
    }
}
