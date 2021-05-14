package OnlineTradePages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CatalogPage extends LayoutPage
{
    private SelenideElement inStockFilter = $(byXpath("//span[@class=\"firstLetterCap\" and contains(text(),\"в наличии\")]"));

    private SelenideElement filterBtn = $(byXpath("//a[@title=\"Подобрать\"]"));

    private SelenideElement paginatorCount = $(byXpath("//div[@class=\"paginator__count\"]"));

    private SelenideElement nextPageBtn = $(byXpath("//a[contains(@class,\"js__paginator__linkNext\")]"));

    private SelenideElement closeModalWindowBtn = $(byXpath("//a[@title=\"Закрыть окно\"]"));

    private int totalProductOnOnePage;

    public CatalogPage(WebDriver driver)
    {
        super(driver);
    }

    public void closeModalWindowBtnClick() { $(byXpath("//a[@title=\"Закрыть окно\"]")).shouldBe(Condition.visible).click(); }

    public int getTotalProductOnOnePage()
    {
        //возвращает количество товаров на одной странице
        return this.totalProductOnOnePage=Integer.parseInt(this.paginatorCount.getText()
                                                                              .split(" ")[1]
                                                                              .split("–")[1]);
    }

    public void productTypeClick(String productType)
    {
        $(byXpath("//img[@alt=\""+productType+"\"]")).click();
    }

    public void nextPageBtnClick() { this.nextPageBtn.click(); }

    public void inStockFilterClick() { this.inStockFilter.click(); }

    public double getMaxNumberPage()
    {
         double totalAmountProduct = Integer.parseInt(this.paginatorCount.getText().split(" ")[this.paginatorCount.getText().split(" ").length-1]);
         double totalProductOnOnePage = this.getTotalProductOnOnePage();
         return Math.ceil(totalAmountProduct/totalProductOnOnePage);
    }

    public void filterBtnClick() { this.filterBtn.click(); }

    public void addFirstProductToCart()
    {
        $(byXpath("//div[@class=\"indexGoods__item\"][1]//a[text()=\"Купить\"]")).click();
        this.closeModalWindowBtnClick();
    }
}
