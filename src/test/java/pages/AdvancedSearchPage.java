package pages;

import core.LayoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.ConfigProperties;

import java.util.List;

public class AdvancedSearchPage extends LayoutPage
{

    //Локатор - кнопка "Расширенные настройки"
    @FindBy(xpath = "//span[@class = 'main-search__settings-btn']")
    WebElement settingsButtonClick;

    //Локатор - чекбокс "615-ПП РФ"
    @FindBy(xpath = "//label[contains(text(),'615-ПП РФ')]")
    WebElement PpRf615Click;

    //Локатор - чекбокс "Исключить совместные закупки"
    @FindBy(xpath = "//label[contains(text(),'Исключить совместные закупки')]")
    WebElement excludeJointPurchasesCheckbox;

    //Локатор - фильтр даты
    @FindBy(xpath = "//div[@class = 'modal-settings-section']/div/div[@class = 'title-collapse title-collapse--less' and contains(text(),'Фильтры по датам')]")
    WebElement dateFilter;

    //Локатор - кнопка для установки начальной даты подачи заявок
    @FindBy(xpath = "//div[contains(text(),'ПОДАЧА ЗАЯВОК')]/following-sibling::div[1]/div[@class='form-group__cell'][1]")
    WebElement dateFromPickerInput;

    //Локатор - кнопка для установки даты окончания подачи заявок
    @FindBy(xpath = "//div[contains(text(),'ПОДАЧА ЗАЯВОК')]/following-sibling::div[1]/div[@class = 'form-group__cell'][2]")
    WebElement dateToPickerInput;

    //Локатор для установления даты
    @FindBy(css = ".react-datepicker__day--today")
    WebElement openDatePicker;

    //Локатор для выбора региона поставки
    @FindBy(xpath ="//div[@class='modal-settings-section']/div/div[contains(text(),'Регион поставки')]")
    WebElement regionFilter;

    //Локатор - чекбокс "Показать все регионы"
    @FindBy(xpath = "//a[contains(text(),\"Показать еще 77\")]")
    WebElement showAllRegions;

    //нормальный ли это локатор... Локатор - чекбокс "Снять всё"
    @FindBy(xpath = "//div[contains(text(),\"Регион поставки\")]/parent::div//following-sibling::div[contains(@class,\"filter-helpers\")]//a[contains(text(),\"Снять всё\")]")
    WebElement removeAllRegions;

    //Локатор - кнопка "Найти" в расширенном поиске
    @FindBy(xpath = "//button[@class=\"search__btn bottomFilterSearch\"]")
    WebElement advancedFilterSearchBtn;

    //Локатор для выборки всех существующих регионов поставки
    @FindBy(xpath = "//ul[@class=\"regions-list\"]/li/child::label")
    List<WebElement> regionsList;

    public AdvancedSearchPage(WebDriver driver)
    {
        super(driver);
    }

    public void open()
    {
        super.GetDriver().get(ConfigProperties.GetProperty("defaultUrl"));
    }

    public void DateFilterClick()
    {
        dateFilter.click();
    }

    public void ExcludeJointPurchases()
    {
        excludeJointPurchasesCheckbox.click();
    }

    public void Rf615Click()
    {
        PpRf615Click.click();
    }

    public void SettingsButtonClick()
    {
        settingsButtonClick.click();
    }

    public void SetCurrentDate() { openDatePicker.click(); }

    public void OpenDatePickerFrom()
    {
        dateFromPickerInput.click();
    }

    public void OpenDatePickerTo()
    {
        dateToPickerInput.click();
    }

    public void RegionFilterClick()
    {
        regionFilter.click();
    }

    public void ShowAllRegionsClick() {showAllRegions.click();}

    public void RemoveAllRegionClick() {removeAllRegions.click();}

    public FilteredPage AdvancedFilterSearchClick()
    {
        advancedFilterSearchBtn.click();
        return new FilteredPage(super.GetDriver());
    }

    public void SetRegionFilter(String[] regions)
    {
        this.RemoveAllRegionClick();
        this.ShowAllRegionsClick();
        for(int i=0;i<this.regionsList.size();i++)
        {
            for (int j=0;j<regions.length;j++)
            {
                if (this.regionsList.get(i).getText().equals(regions[j]))
                {
                    //System.out.println(this.regionsList.get(i).getText()+" - "+regions[j]);
                    this.regionsList.get(i).click();
                }
            }
        }
    }

}
