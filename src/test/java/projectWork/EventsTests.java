package projectWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import projectWork.pages.EventsPage;
import projectWork.pages.MainPage;
import projectWork.util.BaseHooks;

public class EventsTests extends BaseHooks {
    private static final Logger LOGGER = LogManager.getLogger(EventsTests.class);

    @Test
    public void CounterOfEventsTest() {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).goToNavigate().goToEvents();
        Assert.assertEquals(eventsPage.numberOfEventCards(), eventsPage.eventsCounterValue(), "Number of event cards is not equals event counter");
    }
}
