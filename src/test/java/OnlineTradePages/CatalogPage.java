package OnlineTradePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.text.Style;
import java.util.ArrayList;

public class CatalogPage extends LayoutPage
{
    @FindBy(xpath = "//span[@class=\"firstLetterCap\" and contains(text(),\"в наличии\")]")
    private WebElement inStockFilter;

    @FindBy(xpath = "//a[@title=\"Подобрать\"]")
    private WebElement filterBtn;

    @FindBy(xpath = "//div[@class=\"paginator__count\"]")
    private WebElement paginatorCount;

    @FindBy(xpath = "//a[contains(@class,\"js__paginator__linkNext\")]")
    private WebElement nextPageBtn;

    @FindBy(xpath = "//a[@title=\"Закрыть окно\"]")
    private WebElement closeModalWindowBtn;

    private int totalProductOnOnePage;

    public CatalogPage(WebDriver driver)
    {
        super(driver);
    }

    public void CloseModalWindowBtnClick()
    {
        this.closeModalWindowBtn.click();
    }
    public int GetTotalProductOnOnePage()
    {
        //возвращает количество товаров на одной странице
        return this.totalProductOnOnePage=Integer.parseInt(this.paginatorCount.getText()
                                                                              .split(" ")[1]
                                                                              .split("–")[1]);
    }

    public void ProductTypeClick(String productType)
    {
        super.GetDriver().findElement(By.xpath("//img[@alt=\""+productType+"\"]")).click();
    }

    public void NextPageBtnClick()
    {
        this.nextPageBtn.click();
    }

    public void InStockFilterClick()
    {
        this.inStockFilter.click();
    }

    public double GetMaxNumberPage()
    {
         double totalAmountProduct = Integer.parseInt(this.paginatorCount.getText().split(" ")[this.paginatorCount.getText().split(" ").length-1]);
         double totalProductOnOnePage = this.GetTotalProductOnOnePage();
         return Math.ceil(totalAmountProduct/totalProductOnOnePage);
    }

    public void FilterBtnClick()
    {
        this.filterBtn.click();
    }

}
