import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GoogleSMS {
    WebDriver driver;
    @BeforeMethod
    public void setup (){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDown (){
        driver.quit();
    }

    String[] brojevi = {"061111111","0622222","06333333"};
    String[] imena = {"Jovan","Pavle","Petar"};
    String message = "Cao $ kako ste?";
    @Test
    public void register() throws InterruptedException {
        driver.get("https://messages.google.com/web/authentication");

        for(int i = 0; i< brojevi.length; i++){
            driver.findElement(By.cssSelector("[href='/web/conversations/new']")).click();
            driver.findElement(By.cssSelector("span[role='presentation'] > input")).sendKeys(brojevi[i]);
            driver.findElement(By.xpath("//span[contains(text(),'"+brojevi[i]+"')]")).click();
            driver.findElement(By.cssSelector("textarea")).sendKeys(message.replace("$", imena[i]));
            driver.findElement(By.xpath("(//button[contains(@class, 'send-button')])[2]")).click();
            Thread.sleep(5000);

        }


    }

}
