package org.s367118.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Page{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickVideo(int videoNumber){
        String videoXPath = String.format("(//div[@data-video-id])[%d]", videoNumber);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement video = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoXPath)));
        video.click();
    }
}
