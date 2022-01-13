package com.inventory.selenium.products;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@ExtendWith(SeleniumExtension.class)
public class UpdateProductTest {

    private final ChromeDriver driver;

    public UpdateProductTest(ChromeDriver driver) {
        this.driver = driver;

        DesiredCapabilities decap = new DesiredCapabilities();
        decap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);

        System.setProperty("sel.jup.screenshot.at.the.end.of.tests", "whenfailure");
        System.setProperty("sel.jup.screenshot.format", "png");
        System.setProperty("sel.jup.output.folder", "./src/test/onFailureScreenshots");
    }

    @BeforeEach
    public void setupPage(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.get("http://localhost:8081/");
            driver.manage().window().maximize();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"productPage\"]/button")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void update_product_test() throws InterruptedException {

        Thread.sleep(5000);
        List<WebElement> options = driver.findElements(By.id("dropdownMenuButton1"));
        WebElement target = options.get(2);
        target.click();

        Thread.sleep(3000);
        WebElement optionsList = driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                "/tr[2" +
                "]/td[8]/div/ul");

        List<WebElement> listItems = optionsList.findElements(By.tagName("li"));

        Thread.sleep(3000);

        driver.close();
    }
}
