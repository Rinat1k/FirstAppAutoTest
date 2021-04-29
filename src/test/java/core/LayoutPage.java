package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LayoutPage
{
    private WebDriver driver;

    public WebDriver GetDriver(){return driver;}

    public LayoutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean IsElementPresent(By element)
    {
        try
        {
            Thread.sleep(2000);
            driver.findElement(element);
            return true;
        }
        catch (NoSuchElementException e)
        {

            return false;
        }
        catch (InterruptedException e)
        {
            return false;
        }
    }
}
