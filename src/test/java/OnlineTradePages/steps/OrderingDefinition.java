package OnlineTradePages.steps;

import OnlineTradePages.BasketPage;
import OnlineTradePages.CatalogPage;
import OnlineTradePages.LayoutPage;
import OnlineTradePages.SignInPage;
import config.ConfigProperties;
import core.TestBase;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.Assert;

public class OrderingDefinition extends TestBase
{
    private SignInPage signInPage;

    private LayoutPage layoutPage;

    private CatalogPage catalogPage;

    private BasketPage basketPage;

    @Before
    public void Init()
    {
        this.start(ConfigProperties.GetProperty("defaultUrlForOnlineTrade"));
        this.layoutPage = new LayoutPage(this.GetDriver());
    }

   /*ненужный кусок, так как селенид сам делает скриншоты...
   @After
    public void TearDown(Scenario scenario) {
        if (scenario.isFailed())
        {
            super.takeAScreenshot(scenario.getName());
        }
    }*/

    @Дано("^покупатель переходит на главную страницу$")
    public void CustomerGoesToMainPage()
    {
        this.layoutPage.headerLogoClick();
    }

    @Если("^покупатель открывает модальное окно авторизации$")
    public void RegisteredCustomerOpenAuthModalWindow()
    {
        this.signInPage=layoutPage.loginBtnClick();
    }

    @И("^вводит корректные реквизиты для входа$")
    public void RegisteredCustomerEntersCorrectCredentials()
    {
        this.signInPage.setEmailInput(ConfigProperties.GetProperty("signInEmail"));
        this.signInPage.setPasswordInput(ConfigProperties.GetProperty("signInPassword"));
        this.signInPage.autoLoginCheckBoxClick();
        this.signInPage.signInBtnClick();
    }

    @То("^покупатель успешно авторизуется$")
    public void CustomerAuthorized()
    {
        Assert.assertTrue(layoutPage.isAuthorized());
        //проверка, того что авторизация прошла успешно и веб элемент "Вход в личный кабинет" изменился на "Профиль"
    }

    @Затем("^покупатель открывает каталог$")
    public void CustomerOpensCatalog()
    {
        this.layoutPage.catalogBtnClick();
    }

    @И("выбирает желаемую категорию - {string}")
    public void ChooseDesiredCategory(String category)
    {
        this.catalogPage=this.layoutPage.categoryClick(category);
    }

    @И("желаемый тип - {string} и подтипы товара - {string} {string}")
    public void ChooseDesiredProductType(String productType, String productSubtype1,String productSubtype2)
    {
        this.catalogPage.productTypeClick(productType);
        this.catalogPage.productTypeClick(productSubtype1);
        this.catalogPage.productTypeClick(productSubtype2);
    }

    @Затем("^покупатель осуществляет фильтрацию товаров по наличию$")
    public void CustomerFiltersProduct()
    {
        //для примера
        this.catalogPage.inStockFilterClick();
        this.catalogPage.filterBtnClick();
    }

    @И("выбирает желаемые товары")
    public void ChooseDesiredProduct()
    {
        this.catalogPage.addFirstProductToCart();
        //инициирование бага
        //Assert.assertEquals(2, layoutPage.getItemCountInBasket());
    }

    @Затем("^покупатель переходит на страницу оформления заказов$")
    public void CustomerGoesToBasket()
    {
        this.basketPage=layoutPage.basketBtnClick();
        this.basketPage.orderingBtnClick();
    }

    @И("заполняет данные для оформления заказа")
    public void FillsDataForOrdering()
    {
        this.basketPage.setContactInformation("Иванов Иван Иванович","88005553535");
        this.basketPage.setDeliveryAddress("Севастополь, ул.Уличная 23");
        this.finish();
    }
}
