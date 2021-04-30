package test;

import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdvancedSearchPage;
import pages.FilteredPage;
import pages.ParticipantsPage;
import config.ConfigProperties;

import java.util.ArrayList;
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

        var logger = Logger.getLogger("RTS-TENDER-LOG");
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

        // нажимаем на расширенный поиск
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
        advancedSearchPage.SetRegionFilter(new String[]{"Алтайский край","Москва","Краснодарский край",""});

        //нажимаем на кнопку фильтрации "Найти"
        FilteredPage filteredPage = advancedSearchPage.AdvancedFilterSearchClick();

        //Закрываем всплывающее окно "Поможем в любой ситуации"
        filteredPage.ConsultationBtnClick();
        filteredPage.closeConsulatationModalWindowBtnClick();

        //Считываем данные "Начальная цена" и "Кол-во закупок
        ArrayList<String> startPricesList; ArrayList<ArrayList<String>> purchaseNumberList;
         while(filteredPage.NextBtnPageIsPresent())
         {
            startPricesList = filteredPage.GetStartPricesList();
            purchaseNumberList = filteredPage.GetPurchaseNumbersList();

            //Запись в лог
            for (int i=0;i<filteredPage.GetStartPricesList().size();i++)
            {
                logger.info("Закупка "+ i);
                logger.info("Начальная цена: " + startPricesList.get(i));
                logger.info("Количество закупок: ");
                for (int j=0;j<purchaseNumberList.get(i).size();j++)
                {
                    logger.info("Закупка "+j+": "+purchaseNumberList.get(i).get(j));
                }
            }
            filteredPage.NextBtnPageClick();
        }
    }
}