package org.s367118.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends Page{

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickVideo(int videoNumber){
        String videoXPath = String.format("(//a[@data-testid='video_card_thumb'])[%d]", videoNumber);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement video = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoXPath)));
        video.click();
    }
}
