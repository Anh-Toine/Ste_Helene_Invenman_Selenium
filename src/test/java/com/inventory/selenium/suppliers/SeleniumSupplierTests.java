package com.inventory.selenium.suppliers;

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
public class SeleniumSupplierTests {
    private final ChromeDriver driver;

    public SeleniumSupplierTests(ChromeDriver driver) {
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
            driver.get("http://localhost:8081/suppliers");
            driver.manage().window().maximize();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id" +
                    "=\"supplierPage\"]/button")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void add_suppliers_test() throws InterruptedException {
        Thread.sleep(5000);
        WebElement addButton = driver.findElementByXPath("//*[@id=\"supplierPage\"]/button");
        addButton.click();

        Thread.sleep(5000);
        WebElement supplierName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[2]/input");
        supplierName.sendKeys("La Bananane Bleue");

        WebElement repName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[3]/input");
        repName.sendKeys("Joel N. Mansol");

        WebElement email = driver.findElementByXPath("//*[@id=\"addForm\"]/div[4]/input");
        email.sendKeys("joelnellek@yahoo.com");

        WebElement phoneNumber = driver.findElementByXPath("//*[@id=\"addForm\"]/div[5]/input");
        phoneNumber.sendKeys("555-5555-5555");

        Thread.sleep(5000);

        WebElement confirm = driver.findElementByXPath("//*[@id=\"addForm\"]/div[9]/div/div/div[1]/button");
        confirm.click();

        Thread.sleep(5000);
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[5]/td[1]").getText(), is("La Bananane Bleue"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[5]/td[2]").getText(), is("Joel N. Mansol"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[5]/td[3]").getText(), is("joelnellek@yahoo.com"));
        assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[5]/td[4]").getText(), is("555-5555-5555"));

        driver.quit();
    }
}
