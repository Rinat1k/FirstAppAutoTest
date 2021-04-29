package pages;

import core.LayoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FilteredPage extends LayoutPage
{
    @FindBy(xpath = "//div[@itemprop=\"price\"]")
    private List<WebElement> startPrices;

    @FindBy(xpath = "//table[@class=\"card-table\"]//td[3]")
    private List<WebElement> purchaseNumbers;

    @FindBy(xpath = "//a[@class=\"page-link next\"]")
    private WebElement nextBtnPage;

    public FilteredPage(WebDriver driver)
    {
        super(driver);
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public boolean NextBtnPageIsPresent()
    {
        if (super.IsElementPresent(By.xpath("//a[@class=\"page-link next\"]")))
        {
            System.out.println("Можно идти дальше");
            return true;
        }
        else
        {
            System.out.println("Конец.");
            return false;
        }
    }

    public void NextBtnPageClick()
    {
        nextBtnPage.click();
    }

    public ArrayList<String> GetStartPricesList()
    {
        ArrayList<String> startPricesContent = new ArrayList<String>();
        for (int i=0;i<this.startPrices.size();i++)
        {
            startPricesContent.add(this.startPrices.get(i).getAttribute("content"));
        }
        return startPricesContent;
    }

    public ArrayList<String> GetPurchaseNumbersList()
    {
        ArrayList<String> purchaseNumbersContent = new ArrayList<String>();
        for (int i=0;i<this.purchaseNumbers.size();i++)
        {
            purchaseNumbersContent.add(this.purchaseNumbers.get(i).getText());
        }
        return purchaseNumbersContent;
    }


}