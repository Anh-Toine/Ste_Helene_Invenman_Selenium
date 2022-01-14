package com.inventory.selenium.categories;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SeleniumExtension.class)
public class SeleniumCategoryTests {

    private final ChromeDriver driver;

    public SeleniumCategoryTests(ChromeDriver driver) {
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
            driver.get("http://localhost:8081/categories");
            driver.manage().window().maximize();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"productPage\"]/button")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void add_category_test() throws InterruptedException {
        Thread.sleep(500);
        WebElement addButton = driver.findElementByXPath("//*[@id=\"categoryPage\"]/button");
        addButton.click();

        Thread.sleep(500);
        WebElement categoryName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[2]/input");
        categoryName.sendKeys("alcohol");

        Thread.sleep(500);
        WebElement taxable = driver.findElementById("taxTrue");
        taxable.click();

        Thread.sleep(500);
        // located element with contains()
        WebElement tax = driver.findElementByXPath("//*[@id=\"addForm\"]/div[4]/input");
        tax.sendKeys("0.14975");

        Thread.sleep(500);

        WebElement confirm = driver.findElement(By.xpath("//*[text()='Confirm']"));
        confirm.click();

        Thread.sleep(500);

        assertThat(driver.findElementByXPath("//*[@id=\"categoryPage\"]/table/tbody/tr[3]/td[1]").getText(), is("alcohol"));
        assertThat(driver.findElementByXPath("//*[@id=\"categoryPage\"]/table/tbody/tr[3]/td[2]").getText(), is("true"));
        assertThat(driver.findElementByXPath("//*[@id=\"categoryPage\"]/table/tbody/tr[3]/td[3]").getText(), is("0.14975"));


        driver.quit();
    }
}
