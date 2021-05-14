package OnlineTradePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage extends LayoutPage
{
    SelenideElement emailInput = $(byXpath("//input[@name=\"login\"]"));

    SelenideElement passwordInput = $(byXpath("//input[@name=\"password\"]"));

    SelenideElement autoLoginCheckBox = $(byXpath("//label[@for=\"autologin\"]"));

    SelenideElement signInBtn = $(byXpath("//input[@value=\"Вход\"]"));

    public SignInPage(WebDriver driver)
    {
        super(driver);
    }

    public void setEmailInput(String loginVal) { this.emailInput.setValue(loginVal);}

    public void setPasswordInput(String passwordVal) { this.passwordInput.setValue(passwordVal); }

    public void autoLoginCheckBoxClick() {this.autoLoginCheckBox.click();}

    public void signInBtnClick() {this.signInBtn.click();}
}
