package tests;

import data.ListData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.RunnerExtension;
import org.assertj.core.api.SoftAssertions;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@ExtendWith(RunnerExtension.class)
public class ValidateAbilityCreateRemindersWithDetails extends BaseTest{

    ListData listData;
    String reminderName;
    String priority;
    List<String> aclualListOfReminders;
    String notes;
    String time;
    @BeforeEach
    public void setUp() {
        listData = new ListData("List1", "Green");
        reminderName = "Reminder1";
        aclualListOfReminders = new ArrayList<>();
        notes = "Test notes";
        priority = "High";
    }

    @AllureId("104")
    @Description("Case-4 Validate ability Create Reminders with details ")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void validateAbilityCreateRemindersTest() {
        onboardingScreen.completedOnboarding();

        reminderScreen.navigateToNewList();
        newListScreen.creationList(listData);
        reminderScreen.tapOnList(listData.getName());

        reminderScreen.navigateToNewReminderScreen();
        newReminderScreen.creationReminder(reminderName);

        fillDetailsForReminder();
        validationDetailsForReminder();
    }

    public void fillDetailsForReminder() {
        reminderScreen.tapReminder(reminderName);
        reminderScreen.swipeAndtapDetails(reminderName);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH);

        LocalDateTime dateTime = timestamp.toLocalDateTime().plusDays(1);
        String date  = formatter.format(dateTime);

        formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
        dateTime = timestamp.toLocalDateTime().plusHours(10);
        time = formatter.format(dateTime);

        detailsReminderScreen.fillDetails(notes, date, time, priority);
    }

    public void validationDetailsForReminder() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(reminderScreen.isReminderWithNameDisplayed(reminderName)).isTrue();
        softAssertions.assertThat(reminderScreen.isReminderWithNoteDisplayed(notes)).isTrue();
        softAssertions.assertThat(reminderScreen.isReminderWithTimeDisplayed(time)).isTrue();
        softAssertions.assertThat(reminderScreen.isReminderWithPriorityDisplayed()).isTrue();

        softAssertions.assertAll();
    }

    @AfterEach
    public void tearDown() {
        driver.resetApp();
        deleteListData(listData);
    }
}
