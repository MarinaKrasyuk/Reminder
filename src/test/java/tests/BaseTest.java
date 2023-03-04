package tests;

import data.ListData;
import driver.Driver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import screens.*;
import utils.JsonParser;
import utils.Utils;
import java.net.MalformedURLException;
import java.util.List;

public class BaseTest {

    public static IOSDriver driver;
    protected static List<ListData> createdReminderList;
    protected static List<ListData> actualList;
    protected static OnboardingScreen onboardingScreen;
    protected static ReminderScreen reminderScreen;
    protected static NewListScreen newListScreen;
    protected static NewReminderScreen newReminderScreen;
    protected static DetailsReminderScreen detailsReminderScreen;

    @BeforeAll
    public static void initialDriver() throws MalformedURLException {
        driver = Driver.getDriver();
        createdReminderList = JsonParser.getReminerList();
        onboardingScreen = new OnboardingScreen(driver);
        reminderScreen = new ReminderScreen(driver);
        newListScreen  = new NewListScreen(driver);
        newReminderScreen = new NewReminderScreen(driver);
        detailsReminderScreen = new DetailsReminderScreen(driver);
    }

    public void creationListsOfReminder() {
        onboardingScreen.completedOnboarding();

        createdReminderList.forEach(listData -> {
            reminderScreen.navigateToNewList();
            newListScreen.creationList(listData);
        });
    }

    public void validationListOfReminders() {
        actualList = reminderScreen.getActualListOfReminder();

        Assert.assertTrue("List of Reminder is not correct!", Utils.compareLists(createdReminderList, actualList));
    }

    public void deleteAllLists() {
        driver.resetApp();
        if (createdReminderList.size() !=0 ) {
            createdReminderList.forEach(listData -> {
                reminderScreen.deleteList(listData.getName());
            });
        }
    }
    public void deleteListData(ListData listData) {
        reminderScreen.deleteList(listData.getName());
    }

    @AfterAll
    public static void cleanUp() {
        Driver.closeDriver(driver);
    }
}
