package org.s367118;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.s367118.page.MainPage;
import org.s367118.page.VideoPage;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://vkvideo.ru/video-51126445_456242756");

        VideoPage videoPage = new VideoPage(driver);

        videoPage.scrollDown(700);
        videoPage.justWait(Duration.ofSeconds(2));
        videoPage.deleteComment("Надежда Чебышева");
    }
}