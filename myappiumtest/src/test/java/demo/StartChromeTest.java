package demo;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import static junit.framework.Assert.assertTrue;

public class StartChromeTest
{


    public static void swipeHorizontal(AppiumDriver driver, double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * finalPercentage);
        new TouchAction(driver).press(startPoint, anchor).waitAction(duration).moveTo(endPoint, anchor).release().perform();

        //In documentation they mention moveTo coordinates are relative to initial ones, but thats not happening. When it does we need to use the function below
        //new TouchAction(driver).press(startPoint, anchor).waitAction(duration).moveTo(endPoint-startPoint,0).release().perform();
    }


    @Test
    public void test1() throws MalformedURLException{


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5X_API_23");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");


        // Create object of URL class and specify the appium server address
        URL url= new URL("http://127.0.0.1:4723/wd/hub");

// Create object of  AndroidDriver class and pass the url and capability that we created
        WebDriver driver = new AndroidDriver(url, capabilities);

// Open url
        driver.get("http://www.wikipedia.org");

        // print the title
        System.out.println("Title "+driver.getTitle());
        assertTrue("Title do not match",driver.getTitle() == "Wikipedia");


        driver.findElement(By.className("android.widget.EditText")).sendKeys("furry rabbits");

        driver.findElement(By.id("mw-search-DYM-suggestion")).click();

        driver.findElement(By.name("Results 1 – 20 of 639"));

        driver.findElement(By.name("Pulp Fiction Pulp Fiction")).click();

        driver.getTitle();

        assertTrue("Title doesn't exist",!driver.getTitle());

        driver.findElement(By.name("Directed by"));

    // Open url
        driver.get("http://www.travelex.co.uk");


        driver.manage(WebDriver.Window)

       // Referred this https://discuss.appium.io/t/left-or-right-swipe-not-working-in-appium-for-android-app/14515/4
        // swipeHorizontal(driver,0.5,)



// close the browser
        driver.quit();



    }

}