package projectWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectWork.pages.EventCardPage;
import projectWork.pages.EventsPage;
import projectWork.pages.MainPage;
import projectWork.util.BaseHooks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventsTests extends BaseHooks {
    private static final Logger LOGGER = LogManager.getLogger(EventsTests.class);

    @Test
    public void counterOfEventsTest() {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToEvents();
        Assert.assertEquals(eventsPage.numberOfEventCards(), eventsPage.upcomingEventsCounterValue(), "Number of event cards is not equals event counter");
    }

    @Test
    public void pastEventsTest() {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToEvents().goToPastEvents();
        SoftAssert softAssert = new SoftAssert();
        List<EventCardPage> listOfCards = eventsPage.eventCards();
        for (EventCardPage card : listOfCards) {
            softAssert.assertFalse(card.getCardLang().isEmpty(), "Language in event card is not indicated");
            softAssert.assertFalse(card.getCardName().isEmpty(), "Name of event in event card is not indicated");
            softAssert.assertFalse(card.getCardDate().isEmpty(), "Date of event in event card is not indicated");
            softAssert.assertFalse(card.getCardRegistration().isEmpty(), "Registration in event card is not indicated");
            softAssert.assertTrue(card.getCardSpeaker(), "Speaker in event card is not indicated");
        }
        softAssert.assertAll();
    }

    @Test
    public void upcomingEventsDateTest() throws ParseException {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToEvents();
        List<EventCardPage> listOfCards = eventsPage.eventCards();
        for (EventCardPage card : listOfCards) {
            Assert.assertFalse(card.checkIfDateIsInPast(), "Date of upcoming event is earlier than current date");
        }
    }

    @Test
    public void filterPastEventsByLocationTest() throws ParseException {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getWebDriverHooks().getDriver()).acceptCookie()
                .navigationToolbar().goToEvents().goToPastEvents();
        eventsPage.filterByLocation("Canada");
        Assert.assertEquals(eventsPage.numberOfEventCards(), eventsPage.pastEventsCounterValue(), "Number of event cards is not equals event counter");
        List<EventCardPage> listOfCards = eventsPage.eventCards();
        for (EventCardPage card : listOfCards) {
            Assert.assertTrue(card.checkIfDateIsInPast(), "Date of past event is after current date");
        }
    }
}
