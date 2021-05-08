package OnlineTradePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends LayoutPage
{
    @FindBy(xpath = "//span[@class=\"firstLetterCap\" and contains(text(),\"в наличии\")]")
    private WebElement inStockFilter;

    @FindBy(xpath = "//a[@title=\"Подобрать\"]")
    private WebElement filterBtn;

    public CatalogPage(WebDriver driver)
    {
        super(driver);
    }

    public void ProductTypeClick(String productType)
    {
        super.GetDriver().findElement(By.xpath("//img[@alt=\""+productType+"\"]")).click();
    }

    public void InStockFilterClick()
    {
        this.inStockFilter.click();
    }

    public void FilterBtnClick()
    {
        this.filterBtn.click();
    }

}
