package test;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import RtsTenderPages.AdvancedSearchPage;
import RtsTenderPages.FilteredPage;
import RtsTenderPages.ParticipantsPage;
import config.ConfigProperties;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;



public class RtsTenderSmokeTest
{
    static Logger logger = LogManager.getLogger(RtsTenderSmokeTest.class);
    @Test
    public void MyTask()
    {
        System.setProperty("webdriver.chrome.driver",ConfigProperties.GetProperty("pathToChromeDriver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        ParticipantsPage participantsPage = new ParticipantsPage();
        participantsPage.open();
        logger.info("Произошло открытие страницы \"Поставщикам\": " + driver.getCurrentUrl());
        AdvancedSearchPage advancedSearchPage = participantsPage.AdvancedSearchBtnClick();
        logger.info("Произошло нажатие на кнопку \"Расширенный поиск\"");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());
        // нажимаем на расширенный поиск
        advancedSearchPage.SettingsButtonClick();
        logger.info("Произошло нажатие на кнопку \"Настройки поиска\"");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());
        // нажимаем на 615-ПП РФ
        advancedSearchPage.Rf615Click();
        logger.info("Произоошло включения вывода закупок с правилом поведения 615-ПП РФ ");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());
        // нажимаем на включить совместные закупки
        advancedSearchPage.ExcludeJointPurchases();
        logger.info("Произошло включение исключения совместных закупок");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());

        // устанавливаем значения фильтров даты
        advancedSearchPage.DateFilterClick();
        advancedSearchPage.OpenDatePickerFrom();
        advancedSearchPage.SetDateFilter(1,11,2010);//month - [0..11]
        advancedSearchPage.OpenDatePickerTo();
        advancedSearchPage.SetCurrentDate();
        logger.info("Значения фильтров даты были установлены");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());

        // открываем фильтр "регион поставки"
        advancedSearchPage.RegionFilterClick();
        logger.info("Произошло нажатие на элемент \"Регион поставки\"");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());
        advancedSearchPage.SetRegionFilter(new String[]{"Алтайский край","Москва","Краснодарский край",""});
        logger.info("Значения фильтров региона поставки установлены");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());

        //нажимаем на кнопку фильтрации "Найти"
        FilteredPage filteredPage = advancedSearchPage.AdvancedFilterSearchClick();
        logger.info("Произошло нажатие на кнопку \"Найти\"");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());

        //Закрываем всплывающее окно "Поможем в любой ситуации"
        filteredPage.ConsultationBtnClick();
        logger.info("Произошло открытие всплывающего окна \"Поможем в любой ситуации\"");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());

        filteredPage.closeConsulatationModalWindowBtnClick();
        logger.info("Произошло нажатие на кнопку \"Спасибо, пока не нужно\"");
        logger.info("Текущая страница: "+ driver.getCurrentUrl());
        //Считываем данные "Начальная цена" и "Кол-во закупок
        ArrayList<String> startPricesList; ArrayList<ArrayList<String>> purchaseNumberList;
        ArrayList<String> purchasesId;
        logger.info("Вывод значений полей закупок \"Начальная цена\" \"Количество закупок\"");
        int maxNumberPageList= filteredPage.GetMaxNumberPageList();
        for (int k=0;k< maxNumberPageList;k++)
        {
            maxNumberPageList = Math.max(filteredPage.GetMaxNumberPageList(), maxNumberPageList);
            logger.info("Текущая страница: " + driver.getCurrentUrl());
            startPricesList = filteredPage.GetStartPricesList();
            purchaseNumberList = filteredPage.GetPurchaseNumbersList();
            purchasesId = filteredPage.GetPurchacesId();

            //Запись в лог
            for (int i = 0; i < filteredPage.GetStartPricesList().size(); i++) {
                logger.info(purchasesId.get(i));
                logger.info("Начальная цена: " + startPricesList.get(i));
                logger.info("Количество закупок: ");
                for (int j = 0; j < purchaseNumberList.get(i).size(); j++) {
                    logger.info("Закупка " + j + ": " + purchaseNumberList.get(i).get(j));
                }
            }
            try
            {
                if (filteredPage.NextBtnPageIsPresent())
                {
                    filteredPage.NextBtnPageClick();
                    Thread.sleep(2000);
                }
                else
                {
                    break;
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}