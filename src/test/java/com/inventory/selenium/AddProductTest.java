package com.inventory.selenium;

import io.github.bonigarcia.seljup.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class AddProductTest {

    ChromeDriver driver;

    public AddProductTest(ChromeDriver driver) {
        this.driver = driver;
    }

    void sleep(int time){
        try {
            Thread.sleep(time* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setupPage(){
        driver.get("http://localhost:8081/");
        sleep(3);
        driver.manage().window().maximize();
        sleep(3);
    }
    @Test
    void add_product_test(){
        setupPage();

        WebElement addButton = driver.findElementByXPath("//*[@id=\"productPage\"]/button");
        sleep(3);
        addButton.click();
        sleep(3);

        WebElement barCode = driver.findElementByXPath("//*[@id=\"addForm\"]/div[2]/input");
        barCode.sendKeys("116723");

        WebElement productName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[3]/input");
        productName.sendKeys("CC Lemon");

        WebElement brand = driver.findElementByXPath("//*[@id=\"addForm\"]/div[4]/input");
        brand.sendKeys("Suntory");

        WebElement price = driver.findElementByXPath("//*[@id=\"addForm\"]/div[5]/input");
        price.sendKeys("6.78");

        WebElement quantity = driver.findElementByXPath("//*[@id=\"addForm\"]/div[6]/input");
        quantity.sendKeys("14");

        driver.quit();
    }

    @Test
    void add_supplier_test(){

    }
}
