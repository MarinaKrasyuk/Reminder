package screens;

import data.ListData;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.util.List;

public class NewListScreen extends BaseScreen{
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == 'List Name'`]")
    private IOSElement listInput;
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Done'")
    private IOSElement doneButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == 'Colors'`]")
    private IOSElement colourSection;

    public NewListScreen(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void creationList(ListData listData) {
        clearAndEnterTextToInput(listInput, listData.getName());
        List<MobileElement> listOfColour = colourSection.findElements(MobileBy.iOSClassChain("XCUIElementTypeOther"));
        listOfColour.forEach(colourElement -> {
            if (colourElement.getAttribute("value").equals(listData.getColor()))
            {
                waitAndClick(colourElement);
            }
        });
        waitAndClick(doneButton);
    }
}
