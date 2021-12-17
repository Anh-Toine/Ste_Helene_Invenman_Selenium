package com.inventory.selenium;

import io.github.bonigarcia.seljup.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class DeleteProductTest {

    ChromeDriver driver;

    public DeleteProductTest(ChromeDriver driver) {
        this.driver = driver;
    }

    void sleep(int time){
        try {
            Thread.sleep(time* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void all_product_tests(){
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

        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[1]").getText(),is(
                "14"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[2]").getText(),is(
                "14"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[3]").getText(),is(
                "14"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[4]").getText(),is(
                "14"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[5]").getText(),is(
                "14"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[6]").getText(),is(
                "14"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[7]").getText(),is(
                "14"));
        WebElement newBarCode = driver.findElementByXPath("//input[@placeholder='New Bar Code']");
        newBarCode.sendKeys("14");
        WebElement newProductName = driver.findElementByXPath("//input[@placeholder='New Product " +
                "Name']");
        newProductName.sendKeys("14");
        WebElement newBrand = driver.findElementByXPath("//input[@placeholder='New Brand']");
        newBrand.sendKeys("14");
        WebElement newPrice = driver.findElementByXPath("//input[@placeholder='New Price']");
        newPrice.sendKeys("14");
        WebElement newQuantity = driver.findElementByXPath("//input[@placeholder='New Quantity']");
        newQuantity.sendKeys("14");
        WebElement newQuantitySold = driver.findElementByXPath("//input[@placeholder='New " +
                "Quantity " +
                "Sold']");
        newQuantitySold.sendKeys("3");
        WebElement newCategoryId = driver.findElementByXPath("//input[@placeholder='New " +
                "Category " +
                "Id']");
        newCategoryId.sendKeys("14");

        sleep(4);
        WebElement update = driver.findElement(By.id("updateBtn"));
        update.click();
        driver.navigate().refresh();

        WebElement nqs = driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody/tr/td[6]");
        assertEquals("3", nqs.getText());

        sleep(4);
        WebElement deleteButton = driver.findElement(By.id("deleteBtn"));
        deleteButton.click();

        driver.navigate().refresh();
        sleep(5);

        assertTrue(driver.findElements(By.xpath("//*[@id=\"productPage\"]/table" +
                "/tbody/tr/td[1]")).size() < 1);

        driver.quit();
    }

}
