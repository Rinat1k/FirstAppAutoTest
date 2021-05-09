package OnlineTradePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends LayoutPage
{
    @FindBy (xpath = "//input[contains(@value,\"Оформить заказ\")]")
    private WebElement orderingBtn;

    @FindBy (xpath = "//input[@title=\"Контактное лицо\"]")
    private WebElement contactInformationInput;

    @FindBy (xpath = "//input[@title=\"Мобильный телефон\"]")
    private WebElement phoneNumberInput;

    @FindBy (xpath = "//textarea[@title=\"Адрес доставки\"]")
    private WebElement deliveryAddress;

    public BasketPage(WebDriver driver)
    {
        super(driver);
    }

    public void SetContactInformation(String fio,String phoneNumber)
    {
        this.contactInformationInput.clear();
        this.contactInformationInput.sendKeys(fio);
        this.phoneNumberInput.sendKeys(phoneNumber);
    }

    public void SetDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress.sendKeys(deliveryAddress);
    }

    public void OrderingBtnClick()
    {
        this.orderingBtn.click();
    }

}
