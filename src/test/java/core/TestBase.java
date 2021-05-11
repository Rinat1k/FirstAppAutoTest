package core;

import OnlineTradePages.LayoutPage;
import config.ConfigProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase
{
    private WebDriver driver;
    private LayoutPage layoutPage;

    public WebDriver  GetDriver(){return driver;}

    public TestBase()
    {
        Start();
    }

    public void TakeAScreenshot(String screenshotName)
    {
        try
        {
            String curretTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile=new File(ConfigProperties.GetProperty("pathToScreenshotsDirectory")+"/"+screenshotName+curretTime+".png");
            FileUtils.copyFile(SrcFile, DestFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void Start()
    {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.GetProperty("pathToChromeDriver"));
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(ConfigProperties.GetProperty("defaultUrlForOnlineTrade"));
        PageFactory.initElements(driver, this);
    }

    public void Finish() { driver.quit();}

}
