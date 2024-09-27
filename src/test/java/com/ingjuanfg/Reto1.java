package com.ingjuanfg;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Reto1 {

    WebDriver driver;

    @BeforeEach
    public void configuracionInicial() {
        // System.setProperty("webdriver.chrome.driver", "C:\\Users\\camil\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testExitoso() {
        //ARRANGE
        driver.get("https://teststore.automationtesting.co.uk/index.php");
        //ACT
        WebElement buttonSignIn = driver.findElement(By.xpath("//div[@id='_desktop_user_info']//a"));
        buttonSignIn.click();
        WebElement buttonCreateOne = driver.findElement(By.xpath("//div[@id='content']/div/a"));
        buttonCreateOne.click();
        //form[@id='customer-form']/div//descendant::input[@type='radio']
        WebElement inputRadioMr = driver.findElement(By.xpath("//input[@id = 'field-id_gender-2']/preceding::input[@type='radio']"));
        inputRadioMr.click();
        WebElement inputFirstName = driver.findElement(By.xpath("//div[contains(@class, 'form-group')]//input[@id='field-firstname']"));
        inputFirstName.sendKeys("camilo");
        WebElement inputLastName = driver.findElement(By.xpath("//div[contains(@class, 'form-group')]//input[@name='lastname']"));
        inputLastName.sendKeys("ramirez");
        WebElement inputEmail = driver.findElement(By.xpath("//label[contains(text(), 'Email')]/following-sibling::div//input"));

        long timestamp = Instant.now().getEpochSecond();
        String email = "camilo+" + timestamp + "@gmail.com";
        inputEmail.sendKeys(email);

        WebElement inputPassword = driver.findElement(By.xpath("//label[contains(text(), 'Password')]/following-sibling::div//input"));
        inputPassword.sendKeys("very secure password");
        WebElement birthdateInput = driver.findElement(By.xpath("//label[contains(text(), 'Birthdate')]/following-sibling::div//input"));
        birthdateInput.sendKeys("01/01/1990");

        WebElement checkboxOffers = driver.findElement(By.xpath("//span[@class='custom-checkbox']/descendant::input[@name='optin']"));
        checkboxOffers.click();
        WebElement checkboxTerms = driver.findElement(By.xpath("//span[@class='custom-checkbox']/descendant::input[@name='psgdpr']"));
        checkboxTerms.click();
        WebElement checkboxNewsletter = driver.findElement(By.xpath("//span[@class='custom-checkbox']/descendant::input[@name='newsletter']"));
        checkboxNewsletter.click();

        WebElement buttonSave = driver.findElement(By.xpath("//div[@id='content']//button[@type='submit']"));
        buttonSave.click();
        //ASSERT
        WebElement userName = driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']/following-sibling::a/span"));
        assertEquals("camilo ramirez", userName.getText());
    }

    @AfterEach
    public void configuracionFinal() {
        WebElement buttonSignOut = driver.findElement(By.xpath("//div[@class='user-info']/a"));
        buttonSignOut.click();
        driver.close();
    }
}
