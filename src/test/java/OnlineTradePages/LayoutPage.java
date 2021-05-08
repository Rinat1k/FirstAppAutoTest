package OnlineTradePages;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LayoutPage
{
     private WebDriver driver;

     @FindBy(xpath = "//a[@href=\"/member/login.html\"]")
     private WebElement loginBtn;

     @FindBy(xpath = "//div[@class=\"header__logo\"]")
     private WebElement headerLogo;

     @FindBy(xpath = "//a[@href=\"/catalogue/\"]")
     private WebElement catalogBtn;

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

     public void CatalogBtnClick()
     {
          this.catalogBtn.click();
     }

     public void LoginBtnClick()
     {
          this.loginBtn.findElement(By.xpath("//a[@href=\"/member/login.html\"]"));
          this.loginBtn.click();
     }

     public void HeaderLogoClick()
     {
          this.headerLogo.click();
     }

     public void CategoryClick(String categoryTitle)
     {
          driver.findElement(By.xpath("//a[contains(text(),\""+categoryTitle+"\")]")).click();
     }

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
