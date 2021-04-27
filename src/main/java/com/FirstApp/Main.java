package com.FirstApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        var path = "D:\\Selenium\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver obj = new ChromeDriver();
        obj.get("https://vk.com/mes1019");
    }
}
