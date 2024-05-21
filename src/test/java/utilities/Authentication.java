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
        driver.findElement(By.id("username")).sendKeys("sda2024@gmail.com");
        driver.findElement(By.id("password")).sendKeys("2JDTWt4UWdjGcNv");
        driver.findElement(By.tagName("button")).click();
        return driver.manage().getCookieNamed("GSESSIONID").getValue();
    }
}
