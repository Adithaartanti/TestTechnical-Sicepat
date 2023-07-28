import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestSiCepat {
    public static void main(String[] args) {
        String strFileNameDriver = "chromedriver114.exe";
        WebDriver webdriver = null;

        /*
        form login
         */
        String strUsername = "standard_user";
        String strPassword = "secret_sauce";

        String[] strProdukName = {"Sauce Labs Backpack"};

        /*
        form checkout
         */
        String strFirstName = "Aditha";
        String strLastName = "Artanti";
        String strZipCode = "1234567";


        webdriver = startChromeDriver(strFileNameDriver, webdriver);

        webdriver.get("https://www.saucedemo.com/");


        webdriver.findElement(By.xpath("//input[@name='user-name']")).sendKeys(strUsername);
        webdriver.findElement(By.xpath("//input[@name='password']")).sendKeys(strPassword);
        webdriver.findElement(By.xpath("//input[@name='login-button']")).click();

        String priceA = webdriver.findElement(By.xpath("//div//a//div[text()='Sauce Labs Backpack']//parent::a//parent::div//following-sibling::div[@class='pricebar']//div")).getText();


        for (int i = 0; i < strProdukName.length; i++) {
            webdriver.findElement(By.xpath("//div//a//div[text()='" + strProdukName[i] + "']//parent::a//parent::div//following-sibling::div//button")).click();
        }

        webdriver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        String priceB = webdriver.findElement(By.xpath("//a//div[text()='Sauce Labs Backpack']//parent::a//following-sibling::div[@class='item_pricebar']//div")).getText();

        Assert.assertTrue("price not match\n", priceA.equals(priceB));
        System.out.println("price match");

        webdriver.findElement(By.xpath("//button[@id='checkout']")).click();


        webdriver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(strFirstName);
        webdriver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(strLastName);
        webdriver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(strZipCode);

        webdriver.findElement(By.xpath("//input[@id='continue']")).click();
        webdriver.findElement(By.xpath("//button[@id='finish']")).click();

        String expectedURL = "https://www.saucedemo.com/checkout-complete.html";
        String getCurrentURL = webdriver.getCurrentUrl();

        Assert.assertTrue("URL not match\n", expectedURL.equals(getCurrentURL));
        System.out.println("Done");




    }


    public static WebDriver startChromeDriver(String strFileNameDriver, WebDriver webdriver) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\" + strFileNameDriver);
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        return webdriver;
    }
}
