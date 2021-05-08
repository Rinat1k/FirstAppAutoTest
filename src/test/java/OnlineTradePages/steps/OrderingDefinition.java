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

    @И("^выбирает желаемую категорию$")
    public void ChooseDesiredCategory()
    {
        layoutPage.CategoryClick("Сад и огород");
    }

    @И("^желаемый тип товара$")
    public void ChooseDesiredProductType()
    {
        catalogPage.ProductTypeClick("Уличное освещение для дома и сада");
        catalogPage.ProductTypeClick("Дюралайт");
        catalogPage.ProductTypeClick("Шнуры Дюралайт");
    }

    @Затем("^покупатель осуществляет фильтрацию товаров$")
    public void CustomerFiltersProduct()
    {
        //переписать эти методы
        catalogPage.InStockFilterClick();
        catalogPage.FilterBtnClick();
        this.TakeAScreenshot();
    }

    @И("выбирает желаемые товары")
    public void ChooseDesiredProduct()
    {
        //
    }
////div[@class="indexGoods__item"][1]//a[contains(@title,"Купить")]
}
