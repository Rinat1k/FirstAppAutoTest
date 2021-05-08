package OnlineTradePages;

import core.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends LayoutPage
{
    @FindBy(xpath = "//input[@name=\"login\"]")
    WebElement emailInput;

    @FindBy(xpath = "//input[@name=\"password\"]")
    WebElement passwordInput;

    @FindBy(xpath = "//label[@for=\"autologin\"]")
    WebElement autoLoginCheckBox;

    @FindBy(xpath = "//input[@value=\"Вход\"]")
    WebElement signInBtn;

    public SignInPage(WebDriver driver)
    {
        super(driver);
    }

    public void SetEmailInput(String loginVal) { this.emailInput.sendKeys(loginVal); }

    public void SetPasswordInput(String passwordVal) { this.passwordInput.sendKeys(passwordVal); }

    public void AutoLoginCheckBoxClick() {this.autoLoginCheckBox.click();}

    public void SignInBtnClick() {this.signInBtn.click();}
}
