package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

public class BaseScreen {
    IOSDriver driver;
    WebDriverWait wait;

    BaseScreen(IOSDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10, 5);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void enterTextToInput(WebElement input, String text) {
        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(text);
    }


    protected boolean isElementPresentWithinTimeBy(MobileBy by, long time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean isElementPresentWithinTime(MobileElement element, long time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    private ExpectedCondition<MobileElement> elementIdDisplayed(By by) {
        return driver -> {
            List<MobileElement> listMobileElemets;
            listMobileElemets = driver.findElements(by);
            if (listMobileElemets.size() > 0 && listMobileElemets.get(0).isDisplayed()) {
                return listMobileElemets.get(0);
            } else return null;
        };
    }

    protected MobileElement findElementByWithWait(By by) {
        return wait.until(elementIdDisplayed(by));
    }

    public void longTapping(MobileElement element) {
        Dimension eventElementSize = element.getSize();
        int y = element.getCenter().getY();
        int X = (int) (0.5 * eventElementSize.getWidth());

        new TouchAction(driver)
                .longPress(PointOption.point(X, y))
                .release()
                .perform();
    }

    public void swipeToLeft(MobileBy by) {
        if (isElementPresentWithinTimeBy(by, 15)) {
            MobileElement listItem = (MobileElement) driver.findElement(by);
            Dimension itemElementSize = listItem.getSize();
            int y = listItem.getCenter().getY();
            int endX = (int) (0.3 * itemElementSize.getWidth());
            int startX = (int) (0.9 * itemElementSize.getWidth());

            new TouchAction(driver)
                    .press(PointOption.point(startX, y))
                    .waitAction(waitOptions(ofSeconds(2)))
                    .moveTo(PointOption.point(endX, y))
                    .release()
                    .perform();
        }
    }

    public void swipeElements(MobileBy by1, MobileBy by2) {
        if (isElementPresentWithinTimeBy(by1, 15) && isElementPresentWithinTimeBy(by2, 15)) {

            MobileElement listitem1 = (MobileElement) driver.findElement(by1);

            int startX = listitem1.getCenter().getX();
            int startY = listitem1.getCenter().getY();

            MobileElement listitem2 = (MobileElement) driver.findElement(by2);

            int endX = listitem2.getCenter().getX();
            int endY = listitem2.getCenter().getY();

            new TouchAction<>(driver)
                    .tap(PointOption.point(startX, startY))
                    .waitAction(waitOptions(Duration.ofMillis(3000)))
                    .moveTo(PointOption.point(endX, endY + 10))
                    .perform()
                    .release();
        }
    }

    public void tapElement(MobileElement element) {
        Dimension eventElementSize = element.getSize();
        int y = element.getCenter().getY();
        int x = (int) (0.5 * eventElementSize.getWidth());

        new TouchAction(driver)
                .tap(PointOption.point(x, y))
                .release()
                .perform();
    }
    public void waitAndClick(By by) {
        findElementByWithWait(by).click();
    }

    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected boolean isElementFoundByLocator(By elementLocator) {
        return driver.findElements(elementLocator).size() > 0;
    }

    protected void clearAndEnterTextToInput(MobileElement input, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(text);
    }
}


