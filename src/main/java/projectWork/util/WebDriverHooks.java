package projectWork.util;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import projectWork.ServerConfig;

import java.util.concurrent.TimeUnit;

public class WebDriverHooks {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(WebDriverHooks.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class, System.getProperties(), System.getenv());

    public void setDriver() {
/**
 * Initialize driver for local test run (comment or uncomment necessary option)
 */
        this.driver = WebDriverFactory.createDriver(Browsers.getBrowserByString(cfg.browser()));
/**
 * Initialize driver for remote test run (comment or uncomment necessary option)
 */
        //        this.driver = WebDriverFactory.createDriver();
        logger.info("Driver is initialised");
        if (this.driver != null) {
            this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
        }
    }

    public void shutDownDriver() {
        if (this.driver != null) {
            this.driver.quit();
            logger.info("Browser is closed");
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver should be initialised");
        }
        return driver;
    }

    public void clearCookies() {
        if (driver == null) {
            throw new IllegalStateException("Driver should be initialised");
        }
        driver.manage().deleteAllCookies();
        logger.info("All cookies are deleted");
    }
}
