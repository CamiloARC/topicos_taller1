package com.ingjuanfg;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Reto2 {

    WebDriver driver;

    @BeforeEach
    public void configuracionInicial() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\camil\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testExitoso() {
        //ARRANGE
        driver.get("https://www.saucedemo.com/");
        //ACT
        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        inputUser.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
        buttonLogin.click();

        WebElement priceProd1 = driver.findElement(By.xpath("//div[@data-test='inventory-item'][1]//div[@data-test='inventory-item-price']"));
        WebElement priceProd2 = driver.findElement(By.xpath("//div[@data-test='inventory-item'][2]//div[@data-test='inventory-item-price']"));
        WebElement buttonAddProd1 = driver.findElement(By.xpath("//div[@data-test='inventory-item'][1]//button"));
        WebElement buttonAddProd2 = driver.findElement(By.xpath("//div[@data-test='inventory-item'][2]//button"));
        WebElement buttonCart = driver.findElement(By.xpath("//div[@data-test='primary-header']/div/a"));

        buttonAddProd1.click();
        buttonAddProd2.click();
        float price1 = Float.parseFloat(priceProd1.getText().replace("$", ""));
        float price2 = Float.parseFloat(priceProd2.getText().replace("$", ""));
        buttonCart.click();

        WebElement buttonCheckout = driver.findElement(By.xpath("//div[@class='cart_footer']/button[contains(text(), 'Checkout')]"));
        buttonCheckout.click();

        WebElement inputFirstName = driver.findElement(By.xpath("//form/descendant::div[@class='form_group']/input[@data-test='firstName']"));
        WebElement inputLasName = driver.findElement(By.xpath("//input[@data-test='firstName']/following::input[@data-test='lastName']"));
        WebElement inputZip = driver.findElement(By.xpath("//input[@data-test='lastName']/following::input[@data-test='postalCode']"));
        WebElement inputContinue = driver.findElement(By.xpath("//div[@class='checkout_buttons']/input"));
        inputFirstName.sendKeys("camilo");
        inputLasName.sendKeys("ramirez");
        inputZip.sendKeys("123456");
        inputContinue.click();

        WebElement subtotalPrice = driver.findElement(By.xpath("//div[@class='summary_info']/div[@data-test='subtotal-label']"));
        WebElement buttonFinish = driver.findElement(By.xpath("//button[@data-test='cancel']/following-sibling::button"));

        // validar precios
        float subtotal = Float.parseFloat(subtotalPrice.getText().replace("Item total: $", ""));
        float sum = price1 + price2;

        //ASSERT 1
        assertEquals(sum, subtotal);

        buttonFinish.click();

        //ASSERT 2
        WebElement pageTitle = driver.findElement(By.xpath("//div[@data-test='secondary-header']/span"));
        assertEquals("Checkout: Complete!", pageTitle.getText());
    }

    @AfterEach
    public void configuracionFinal() {
        driver.close();
    }
}
