package RtsTenderPages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LayoutPage extends TestBase
{
    private WebDriver driver;

    public LayoutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public WebDriver GetDriver()
    {
        return driver;
    }

    public LayoutPage() { }

    public boolean IsElementPresent(By element)
    {
        try
        {
            Thread.sleep(1500);
            driver.findElement(element);
            return true;
        }
        catch (NoSuchElementException | InterruptedException e)
        {
            return false;
        }
    }
}
