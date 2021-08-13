package projectWork.util;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import projectWork.ServerConfig;

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
}
