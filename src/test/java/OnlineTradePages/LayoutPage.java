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

     @FindBy(xpath = "//div[contains(@class,\"basketCover\")]")
     private WebElement basketBtn;

     @FindBy(xpath = "//span[@class=\"itemCount\"]")
     private WebElement itemCountInBasket;

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

     public int GetItemCountInBasket()
     {
          return Integer.parseInt(this.itemCountInBasket.getText());
     }

     public BasketPage BasketBtnClick()
     {
          this.basketBtn.click();
          return new BasketPage(this.driver);
     }

     public SignInPage LoginBtnClick()
     {
          this.loginBtn.findElement(By.xpath("//a[@href=\"/member/login.html\"]"));
          this.loginBtn.click();
          return new SignInPage(this.driver);
     }

     public void HeaderLogoClick()
     {
          this.headerLogo.click();
     }

     public CatalogPage CategoryClick(String categoryTitle)
     {
          driver.findElement(By.xpath("//a[contains(text(),\""+categoryTitle+"\")]")).click();
          return new CatalogPage(this.driver);
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
