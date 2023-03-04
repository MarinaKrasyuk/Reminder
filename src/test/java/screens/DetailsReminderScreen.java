package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DetailsReminderScreen extends BaseScreen{
    public DetailsReminderScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @iOSXCUITFindBy(accessibility = "Detail View Note Field")
    private IOSElement noteInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`label == 'Date'`]")
    private IOSElement dateRadiobutton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`label == 'Time'`]")
    private IOSElement timeRadiobutton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Time'`]")
    private IOSElement timeField;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'Continue'`]")
    private IOSElement continueButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'Allow'`]")
    private IOSElement allowButton;
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypePicker'")
    private IOSElement timePicker;
    @iOSXCUITFindBy(accessibility = "Done")
    private IOSElement doneButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[`label == 'Priority'`]")
    private IOSElement priorityDropdownlist;
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeCollectionView'")
    private IOSElement dropdownList;

    private final String DATE_PATTERN = "**/XCUIElementTypeButton[`label == '%s'`]";
    private final String PRIORITY_PATTERN = "XCUIElementTypeCell/XCUIElementTypeButton[`label == '%s'`]";

    public void fillDetails(String notes, String date, String time, String priority) {
        enterNotesToDetails(notes);
        fillDateToDetails(date);
        fillTimeToDetails(time);
        setHighestPriority(priority);
        waitAndClick(doneButton);
    }

    private void fillTimeToDetails(String time) {
        waitAndClick(timeRadiobutton);
        enterTextToInput(timePicker, time);
        waitAndClick(timeField);
    }

    private void enterNotesToDetails(String notes) {
        clearAndEnterTextToInput(noteInput, notes);
    }

    private void fillDateToDetails(String date) {
        waitAndClick(dateRadiobutton);
        if(isElementPresentWithinTime(continueButton, 2)) {
            tapElement(continueButton);
        }
        if(isElementPresentWithinTime(allowButton, 2)) {
            tapElement(allowButton);
        }
        waitAndClick(MobileBy.iOSClassChain(String.format(DATE_PATTERN, date)));
    }

    public void setHighestPriority(String priority) {
        waitAndClick(priorityDropdownlist);
        waitAndClick(dropdownList.findElementByIosClassChain(String.format(PRIORITY_PATTERN, priority)));
    }
}
