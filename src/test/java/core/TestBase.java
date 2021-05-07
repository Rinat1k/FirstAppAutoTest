package core;

import OnlineTradePages.LayoutPage;
import config.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class TestBase
{
    private WebDriver driver;
    private LayoutPage layoutPage;

    public WebDriver GetDriver(){return driver;}

    public void TestBase()
    {
        Start();
    }

    public void Start()
    {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.GetProperty("pathToChromeDriver"));
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        this.driver.get(ConfigProperties.GetProperty("defaultUrlForOnlineTrade"));
        PageFactory.initElements(driver, this);
    }

    public void Finish() { driver.quit();}

}
