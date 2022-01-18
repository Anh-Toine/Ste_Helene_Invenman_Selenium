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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
            driver.get("http://localhost:8081/");
            driver.manage().window().maximize();

            String loginButtonXP = "//*[@id=\"loginButton\"]/a";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginButtonXP)));

            driver.findElementByXPath(loginButtonXP).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id" +
                    "=\"productPage\"]/button")));

            WebElement suppliersLink = driver.findElement(By.linkText("Suppliers"));
            suppliersLink.click();

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

        Thread.sleep(3000);
        WebElement supplierName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[2]/input");
        supplierName.sendKeys("Adenna's");

        WebElement repName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[3]/input");
        repName.sendKeys("Michael H. Pieto");

        WebElement email = driver.findElementByXPath("//*[@id=\"addForm\"]/div[4]/input");
        email.sendKeys("michael271@sennomail.com");

        WebElement phoneNumber = driver.findElementByXPath("//*[@id=\"addForm\"]/div[5]/input");
        phoneNumber.sendKeys("777-777-7777");

        Thread.sleep(3000);

        WebElement confirm = driver.findElementByXPath("//*[@id=\"addForm\"]/div[6]/div/div/div[1]/button");
        confirm.click();

        Thread.sleep(3000);
        for(int i=1 ; i<5 ; i++){
            switch(i){
                case 1:
                    assertThat(driver.findElementByXPath("//*[@id=\"supplierPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("Adenna's"));
                    break;
                case 2:
                    assertThat(driver.findElementByXPath("//*[@id=\"supplierPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("Michael H. Pieto"));
                    break;
                case 3:
                    assertThat(driver.findElementByXPath("//*[@id=\"supplierPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("michael271@sennomail.com"));
                    break;
                case 4:
                    assertThat(driver.findElementByXPath("//*[@id=\"supplierPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("777-777-7777"));
                    break;
            }
        }
        driver.quit();
    }

    @Test
    public void update_supplier_test() throws InterruptedException {
        Thread.sleep(5000);

        List<WebElement> options = driver.findElements(By.id("dropdownMenuButton1"));
        WebElement target = options.get(0);
        target.click();

        Thread.sleep(3000);

        WebElement optionsList = driver.findElementByXPath("//*[@id=\"supplierPage\"]/table/tbody" +
                "/tr[1]/td[5]/div/ul");
        List<WebElement> listItems = optionsList.findElements(By.tagName("li"));

        WebElement editCell = listItems.get(0);
        editCell.findElement(By.linkText("Edit")).click();

        Thread.sleep(3000);
        WebElement representativeName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[3" +
                "]/input");
        representativeName.clear();
        representativeName.sendKeys("Jonathan B. Thibodeaux");

        Thread.sleep(3000);
        WebElement confirmButton = driver.findElementByXPath("//*[@id=\"addForm\"]/div[6]/div/div" +
               "/div[1]/button");
        confirmButton.click();
        Thread.sleep(3000);

        WebElement newRepresentative = driver.findElementByXPath("//*[@id=\"supplierPage\"]/table" +
                "/tbody/tr[1]/td[2]");
        assertEquals("Jonathan B. Thibodeaux",newRepresentative.getText());
        driver.quit();
    }
}
