package OnlineTradePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LayoutPage
{
     private WebDriver driver;

     private SelenideElement loginBtn = $(byXpath("//a[@href=\"/member/login.html\"]"));

     private SelenideElement headerLogo = $(byXpath("//div[@class=\"header__logo\"]"));

     private SelenideElement catalogBtn = $(byXpath("//a[@href=\"/catalogue/\"]"));

     private SelenideElement basketBtn = $(byXpath("//div[contains(@class,\"basketCover\")]"));

     private SelenideElement itemCountInBasket = $(byXpath("//span[@class=\"itemCount\"]"));

     public LayoutPage(WebDriver driver)
     {
          this.driver = driver;
     }

     public LayoutPage() { }

     public void catalogBtnClick()
     {
          this.catalogBtn.click();
     }

     public int getItemCountInBasket()
     {
          return Integer.parseInt(this.itemCountInBasket.getText());
     }

     public BasketPage basketBtnClick()
     {
          this.basketBtn.click();
          return new BasketPage(this.driver);
     }

     public SignInPage loginBtnClick()
     {
          this.loginBtn.click();
          return new SignInPage(this.driver);
     }

     public void headerLogoClick()
     {
          this.headerLogo.click();
     }

     public CatalogPage categoryClick(String categoryTitle)
     {
          $(byXpath("//a[contains(text(),\""+categoryTitle+"\")]")).click();
          return new CatalogPage(this.driver);
     }

     public boolean isAuthorized()
     {
          return this.isElementPresent(By.xpath("//a[@href=\"/member/\"]"));
     }

     public boolean isElementPresent(By element)
     {
                return $(element).exists();
     }
}
