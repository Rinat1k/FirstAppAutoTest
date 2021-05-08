package OnlineTradePages.steps;

import OnlineTradePages.CatalogPage;
import OnlineTradePages.LayoutPage;
import OnlineTradePages.SignInPage;
import config.ConfigProperties;
import core.TestBase;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.Assert;
import org.openqa.selenium.By;

public class OrderingDefinition extends TestBase
{
    private SignInPage signInPage;

    private LayoutPage layoutPage;

    private CatalogPage catalogPage;

    @Before
    public void Init()
    {
        System.out.println("Начало теста...");
        this.Start();
        layoutPage = new LayoutPage(this.GetDriver());
        signInPage = new SignInPage(this.GetDriver());
        catalogPage = new CatalogPage(this.GetDriver());
    }

    @Дано("^покупатель переходит на главную страницу$")
    public void CustomerGoesToMainPage()
    {
        layoutPage.HeaderLogoClick();
    }

    @Если("^покупатель открывает модальное окно авторизации$")
    public void RegisteredCustomerOpenAuthModalWindow()
    {
        layoutPage.LoginBtnClick();
    }

    @И("^вводит корректные реквизиты для входа$")
    public void RegisteredCustomerEntersCorrectCredentials()
    {
        signInPage.SetEmailInput(ConfigProperties.GetProperty("signInEmail"));
        signInPage.SetPasswordInput(ConfigProperties.GetProperty("signInPassword"));
        signInPage.AutoLoginCheckBoxClick();
        signInPage.SignInBtnClick();
    }

    @То("^покупатель успешно авторизуется$")
    public void CustomerAuthorized()
    {
        Assert.assertTrue(signInPage.IsElementPresent(By.xpath("//a[@href=\"/member/\"]")));
    }

    @Затем("^покупатель открывает каталог$")
    public void CustomerOpensCatalog()
    {
        layoutPage.CatalogBtnClick();
    }

    @И("выбирает желаемую категорию: {string}")
    public void ChooseDesiredCategory(String category)
    {
        layoutPage.CategoryClick(category);
    }

    @И("желаемый тип - {string} и подтипы товара - {string} {string}")
    public void ChooseDesiredProductType(String productType, String productSubtype1,String productSubtype2)
    {
        catalogPage.ProductTypeClick(productType);
        catalogPage.ProductTypeClick(productSubtype1);
        catalogPage.ProductTypeClick(productSubtype2);
    }

    @Затем("^покупатель осуществляет фильтрацию товаров$")
    public void CustomerFiltersProduct()
    {
        //переписать эти методы
        catalogPage.InStockFilterClick();
        catalogPage.FilterBtnClick();
    }

    @И("выбирает желаемые товары")
    public void ChooseDesiredProduct()
    {
        catalogPage.GetDriver().findElement(By.xpath("//div[@class=\"indexGoods__item\"][1]//a[text()=\"Купить\"]")).click();
        catalogPage.CloseModalWindowBtnClick();
    }

    @Затем("^покупатель переходит на страницу оформления заказов$")
    public void CustomerGoesToBasket()
    {
        layoutPage.BasketBtnClick();
    }

////div[@class="indexGoods__item"][1]//a[contains(@title,"Купить")]
}
