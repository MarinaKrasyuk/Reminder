package screens;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewReminderScreen extends BaseScreen{
    IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "Title")
    private IOSElement titleInput;
    @iOSXCUITFindBy(accessibility = "Done")
    private IOSElement doneButton;

    public NewReminderScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void creationReminder(String reminderName) {
        enterTextToInput(titleInput, reminderName);
        waitAndClick(doneButton);
    }

}
