package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase  extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabiliesByPlatformEnv();
        this.getDriver(capabilities);
        //driver = new AndroidDriver(new URL(AppiumUrl), capabilities);

        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getCapabiliesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","/Users/ruslan.khaziev/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS))
        {
            capabilities.setCapability("platformName","ios");
            capabilities.setCapability("deviceName","iPhone SE (2nd generation)");
            capabilities.setCapability("platformVersion","13.5");
            capabilities.setCapability("app","/Users/ruslan.khaziev/IdeaProjects/JavaAppiumAutomation/apks/Wikipedia.app");
        } else
        {
            throw new Exception("Cannot get run platform from env variable. Platform value: " + platform);
        }

        return capabilities;

    }

    private void getDriver(DesiredCapabilities capabilities) throws Exception
    {
        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID))
        {
            driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        } else if (platform.equals(PLATFORM_IOS))
        {
            driver = new IOSDriver(new URL(AppiumUrl), capabilities);
        } else
        {
            throw new Exception("Cannot get driver by env variable. Platform value: " + platform);
        }
    }




}
