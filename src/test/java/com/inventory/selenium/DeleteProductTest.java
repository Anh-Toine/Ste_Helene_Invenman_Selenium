package com.inventory.selenium;

import io.github.bonigarcia.seljup.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class DeleteProductTest {

    ChromeDriver driver;

    public DeleteProductTest(ChromeDriver driver) {
        this.driver = driver;
    }

//    @BeforeAll
//    void setup(){
//        EdgeOptions options = new EdgeOptions();
//        options.
//    }
    void sleep(int time){
        try {
            Thread.sleep(time* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void delete_product_test(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.get("file:///Users/lukas/Desktop/index.html");
        sleep(3);
        driver.manage().window().maximize();
        sleep(3);
        WebElement barCode = driver.findElementByXPath("//input[@placeholder='Bar Code']");
        barCode.sendKeys("14");

        WebElement productName = driver.findElementByXPath("//input[@placeholder='Product Name']");
        productName.sendKeys("14");

        WebElement brand = driver.findElementByXPath("//input[@placeholder='Brand']");
        brand.sendKeys("14");

        WebElement price = driver.findElementByXPath("//input[@placeholder='Price']");
        price.sendKeys("14");

        WebElement quantity = driver.findElementByXPath("//input[@placeholder='Quantity']");
        quantity.sendKeys("14");

        WebElement quantitySold = driver.findElementByXPath("//input[@placeholder='Quantity " +
                "Sold']");
        quantitySold.sendKeys("14");

        WebElement categoryId = driver.findElementByXPath("//input[@placeholder='Category Id']");
        categoryId.sendKeys("14");

        sleep(4);
        WebElement add = driver.findElement(By.id("addBtn"));
        add.click();

        driver.navigate().refresh();
        sleep(5);

        WebElement deleteButton = driver.findElement(By.id("deleteBtn"));
        deleteButton.click();

        driver.navigate().refresh();
        sleep(5);

        assertTrue(driver.findElements(By.xpath("//*[@id=\"productPage\"]/table" +
                "/tbody/tr/td[1]")).size() < 1);

        driver.quit();
    }

}
