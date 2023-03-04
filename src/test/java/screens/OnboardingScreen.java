package screens;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class OnboardingScreen extends BaseScreen{
    static IOSDriver driver;
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Allow While Using App']")
    private IOSElement allowWhileUsingAppButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'Continue'`]")
    private IOSElement continueButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert[`label == 'Allow “Reminders” to use your location?'`]")
    private IOSElement allowWindow;

    public OnboardingScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void completedOnboarding() {
        if(isElementPresentWithinTime(allowWindow,5)) {
            waitAndClick(allowWhileUsingAppButton);
            waitAndClick(continueButton);
        }
    }
}
