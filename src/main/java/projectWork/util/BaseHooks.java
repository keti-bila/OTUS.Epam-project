package projectWork.util;

import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import projectWork.ServerConfig;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BaseHooks {
    private WebDriverHooks webDriverHooks = new WebDriverHooks();
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class, System.getProperties(), System.getenv());

    public WebDriverHooks getWebDriverHooks() {
        return webDriverHooks;
    }

    public ServerConfig getCfg() {
        return cfg;
    }

    @BeforeClass
    public void setUp() {
        webDriverHooks.setDriver();
    }

    @AfterClass
    public void tearDown() {
        webDriverHooks.shutDownDriver();
    }

    @AfterMethod
    public void cleanUp() {
        webDriverHooks.clearCookies();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment("Test was failed",
                    new ByteArrayInputStream(((TakesScreenshot) webDriverHooks.getDriver()).getScreenshotAs(OutputType.BYTES)));
        }
    }
}
