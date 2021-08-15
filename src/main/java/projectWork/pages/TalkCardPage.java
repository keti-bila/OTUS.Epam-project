package projectWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TalkCardPage extends AbstractPage {
    private By location = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']/span");
    private By language = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']/span");
    private By category = By.xpath("//div[@class='evnt-topic evnt-talk-topic small gray']/label");

    public TalkCardPage(WebDriver driver) {
        super(driver);
    }

    public String getLocation() {
        return driver.findElement(location).getText();
    }

    public String getLanguage() {
        return driver.findElement(language).getText();
    }

    public ArrayList<String> getListOfCategories() {
        ArrayList<String> listOfCategories = new ArrayList<>();
        List<WebElement> listOfElements = driver.findElements(category);
        for (WebElement element : listOfElements) {
            String category = element.getText();
            listOfCategories.add(category);
        }
        return listOfCategories;
    }
}
