package test;

import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdvancedSearchPage;
import pages.FilteredPage;
import pages.ParticipantsPage;
import config.ConfigProperties;

import java.util.logging.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class RtsTenderSmokeTest
{
    @Test
    public void MyTask()
    {
        System.setProperty("webdriver.chrome.driver",ConfigProperties.GetProperty("pathToChromeDriver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        var logger = Logger.getLogger("MyLog");
        FileHandler fh;
        try
        {
            fh = new FileHandler(ConfigProperties.GetProperty("pathToLogFile"));
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ParticipantsPage participantsPage = new ParticipantsPage(driver);
        participantsPage.open();

        AdvancedSearchPage advancedSearchPage = participantsPage.AdvancedSearchBtnClick();
        // пронажимаем на расширенный поиск
        advancedSearchPage.SettingsButtonClick();
        // нажимаем на 615-ПП РФ
        advancedSearchPage.Rf615Click();
        // нажимаем на включить совместные закупки
        advancedSearchPage.ExcludeJointPurchases();
        // устанавливаем значения фильтров даты
        advancedSearchPage.DateFilterClick();
        advancedSearchPage.OpenDatePickerFrom();
        advancedSearchPage.SetDateFilter(1,11,2010);//month - [0..11]
        advancedSearchPage.OpenDatePickerTo();
        advancedSearchPage.SetCurrentDate();

        // открываем фильтр "регион поставки"
        advancedSearchPage.RegionFilterClick();
        advancedSearchPage.SetRegionFilter(new String[]{"Алтайский край","Москва","Краснодарский край"});

        //нажимаем на кнопку фильтрации "Найти"
        FilteredPage filteredPage = advancedSearchPage.AdvancedFilterSearchClick();
        //Считываем данные "Начальная цена" и "Кол-во закупок

        while(filteredPage.NextBtnPageIsPresent())
        {

            //Запись в лог
            for (int i=0;i<filteredPage.GetStartPricesList().size();i++)
            {
                logger.info("Начальная цена: " + filteredPage.GetStartPricesList().get(i)
                        + " Кол-во закупок: "+ filteredPage.GetPurchaseNumbersList().get(i));
            }
            filteredPage.NextBtnPageClick();
        }
    }

}