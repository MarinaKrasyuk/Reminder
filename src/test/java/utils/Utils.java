package utils;

import data.ListData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import java.util.List;

public class Utils {

    public static boolean compareLists(List<ListData> list1, List<ListData> list2) {
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            ListData listData1 = list1.get(i);
            ListData listData2 = list2.get(i);
            if (!listData1.equals(listData2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareReminders(List<String> list1, List<String> list2) {
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            String reminderName1 = list1.get(i);
            String reminderName2 = list2.get(i);
            if (!reminderName1.equals(reminderName2)) {
                return false;
            }
        }
        return true;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG () {
        return ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BYTES);
    }

}
