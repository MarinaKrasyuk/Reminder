package screens;

import data.ListData;
import enums.Priority;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;

import java.util.*;

public class ReminderScreen extends BaseScreen{
    IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "Add List")
    private IOSElement addListButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTable")
    private IOSElement tableList;
    @iOSXCUITFindBy(accessibility = "Show Details")
    private IOSElement detailsButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'Delete'`]")
    private IOSElement deleteButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'Edit'`]")
    private IOSElement editButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'New Reminder'`]")
    private IOSElement newReminderButton;
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Title'")
    private IOSElement titleField;

    private final By TYPE_CELL_FOR_LIST = MobileBy.iOSClassChain("**/XCUIElementTypeCell[`label contains 'List'`]");
    private final String TYPE_CELL_FOR_REMINDER = "XCUIElementTypeCell";
    private final String TYPE_OTHER_FOR_REMINDER = "XCUIElementTypeOther/XCUIElementTypeTextField";
    private final String LIST_ITEM_PATTERN = "**/XCUIElementTypeCell[`label contains '%s'`]";
    private final String REMINDER_PATTERN = "value == '%s'";
    private final String REMINDER_CHECKBOX_PATTERN = "**/XCUIElementTypeCell[$value == '%s'$]/XCUIElementTypeOther/XCUIElementTypeButton";
    private final String NOTE_REMINDER_PATTERN = "value == '%s'";
    private final String TIME_REMINDER_PATTERN = "label contains '%s'";
    private final String PRIORITY_REMINDER_PATTERN = "label == '%s '";


    public ReminderScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void navigateToNewList() {
        waitAndClick(addListButton);
    }

    public void editOrderOfLists(List<ListData> listOfReminders) {
        waitAndClick(editButton);
        MobileBy by1 = (MobileBy) MobileBy.iOSClassChain(String.format(LIST_ITEM_PATTERN, listOfReminders.get(0).getName()));
        MobileBy by2 = (MobileBy) MobileBy.iOSClassChain(String.format(LIST_ITEM_PATTERN, listOfReminders.get(1).getName()));
        MobileBy by3 = (MobileBy) MobileBy.iOSClassChain(String.format(LIST_ITEM_PATTERN, listOfReminders.get(2).getName()));
        swipeElements(by1, by3);
        swipeElements(by2, by3);
    }

    public void navigateToNewReminderScreen() {
        waitAndClick(newReminderButton);
    }

    public void swipeAndtapDetails(String reminderName) {
        By reminderBy = MobileBy.iOSNsPredicateString(String.format(REMINDER_PATTERN, reminderName));
        if (isElementPresentWithinTimeBy((MobileBy) reminderBy,5)) {
            swipeToLeft((MobileBy) reminderBy);
            waitAndClick(detailsButton);
        }
    }
     public void tapReminder(String reminderName) {
         By reminderBy = MobileBy.iOSNsPredicateString(String.format(REMINDER_PATTERN, reminderName));
         if (isElementPresentWithinTimeBy((MobileBy) reminderBy,5)) {
            tapElement((MobileElement) driver.findElement(reminderBy));
         }
     }
    public List<ListData> getActualListOfReminder(){
        List<MobileElement> mobileElementList = driver.findElements(TYPE_CELL_FOR_LIST);
        List<ListData> actualList = new ArrayList<>();
        mobileElementList.stream().forEach(mobileElement -> {
            String listNameOfElement = mobileElement.getAttribute("label");
            String[] listName = listNameOfElement.split(",");
            actualList.add(new ListData(listName[0], mobileElement.getAttribute("value").substring(2)));
        });
       return actualList;
    }

    public List<String> getActualReminder() {
        List<MobileElement> mobileElementList = tableList.findElements(MobileBy.iOSClassChain(TYPE_CELL_FOR_REMINDER));
        List<String> actualList = new ArrayList<>();
        mobileElementList.forEach(mobileElement -> {
            MobileElement element = mobileElement.findElement(MobileBy.iOSClassChain(TYPE_OTHER_FOR_REMINDER));
            String valueOfElement = element.getAttribute("value");
            actualList.add(valueOfElement);
        });
        return actualList;
    }

    public void tapOnList(String listName) {
        waitAndClick(MobileBy.iOSClassChain(String.format(LIST_ITEM_PATTERN, listName)));
    }

    public void deleteList(String listName) {
        swipeToLeft((MobileBy) MobileBy.iOSClassChain(String.format(LIST_ITEM_PATTERN, listName)));
        waitAndClick(deleteButton);
        if (isElementPresentWithinTime(deleteButton, 5)) {
            waitAndClick(deleteButton);
        }
    }

    public void checkReminders(String reminderName) {
        if (isElementPresentWithinTimeBy((MobileBy) MobileBy.iOSNsPredicateString(String.format(REMINDER_PATTERN, reminderName)),5)) {
            waitAndClick(MobileBy.iOSClassChain(String.format(REMINDER_CHECKBOX_PATTERN, reminderName)));
        }
    }

    public boolean isReminderWithNameDisplayed(String name) {
        return titleField.getText().equals(name);
    }

    public boolean isReminderWithNoteDisplayed(String note) {
        return isElementFoundByLocator(MobileBy.iOSNsPredicateString(String.format(NOTE_REMINDER_PATTERN, note)));
    }

    public boolean isReminderWithTimeDisplayed(String time) {
        return isElementFoundByLocator(MobileBy.iOSNsPredicateString(String.format(TIME_REMINDER_PATTERN, time)));
    }

    public boolean isReminderWithPriorityDisplayed() {
        return isElementFoundByLocator(MobileBy.iOSNsPredicateString(String.format(PRIORITY_REMINDER_PATTERN, Priority.HIGH.getPriority())));
    }
}
