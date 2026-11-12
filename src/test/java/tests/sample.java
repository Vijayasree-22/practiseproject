package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class sample {

    @Test
    public void TC_1() throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://qaplayground.com/");

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(1));

        WebDriverWait explicitwait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitwait.until(ExpectedConditions.urlToBe("https://qaplayground.com/"));

        WebElement practiseBtn = driver.findElement(By.xpath("//a[@href=\"/practice\" and text()= \"Practice\"]"));
        
        Wait<WebDriver> fluentwait = new FluentWait<>(driver)
        		.withTimeout(Duration.ofSeconds(10))
        		.pollingEvery(Duration.ofSeconds(2))
        		.ignoring(NoSuchElementException.class);
        fluentwait.until(ExpectedConditions.visibilityOf(practiseBtn));
        
        practiseBtn.click();
        WebElement iFrames = driver.findElement(By.xpath("//h3[text()='iFrames']"));
        iFrames.click();
        
        WebElement frame1 = driver.findElement(By.cssSelector("iframe[data-testid='iframe-basic']"));
        driver.switchTo().frame(frame1);
        
        WebElement basicFrame = driver.findElement(By.cssSelector("input[data-testid='iframe-name-input']"));
        basicFrame.sendKeys("jenny");
        
        WebElement basicSubmitBtn = driver.findElement(By.cssSelector("button#iframe-submit-btn"));
        explicitwait.until(ExpectedConditions.elementToBeClickable(basicSubmitBtn));
        basicSubmitBtn.click();
        
        driver.switchTo().defaultContent();
        String tabId = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://qaplayground.com/practice/iframes");
        driver.switchTo().window(tabId);
        
        Thread.sleep(4000);
//       TakesScreenshot ts = (TakesScreenshot) driver;
//       File source = ts.getScreenshotAs(OutputType.FILE);
//       File destination = new File("D:\\sweetie\\New folder\\img.png");
//       Files.copy(source, destination);
       
       
        driver.quit();
    }
}