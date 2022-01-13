package com.inventory.selenium.products;

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
public class AddProductTest {

    private final ChromeDriver driver;

    public AddProductTest(ChromeDriver driver) {
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
    void add_product_test() throws InterruptedException {
        Thread.sleep(5000);
        WebElement addButton = driver.findElementByXPath("//*[@id=\"productPage\"]/button");
        addButton.click();

        Thread.sleep(5000);
        WebElement barCode = driver.findElementByXPath("//*[@id=\"addForm\"]/div[2]/input");
        barCode.sendKeys("116723");

        WebElement productName = driver.findElementByXPath("//*[@id=\"addForm\"]/div[3]/input");
        productName.sendKeys("CC Lemon");

        WebElement brand = driver.findElementByXPath("//*[@id=\"addForm\"]/div[4]/input");
        brand.sendKeys("Suntory");

        WebElement price = driver.findElementByXPath("//*[@id=\"addForm\"]/div[5]/input");
        price.sendKeys("6.78");

        WebElement quantity = driver.findElementByXPath("//*[@id=\"addForm\"]/div[6]/input");
        quantity.sendKeys("12");

        WebElement quantitySold = driver.findElementByXPath("//*[@id=\"addForm\"]/div[7]/input");
        quantitySold.sendKeys("3");

        WebElement categoryId = driver.findElementByXPath("//*[@id=\"addForm\"]/div[8]/input");
        categoryId.sendKeys("1");
        Thread.sleep(5000);

        WebElement confirm = driver.findElementByXPath("//*[@id=\"addForm\"]/div[9]/div/div/div[1]/button");
        confirm.click();

        Thread.sleep(5000);

        for(int i=1 ; i<8 ; i++){
            switch(i){
                case 1:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("116723"));
                    break;
                case 2:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("CC Lemon"));
                    break;
                case 3:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("Suntory"));
                    break;
                case 4:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("6.78"));
                    break;
                case 5:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("12"));
                    break;
                case 6:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("3"));
                    break;
                case 7:
                    assertThat(driver.findElementByXPath("//*[@id=\"productPage\"]/table/tbody" +
                            "/tr[3]/td["+i+"]").getText(), is("1"));
                    break;
            }
        }

        driver.quit();
    }


}
