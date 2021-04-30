package pages;

import core.LayoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class FilteredPage extends LayoutPage
{
    //Локатор для считывания поля "Начальная цена"
    @FindBy(xpath = "//div[@itemprop=\"price\"]")
    private List<WebElement> startPrices;

    //Локатор для считывания поля "Кол-во закупок"
    @FindBy(xpath = "//table[@class=\"card-table\"]//td[3]")
    private List<WebElement> purchaseNumbers;

    //Локатор - кнопка для переключение на следующую страницу
    @FindBy(xpath = "//a[@class=\"page-link next\"]")
    private WebElement nextBtnPage;

    @FindBy(xpath = "//button[@class=\"consultation-btn\"]")
    private WebElement consultationBtn;

    @FindBy(xpath = "//button[contains(text(),\"Спасибо, пока не нужно\")]")
    private WebElement closeConsulatationModalWindowBtn;

    public void ConsultationBtnClick() {consultationBtn.click();}

    public void closeConsulatationModalWindowBtnClick() {closeConsulatationModalWindowBtn.click();}

    public FilteredPage(WebDriver driver)
    {
        super(driver);
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    //Проверка наличия элемента в DOM
    public boolean NextBtnPageIsPresent()
    {
        if (super.IsElementPresent(By.xpath("//a[@class=\"page-link next\"]")))
        {
            System.out.println("Переход на след. страницу доступен");
            return true;
        }
        else
        {
            System.out.println("Переход на след. страницу недоступен");
            return false;
        }
    }

    public void NextBtnPageClick()
    {
        nextBtnPage.click();
    }

    public ArrayList<String> GetStartPricesList()
    {
        ArrayList<String> startPricesContent = new ArrayList<String>();
        for (int i=0;i<this.startPrices.size();i++)
        {
            startPricesContent.add(this.startPrices.get(i).getAttribute("content"));
        }
        return startPricesContent;
    }
    //Метод возвращающий Xpath запрос для элемента "Количество закупок"
    private String GetActivePurchaseNumberXPath(int cardItemIndex,int trIndex)
    {
        return "//div[@class=\"cards\"]["+String.valueOf(cardItemIndex)+"]//div[@class=\"card-item\"]//tr["+String.valueOf(trIndex)+"]/td[3]";
    }

    public ArrayList<ArrayList<String>> GetPurchaseNumbersList()
    {
        int cardItemSize = super.GetDriver().findElements(By.xpath("//div[@class=\"cards\"]//div[@class=\"card-item\"]")).size();
        ArrayList<ArrayList<String>> purchaseNumbersContentResult = new ArrayList<ArrayList<String>>();
        int cardItemIndex=1; //Минимально возможный индекс элемента закупки в запросе Xpath
        int trIndex=2;//Минимально возможный индекс поля "количество закупок" в запросе Xpath
        ArrayList<String> middlewareList = new ArrayList<String>();
        int purchaseNumberElementSize;
        WebElement activePurchaseNumberElement;
        for (int i=0;i<cardItemSize;i++,cardItemIndex++) {
            purchaseNumberElementSize = super.GetDriver()
                    .findElements(By.xpath("//div[@class=\"cards\"][" + cardItemIndex + "]//div[@class=\"card-item\"]//tr/td[3]"))
                    .size();
            trIndex = 2;
            middlewareList = new ArrayList<String >(); //через .clear() не работает
            for (int j = 0; j < purchaseNumberElementSize; j++, trIndex++) {
                activePurchaseNumberElement = super.GetDriver()
                        .findElement(By.xpath(GetActivePurchaseNumberXPath(cardItemIndex, trIndex)));
                 middlewareList.add(activePurchaseNumberElement.getText());
            }
            purchaseNumbersContentResult.add(middlewareList);
        }
        return  purchaseNumbersContentResult;
    }


}