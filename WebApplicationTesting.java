package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
  Web Application Testing
 */
public class WebApplicationTesting 
{
    public static void main( String[] args )
    {   //jaesung.lee.tech@gmail.com
        System.setProperty("webdriver.chrome.driver", "D:/programming/chromedriver.exe");
        //https://www.linkedin.com/in/jaesung-lee-tech/ 
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10,1));

        //input: Destination name
        String destinationName = "Kensington Market";

        long startTime = System.currentTimeMillis();

        driver.get("https://www.google.com/maps");

        destinationIntoTheSystem(destinationName, driver, wait);
        
        //Read the text
        String tripRoute = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div[1]/div[2]/h1[1]/span"))).getText();
        String tripTime = driver.findElement(By.xpath("/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]")).getText();
        
        driver.quit();

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        //output: The trip route, the trip time and the response time
        System.out.println("The trip route is: " + tripRoute);    
        System.out.println("The trip time is: " + tripTime);
        System.out.println("The Web Application Response Time is: " + responseTime + " milliseconds"); 
    
    }
    public static void destinationIntoTheSystem (String destinationName, WebDriver driver, WebDriverWait wait)
    {
        WebElement destination = driver.findElement(By.id("searchboxinput"));
        destination.click();
        destination.sendKeys(destinationName);
        destination.submit();

        //click the autocomplete result
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[role='row']"))).click();
        //Click the Direction button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-value='Directions']"))).click();
    }
}
