package tests;

import data.ListData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.RunnerExtension;

@ExtendWith(RunnerExtension.class)
public class ReverseOrderTest extends BaseTest{
    @AllureId("102")
    @Description("Case-2 Validate ability Create Lists ")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void reverseOrderTest() {
        creationListsOfReminder();
        validationListOfReminders();
        reminderScreen.editOrderOfLists(createdReminderList);
        Collections.sort(createdReminderList, Comparator.comparing(ListData::getName).reversed());
        validationListOfReminders();
    }

    @AfterEach
    public void tearDown() {
        deleteAllLists();
    }
}
