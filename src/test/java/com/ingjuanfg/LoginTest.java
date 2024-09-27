package com.ingjuanfg;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    WebDriver driver;

    @BeforeEach
    public void configuracionInicial(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginExitoso() {
        //ARRANGE
        driver.get("https://www.saucedemo.com/");
        //ACT
        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        inputUser.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
        buttonLogin.click();
        //ASSERT
        WebElement pageTitle = driver.findElement(By.xpath("//span[@data-test='title']"));
        assertEquals("Products", pageTitle.getText());
    }

    @AfterEach
    public void configuracionFinal(){
        driver.close();
    }
}
