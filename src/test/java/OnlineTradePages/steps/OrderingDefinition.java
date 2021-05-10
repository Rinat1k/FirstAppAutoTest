package OnlineTradePages.steps;

import OnlineTradePages.BasketPage;
import OnlineTradePages.CatalogPage;
import OnlineTradePages.LayoutPage;
import OnlineTradePages.SignInPage;
import config.ConfigProperties;
import core.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.ru.*;
import org.junit.Assert;
import org.openqa.selenium.By;

public class OrderingDefinition extends TestBase
{
    private SignInPage signInPage;

    private LayoutPage layoutPage;

    private CatalogPage catalogPage;

    private BasketPage basketPage;

    @Before
    public void Init()
    {
        System.out.println("Начало теста...");
        this.Start();
        this.layoutPage = new LayoutPage(this.GetDriver());
    }

    @After
    public void TearDown(Scenario scenario) {
        if (scenario.isFailed())
        {
            super.TakeAScreenshot(scenario.getName());
        }
    }

    @Дано("^покупатель переходит на главную страницу$")
    public void CustomerGoesToMainPage()
    {
        this.layoutPage.HeaderLogoClick();
    }

    @Если("^покупатель открывает модальное окно авторизации$")
    public void RegisteredCustomerOpenAuthModalWindow()
    {
        this.signInPage=layoutPage.LoginBtnClick();
    }

    @И("^вводит корректные реквизиты для входа$")
    public void RegisteredCustomerEntersCorrectCredentials()
    {
        this.signInPage.SetEmailInput(ConfigProperties.GetProperty("signInEmail"));
        this.signInPage.SetPasswordInput(ConfigProperties.GetProperty("signInPassword"));
        this.signInPage.AutoLoginCheckBoxClick();
        this.signInPage.SignInBtnClick();
    }

    @То("^покупатель успешно авторизуется$")
    public void CustomerAuthorized()
    {
        Assert.assertTrue(this.signInPage.IsElementPresent(By.xpath("//a[@href=\"/member/\"]")));
    }

    @Затем("^покупатель открывает каталог$")
    public void CustomerOpensCatalog()
    {
        this.layoutPage.CatalogBtnClick();
    }

    @И("выбирает желаемую категорию - {string}")
    public void ChooseDesiredCategory(String category)
    {
        this.catalogPage=this.layoutPage.CategoryClick(category);
    }

    @И("желаемый тип - {string} и подтипы товара - {string} {string}")
    public void ChooseDesiredProductType(String productType, String productSubtype1,String productSubtype2)
    {
        this.catalogPage.ProductTypeClick(productType);
        this.catalogPage.ProductTypeClick(productSubtype1);
        this.catalogPage.ProductTypeClick(productSubtype2);
    }

    @Затем("^покупатель осуществляет фильтрацию товаров по наличию$")
    public void CustomerFiltersProduct()
    {
        //для примера
        this.catalogPage.InStockFilterClick();
        this.catalogPage.FilterBtnClick();
    }

    @И("выбирает желаемые товары")
    public void ChooseDesiredProduct()
    {
        this.catalogPage.GetDriver().findElement(By.xpath("//div[@class=\"indexGoods__item\"][1]//a[text()=\"Купить\"]")).click();
        this.catalogPage.CloseModalWindowBtnClick();
        //инициирование бага
        Assert.assertEquals(2, layoutPage.GetItemCountInBasket());
    }

    @Затем("^покупатель переходит на страницу оформления заказов$")
    public void CustomerGoesToBasket()
    {
        this.basketPage=layoutPage.BasketBtnClick();
        this.basketPage.OrderingBtnClick();
    }

    @И("заполняет данные для оформления заказа")
    public void FillsDataForOrdering()
    {
        this.basketPage.SetContactInformation("Иванов Иван Иванович","88005553535");
        this.basketPage.SetDeliveryAddress("Севастополь, ул.Уличная 23");
    }

}
