package OnlineTradePages.steps;

import OnlineTradePages.LayoutPage;
import OnlineTradePages.SignInPage;
import config.ConfigProperties;
import core.TestBase;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.То;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginStepsDefinition extends TestBase
{
    private SignInPage signInPage;

    private LayoutPage layoutPage;

    @Before
    public void Init()
    {
        System.out.println("Начало теста...");
        this.Start();
        layoutPage = new LayoutPage(super.GetDriver());
        signInPage = new SignInPage(super.GetDriver());
    }

    @Дано("^зарегистрированный покупатель переходит на страницу авторизации$")
    public void RegisteredCustomerGoesToLoginPage()
    {
        layoutPage.LoginBtnClick();
    }

    @Если("^зарегистрированный покупатель входит в систему с корректными реквизитами$")
    public void RegisteredCustomerEntersCredentials()
    {
        signInPage.SetEmailInput(ConfigProperties.GetProperty("signInEmail"));
        signInPage.SetPasswordInput(ConfigProperties.GetProperty("signInPassword"));
        signInPage.AutoLoginCheckBoxClick();
        signInPage.SignInBtnClick();
    }

    @То("^покупатель авторизован$")
    public void CustomerAuthorized()
    {
        Assert.assertTrue(signInPage.IsElementPresent(By.xpath("//a[@href=\"/member/\"]")));
    }
}
