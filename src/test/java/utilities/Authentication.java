package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Authentication {

    public static String getSessionId(){
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qa-gm3.quaspareparts.com/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("bo@testevolve.com");
        driver.findElement(By.id("password")).sendKeys("FarahAl_huz@1234");
        driver.findElement(By.id("button")).click();
        return driver.manage().getCookieNamed("GSESSIONID").getValue();
    }
}
