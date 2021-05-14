package OnlineTradePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BasketPage extends LayoutPage
{
    private SelenideElement orderingBtn = $(byXpath("//input[contains(@value,\"Оформить заказ\")]"));

    private SelenideElement contactInformationInput = $(byXpath("//input[@title=\"Контактное лицо\"]"));

    private SelenideElement phoneNumberInput=$(byXpath("//input[@title=\"Мобильный телефон\"]"));

    private SelenideElement deliveryAddress=$(byXpath("//textarea[@title=\"Адрес доставки\"]"));

    public BasketPage(WebDriver driver)
    {
        super(driver);
    }

    public void setContactInformation(String fio, String phoneNumber)
    {
        this.contactInformationInput.clear();
        this.contactInformationInput.setValue(fio);
        this.phoneNumberInput.setValue(phoneNumber);
    }

    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress.setValue(deliveryAddress);
    }

    public void orderingBtnClick()
    {
        this.orderingBtn.click();
    }

}
