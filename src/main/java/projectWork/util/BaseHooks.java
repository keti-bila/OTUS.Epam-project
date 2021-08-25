package projectWork.util;

import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import projectWork.ServerConfig;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

public class BaseHooks {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(BaseHooks.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class, System.getProperties(), System.getenv());

    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver should be initialised");
        }
        return driver;
    }

    public ServerConfig getCfg() {
        return cfg;
    }

    @BeforeClass
    public void setUp() {
        if (cfg.isDriverRemote()) {
            this.driver = WebDriverFactory.createDriver();
        } else {
            this.driver = WebDriverFactory.createDriver(Browsers.getBrowserByString(cfg.browser()));
        }
        logger.info("Driver is initialised");
        if (this.driver != null) {
            this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
        }
    }

    @AfterClass
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
            logger.info("Browser is closed");
        }
    }

    @AfterMethod
    public void cleanUp() {
        if (driver == null) {
            throw new IllegalStateException("Driver should be initialised");
        }
        driver.manage().deleteAllCookies();
        logger.info("All cookies are deleted");
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment("Test was failed",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
}
