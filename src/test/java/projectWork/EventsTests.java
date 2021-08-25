package projectWork;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import projectWork.pages.EventCardPage;
import projectWork.pages.EventsPage;
import projectWork.pages.MainPage;
import projectWork.util.BaseHooks;

import java.text.ParseException;
import java.util.List;

@Epic("Events page testing")
@Feature("Going to events page and validating the data")
public class EventsTests extends BaseHooks {

    @Test(description = "Open https://events.epam.com/, go to events page, verify the number of events in counter is equal to number of event cards")
    @Story("Go to events page, verify the number of events in counter is equal to number of event cards")
    @Description("Open https://events.epam.com/, go to events page, verify the number of events in counter is equal to number of event cards")
    public void counterOfEventsTest() {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getDriver()).acceptCookie()
                .navigationToolbar().goToEvents();
        Assert.assertEquals(eventsPage.numberOfEventCards(), eventsPage.upcomingEventsCounterValue(), "Number of event cards is not equals event counter");
    }

    @Test(description = "Open https://events.epam.com/, go to events page, go to past events, verify all the data is available in card")
    @Story("Go to events page, go to past events, verify all the data is available in card")
    @Description("Open https://events.epam.com/, go to events page, go to past events, verify all the data is available in card")
    public void pastEventsTest() {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getDriver()).acceptCookie()
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

    @Test(description = "Open https://events.epam.com/, go to events page, verify the dates in cards are equal or later than today")
    @Story("Go to events page, verify the dates in cards are equal or later than today")
    @Description("Open https://events.epam.com/, go to events page, verify the dates in cards are equal or later than today")
    public void upcomingEventsDateTest() throws ParseException {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getDriver()).acceptCookie()
                .navigationToolbar().goToEvents();
        List<EventCardPage> listOfCards = eventsPage.eventCards();
        for (EventCardPage card : listOfCards) {
            Assert.assertFalse(card.isEventDateInPast(), "Date of upcoming event is earlier than current date");
        }
    }

    @Test(description = "Open https://events.epam.com/, go to events page, go to past events, apply filter by location, verify the number of events in counter is equal to number of event cards")
    @Story("Go to events page, go to past events, apply filter by location, verify the number of events in counter is equal to number of event cards")
    @Description("Open https://events.epam.com/, go to events page, go to past events, apply filter by location, verify the number of events in counter is equal to number of event cards")
    public void filterPastEventsByLocationTest() throws ParseException {
        EventsPage eventsPage = MainPage.open(getCfg().homepage(), getDriver()).acceptCookie()
                .navigationToolbar().goToEvents().goToPastEvents();
        eventsPage.filterByLocation("Canada");
        Assert.assertEquals(eventsPage.numberOfEventCards(), eventsPage.pastEventsCounterValue(), "Number of event cards is not equals event counter");
        List<EventCardPage> listOfCards = eventsPage.eventCards();
        for (EventCardPage card : listOfCards) {
            Assert.assertTrue(card.isEventDateInPast(), "Date of past event is after current date");
        }
    }
}
