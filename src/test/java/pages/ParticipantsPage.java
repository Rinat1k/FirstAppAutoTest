package pages;

import config.ConfigProperties;
import core.LayoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class ParticipantsPage extends LayoutPage
{

    @FindBy(xpath = "//a[contains(@href,'/poisk/poisk-223-fz/')]")
    WebElement advancedSearchBtn;

    public ParticipantsPage(WebDriver driver)
    {
        super(driver);
    }

    public void open()
    {
        super.GetDriver().get(ConfigProperties.GetProperty("defaultUrl"));
    }

    public AdvancedSearchPage AdvancedSearchBtnClick()
    {
        advancedSearchBtn.click();
        return new AdvancedSearchPage(super.GetDriver());
    }

}
