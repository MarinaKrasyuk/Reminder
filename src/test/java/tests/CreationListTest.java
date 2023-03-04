package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.JsonParser;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.RunnerExtension;

@ExtendWith(RunnerExtension.class)
public class CreationListTest extends BaseTest{

    @BeforeEach
    public void setUp() {
        JsonParser.getReminerList();
    }

    @AllureId("101")
    @Description("Case-1 Validate ability Create Lists ")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void creationListTest() {
        creationListsOfReminder();
        validationListOfReminders();
    }

    @AfterEach
    public void tearDown() {
        deleteAllLists();
    }
}
