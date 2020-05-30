package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","ios");
        capabilities.setCapability("deviceName","iPhone SE (2nd generation)");
        capabilities.setCapability("platformVersion","13.5");
        capabilities.setCapability("app","/Users/ruslan.khaziev/IdeaProjects/JavaAppiumAutomation/apks/Wikipedia.app");

        driver = new IOSDriver(new URL(AppiumUrl), capabilities);

        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }
}
