package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestBase
{
    private WebDriver driver;

    public WebDriver  GetDriver(){return driver;}

    public TestBase() {}
    public TestBase(String defaultUrl)
    {
        start(defaultUrl);
    }

    public void takeAScreenshot(String screenshotName)
    {
        try
        {
            String currentTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            TakesScreenshot scrShot = ((TakesScreenshot)driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(ConfigProperties.GetProperty("pathToScreenshotsDirectory")+"/"+screenshotName+currentTime+".png");
            FileUtils.copyFile(SrcFile, DestFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void start(String defaultUrl)
    {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.GetProperty("pathToChromeDriver"));
        WebDriverRunner.setWebDriver(new ChromeDriver());
        Configuration.browser = "chrome";
        Configuration.startMaximized = false;
        Configuration.timeout = 6000;
        Configuration.reportsFolder = ConfigProperties.GetProperty("pathToScreenshotsDirectory");
        Configuration.screenshots=true;
        Selenide.open(defaultUrl);
    }

    public void finish() { Selenide.closeWebDriver();}

}
