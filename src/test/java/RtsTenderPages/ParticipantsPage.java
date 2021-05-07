package RtsTenderPages;

import config.ConfigProperties;
import core.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParticipantsPage extends TestBase
{

    //Локатор - кнопка перехода на страницу расширенного поиска
    @FindBy(xpath = "//a[contains(@href,'/poisk/poisk-223-fz/')]")
    WebElement advancedSearchBtn;

    public ParticipantsPage()
    {
        super();
    }

    public void open()
    {
        super.GetDriver().get(ConfigProperties.GetProperty("defaultUrlForRtsTender"));
    }

    public AdvancedSearchPage AdvancedSearchBtnClick()
    {
        advancedSearchBtn.click();
        return new AdvancedSearchPage();
    }

}
