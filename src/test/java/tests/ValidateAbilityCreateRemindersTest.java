package tests;

import data.ListData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.RunnerExtension;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(RunnerExtension.class)
public class ValidateAbilityCreateRemindersTest extends BaseTest{
    ListData listData;
    List<String> reminderList;
    List<String> aclualListOfReminders;

    @BeforeEach
    public void setUp() {
        listData = new ListData("List1", "Green");
        reminderList = new ArrayList<>();
        reminderList.add("Reminder1");
        reminderList.add("Reminder2");
        aclualListOfReminders = new ArrayList<>();
    }

    @AllureId("103")
    @Description("Case-3 Validate ability Create Reminders ")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void validateAbilityCreateRemindersTest() {
        onboardingScreen.completedOnboarding();

        reminderScreen.navigateToNewList();
        newListScreen.creationList(listData);
        reminderScreen.tapOnList(listData.getName());
        creationReminder();

        Assert.assertTrue("Reminders are not correct!", validationOfReminders());

        checkAllReminders();

        Assert.assertFalse("Reminders is not disappiered", validationOfReminders());

    }

    public void creationReminder() {
        reminderList.forEach(reminder -> {
            reminderScreen.navigateToNewReminderScreen();
            newReminderScreen.creationReminder(reminder);
        });
    }

    public void checkAllReminders() {
        reminderList.forEach(reminder -> {
            reminderScreen.checkReminders(reminder);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public boolean validationOfReminders() {
        aclualListOfReminders = reminderScreen.getActualReminder();
        return Utils.compareReminders(reminderList, aclualListOfReminders);
    }

    @AfterEach
    public void tearDown() {
        driver.resetApp();
        deleteListData(listData);
    }
}
