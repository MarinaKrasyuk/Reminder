package driver;

import constant.ConstantEnv;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class Driver {
    private static IOSDriver driver;

    public Driver() {
    }
    public static IOSDriver getDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        if (driver == null) {
            caps.setCapability(PLATFORM_NAME, "iOS");
            caps.setCapability(PLATFORM_VERSION, "16.2");
            caps.setCapability(AUTOMATION_NAME, "XCUITest");
            caps.setCapability(UDID, "96DD9456-962A-4891-825E-CE74331C948F");
            caps.setCapability("bundleId", "com.apple.reminders");
            caps.setCapability("deviceName", "sim_14_pro_ios");
        }
        return new IOSDriver(new URL(ConstantEnv.URL_NAME), caps);
    }

    public static void closeDriver(IOSDriver driver){
        System.out.print("close(): ");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}